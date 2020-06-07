package com.Utopia.NPCsMissions;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.Utopia.NPCsMissions.Events.RightClickNPC;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

public class NPC {

	private static List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
	
	public static void createNPC(Player player, String skin, String colorFormat) {
		
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.translateAlternateColorCodes('&', colorFormat + skin));
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),
				player.getLocation().getYaw(), player.getLocation().getPitch());
		
		String[] name = getSkin(player, skin);
		gameProfile.getProperties().put("textures", new Property("textures", name[0], name[1]));
		
		addNPCPacket(npc);
		NPC.add(npc); 
		System.out.println(NPC);
		
		int var = 1;
		if (Main.getData().contains("data"))
			var = Main.getData().getConfigurationSection("data").getKeys(false).size() + 1; // Only childs
		
		Main.getData().set("data." + var + ".x", (int) player.getLocation().getX());
		Main.getData().set("data." + var + ".y", (int) player.getLocation().getY());
		Main.getData().set("data." + var + ".z", (int) player.getLocation().getZ());
		Main.getData().set("data." + var + ".pitch", player.getLocation().getPitch());
		Main.getData().set("data." + var + ".yaw", player.getLocation().getYaw());
		Main.getData().set("data." + var + ".world", player.getLocation().getWorld().getName());
		Main.getData().set("data." + var + ".name", skin);
		Main.getData().set("data." + var + ".text", name[0]);
		Main.getData().set("data." + var + ".signature", name[1]);
		Main.saveData();
		
	}
	
	public static void loadNPC(Location location, GameProfile profile) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(location.getWorld().getName())).getHandle();
		GameProfile gameProfile = profile;
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		npc.setLocation(location.getX(), location.getY(), location.getZ(),
				location.getYaw(), location.getPitch());
		
		addNPCPacket(npc);
		NPC.add(npc); 
	}
	
	private static String[] getSkin(Player player, String name) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
			
			URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid
					+ "?unsigned=false");
			InputStreamReader reader2 = new InputStreamReader(url2.openStream());
			JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties")
					.getAsJsonArray().get(0).getAsJsonObject();
			String texture = property.get("value").getAsString();
			String signature = property.get("signature").getAsString();
			return new String[] {texture, signature};	
			
		} catch (Exception e) {
			EntityPlayer p = ((CraftPlayer) player).getHandle();
			GameProfile profile = p.getProfile();
			Property property = profile.getProperties().get("textures").iterator().next();
			String texture = property.getValue();
			String signature = property.getSignature();
			return new String[] {texture, signature}; 
	        
		}
	}
	
	public static void addNPCPacket(EntityPlayer npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}
	
	public static void removeNPC(Player player, EntityPlayer npc) {
		PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
	}
	
	public static void addJoinPacket(Player player) {
		for (EntityPlayer npc : NPC) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
	}
	
	public static List<EntityPlayer> getNPCs() {
		return NPC;
	}
	
	public static void renameNPC(String name, RightClickNPC npcSelected) {
		
		int npcX = (int) npcSelected.getNPC().locX;
		int npcY = (int) npcSelected.getNPC().locY;
		int npcZ = (int) npcSelected.getNPC().locZ;
		
		FileConfiguration file = Main.getData();
		
		file.getConfigurationSection("data").getKeys(false).forEach(key -> {
			
			if (file.getInt("data." + key + ".x") == npcX && 
					file.getInt("data." + key + ".y") == npcY && 
					file.getInt("data." + key + ".z") == npcZ) {
				Main.getData().set("data." + key + ".name", name);
				Main.saveData();
				return;
			}		
			
		});
	}

	public static void removeNPC(RightClickNPC npcSelected) {
		
		int npcX = (int) npcSelected.getNPC().locX;
		int npcY = (int) npcSelected.getNPC().locY;
		int npcZ = (int) npcSelected.getNPC().locZ;
		
		FileConfiguration file = Main.getData();
		
		file.getConfigurationSection("data").getKeys(false).forEach(key -> {
			
			if (file.getInt("data." + key + ".x") == npcX && 
					file.getInt("data." + key + ".y") == npcY && 
					file.getInt("data." + key + ".z") == npcZ) {
				Main.getData().set("data." + key, null);
				Main.saveData();
				return;
			}		
			
		});
		
	}

	public static void moveNPC(Player player, RightClickNPC npcSelected) {
		
		int npcX = (int) npcSelected.getNPC().locX;
		int npcY = (int) npcSelected.getNPC().locY;
		int npcZ = (int) npcSelected.getNPC().locZ;
		
		FileConfiguration file = Main.getData();
		
		file.getConfigurationSection("data").getKeys(false).forEach(key -> {
			
			if (file.getInt("data." + key + ".x") == npcX && 
					file.getInt("data." + key + ".y") == npcY && 
					file.getInt("data." + key + ".z") == npcZ) {
				Main.getData().set("data." + key + ".x", (int) player.getLocation().getX());
				Main.getData().set("data." + key + ".y", (int) player.getLocation().getY());
				Main.getData().set("data." + key + ".z", (int) player.getLocation().getZ());
				Main.getData().set("data." + key + ".pitch", player.getLocation().getPitch());
				Main.getData().set("data." + key + ".yaw", player.getLocation().getYaw());
				Main.saveData();
				return;
			}		
			
		});
		
	}
	
}
