package com.Utopia.NPCsMissions;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

public class NPC {
	
	private static List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
	
	private static String[] getSkin(Player player, String name) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
			
			URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
			InputStreamReader reader2 = new InputStreamReader(url2.openStream());
			JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
			/*
			 * Player:
			 *   properties:
             *      signature: imasignature
			 *      value: imatexture (texture)
			 * 
			 * */
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
	
	public static void createNPC(Player player, String skin) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.AQUA + "" + ChatColor.BOLD + skin);
		EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		// Pitch: Up and down, Yaw: Left and right
		npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 
				        player.getLocation().getYaw(), player.getLocation().getPitch()); 
		
		String[] name = getSkin(player, skin);
		gameProfile.getProperties().put("textures", new Property("textures", name[0], name[1]));
		
		addNPCPacket(npc);
		NPC.add(npc);
	}
	
	public static void addNPCPacket(EntityPlayer npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256/360)));
		}
	}
	
	// When someone joins after sending the packets
	public static void addJoinNPCPacket(Player player) {
		for (EntityPlayer npc : NPC) {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256/360)));
		}
	}
	
	public static List<EntityPlayer> getNPCs() {
		return NPC;
	}
	
	public static void printList() {
		System.out.println(NPC.toString());
	}
	
}
