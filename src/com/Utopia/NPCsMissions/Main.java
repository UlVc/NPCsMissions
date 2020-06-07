package com.Utopia.NPCsMissions;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.Utopia.NPCsMissions.Events.ClickNPC;
import com.Utopia.NPCsMissions.Events.Join;
import com.Utopia.NPCsMissions.Events.RightClickNPC;
import com.Utopia.NPCsMissions.Missions.Missions;
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
	
	@Override
	public void onLoad() {
		reInjectPlayers();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("createNPC")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
				return true;
			}
			if (!(sender.hasPermission("NPCsMissions.create"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				String name = player.getName();
				if (name.length() > 15) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lI can't soport 16 characters or more yet!"));
					return false;
				}
				NPC.createNPC(player, name, "&c&l");
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Created! :)"));
				reInjectPlayers();
				return true;
			}
			
			if (args[0].length() > 15) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lI can't soport 16 characters or more yet!"));
				return false;
			}
			
			if (args.length < 2) {
				NPC.createNPC(player, args[0], "&c&l");
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Created! :)"));
				reInjectPlayers();
				return true;
			}
			
			NPC.createNPC(player, args[0], args[1]);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Created! :)"));
			reInjectPlayers();
			return true;	
			
		}
		
		if (label.equalsIgnoreCase("renameNPC")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
				return true;
			}
			if (!(sender.hasPermission("NPCsMissions.rename"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
				return true;
			}
			Player player = (Player) sender;
			RightClickNPC npcSelected = npcClicked.getNPCSelected();
			
			if (args.length == 0 || npcSelected == null) {
				player.sendMessage(ChatColor.RED + "Use /renameNPC <new_name_of_the_npc> after selecting the NPC.");
				return true;
			}
			
			NPC.renameNPC(args[0], npcSelected);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC renamed!"));
			for (Player p : Bukkit.getOnlinePlayers()) {
				PacketReader reader = new PacketReader();
				reader.uninject(p);
				for (EntityPlayer npc : NPC.getNPCs())
					NPC.removeNPC(p, npc);
			}
			loadNPC();
			return true;
		}
		
		return false;
	}
	
	public static FileConfiguration getData() {
		return data.getConfig();
	}
	
	public static void saveData() {
		data.saveConfig();
	}
	
	public void loadNPC() {
		FileConfiguration file = data.getConfig();
		file.getConfigurationSection("data").getKeys(false).forEach(npc ->{
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
	
	private void reInjectPlayers() {
		if (!Bukkit.getOnlinePlayers().isEmpty())
			for (Player p :Bukkit.getOnlinePlayers()) {
				PacketReader reader = new PacketReader();
				reader.inject(p);
			}
	}
	
}
