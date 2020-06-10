package com.Utopia.NPCsMissions;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.Utopia.NPCsMissions.Events.Join;
import com.Utopia.NPCsMissions.Files.DataManager;
import com.Utopia.NPCsMissions.Missions.*;
import com.Utopia.NPCsMissions.NPCs.ClickNPC;
import com.Utopia.NPCsMissions.NPCs.NPC;
import com.Utopia.NPCsMissions.NPCs.PacketReader;
import com.Utopia.NPCsMissions.commands.*;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class Main extends JavaPlugin implements Listener{
	
	public static DataManager data;
	private ClickNPC npcClicked = new ClickNPC(this);

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		
		data = new DataManager(this);
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + 
				"\n\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n NPCs Missions has been enabled \n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
		
		this.getServer().getPluginManager().registerEvents(new Join(), this);
		this.getServer().getPluginManager().registerEvents(npcClicked, this);
		this.getServer().getPluginManager().registerEvents(new Missions(), this);
		this.getServer().getPluginManager().registerEvents(new Mission2(), this);
		this.getServer().getPluginManager().registerEvents(new Mission3(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission4(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission7(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission9(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission10CraftedBlock(), this);
		this.getServer().getPluginManager().registerEvents(new Mission10PlacedBlock(), this);
		this.getServer().getPluginManager().registerEvents(new Mission11(), this);
		this.getServer().getPluginManager().registerEvents(new Mission12(), this);
		this.getServer().getPluginManager().registerEvents(new Mission13(), this);//done
		this.getServer().getPluginManager().registerEvents(new Mission14CraftedBlock(), this);//done
		this.getServer().getPluginManager().registerEvents(new Mission14PlacedBlock(), this);//done
		this.getServer().getPluginManager().registerEvents(new Mission15(), this); //done
		
		new CreateNPC(this);
		new RemoveNPC(this, npcClicked);
		new RenameNPC(this, npcClicked);
		new MoveNPCHere(this, npcClicked);
		new AssignNumberOfMission(this, npcClicked);
		
		if (data.getConfig().contains("data"))
			loadNPC();
		
		reInjectPlayers();
	}
	
	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PacketReader reader = new PacketReader();
			reader.uninject(player);
			for (EntityPlayer npc : NPC.getNPCs())
				NPC.removeNPC(player, npc);
		}
	}
	
	public static FileConfiguration getData() {
		return data.getConfig();
	}
	
	public static void saveData() {
		data.saveConfig();
	}
	
	public void loadNPC() {
		FileConfiguration file = data.getConfig();
		file.getConfigurationSection("data").getKeys(false).forEach(npc -> {
			Location location = new Location(Bukkit.getWorld(file.getString("data." + npc + ".world")),
					file.getInt("data." + npc + ".x"), file.getInt("data." + npc + ".y"), file.getInt("data." + npc + ".z"));
			location.setPitch((float) file.getDouble("data." + npc + ".p"));
			location.setYaw((float) file.getDouble("data." + npc + ".yaw"));
			
			String name = file.getString("data." + npc + ".name");
			GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.DARK_AQUA + "" + ChatColor.BOLD + name);
			gameProfile.getProperties().put("textures",new Property("textures", file.getString("data." + npc + ".text"),
					file.getString("data." + npc + ".signature")));
			
			NPC.loadNPC(location, gameProfile);
		});
	}
	
	public void reInjectPlayers() {
		if (!Bukkit.getOnlinePlayers().isEmpty())
			for (Player p :Bukkit.getOnlinePlayers()) {
				PacketReader reader = new PacketReader();
				reader.inject(p);
			}
	}
	
}
