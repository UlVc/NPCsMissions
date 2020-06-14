package com.Utopia.NPCsMissions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.Utopia.NPCsMissions.Main;

public class SkipMission implements CommandExecutor {
	
	private Main plugin;
	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");
	
	public SkipMission(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("skipMission").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String noPermission = this.plugin.getConfig().getString("dont-have-permission");
		String missionSkipped = this.plugin.getConfig().getString("mission-skipped");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
			return true;
		}
		
		if (!(sender.hasPermission("NPCsMissions.skip_mission"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
			return true;
		}
		
		Player player = (Player) sender;
		
		if (args.length != 2) {
			player.sendMessage(ChatColor.RED + "Use /skipMission <Player> <Number of the mission>");
			return true;
		}
		
		try {
			int mission = Integer.parseInt(args[1]);
			String namePlayer = args[0];
			FileConfiguration file = Main.getData();
			
			if (mission > 15) {
				player.sendMessage(ChatColor.RED + "There are only 15 missions! (if you want more missions contact the Developer)");
				return true;
			}
			
			if (file.contains("missions_and_users")) {
				file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
					
					if (file.getInt("missions_and_users." + key + ".mission") == mission &&
							namePlayer.contains(file.getString("missions_and_users." + key + ".username"))) {
						
						if (mission == 15) {
							file.set("missions_and_users." + key + ".mission", null);
							file.set("missions_and_users." + key + ".can_he_do_missions_again", -1);
						} else
							file.set("missions_and_users." + key + ".mission", mission+1);
						
						Main.saveData();
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', missionSkipped));
						
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.getName().equalsIgnoreCase(file.getString("missions_and_users." + key + ".username"))) {
								getRewards(mission, p);
								return;
							}
						}
						
					} else if (file.getInt("missions_and_users." + key + ".mission") < mission &&
							namePlayer.contains(file.getString("missions_and_users." + key + ".username"))) {
						player.sendMessage(ChatColor.RED + args[0] + " has not finished the " + (mission-1) + " mission!");
						return;
					} else if (file.getInt("missions_and_users." + key + ".mission") > mission &&
							namePlayer.contains(file.getString("missions_and_users." + key + ".username"))) {
						player.sendMessage(ChatColor.RED + args[0] + " has already finished the " + (mission) + " mission!");
						return;
					}
					
				});
			}

			return true;
		} catch (Exception e) {
			player.sendMessage(ChatColor.RED + "Use /skipMission <Player> <Number of the mission> where <number of the mission> is a number (not decimal) and <Player> is the name of the player!");
			return true;
		}
		
	}
	
	private void getRewards(Integer mission, Player player) {
		
		if (mission == 1) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the harvest 200 wheat mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6a Kit Serf&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "kit Serf " + 
					player.getName());
		} else if (mission == 2) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix 
					+ "You've finished the visit the Boss Arena mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$300000&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
					player.getName() + " 300000");
		} else if (mission == 3) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the crafting equipment mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 Random Elite Enchant&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ae giverandombook " + 
					player.getName() + " Elite 1");
		} else if (mission == 4) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the place down a spawner mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 Crate Key &fand &6$150,000&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key " + 
					player.getName() + " Spawner 1");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
					player.getName() + " 150000");
		} else if (mission == 5) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the crafting redstone mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6Random Legendary Enchant&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ae giverandombook " + 
					player.getName() + " Legendary 1");
		} else if (mission == 6) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix 
					+ "You've finished the kill 3 players in the Warzone mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &625000 XP &fand &6$500000&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
					player.getName() + " 500000");
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "xp give " + 
					player.getName() + " 1000");
		} else if (mission == 7) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the fish 128 things mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 Mythic Crate Key&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key " + 
					player.getName() + " Mythic 1");
		} else if (mission == 8) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix 
					+ "You've finished the Climb to the highest point of the spawn mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &65 mega crystals&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "kit 5crystal " + 
					player.getName());
		} else if (mission == 9) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the craft 64 fireworks mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6zombie spawner&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawner give zombie " + 
					player.getName());
		} else if (mission == 10) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the place and craft 1 block of every color of wool mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$750,000 &4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
					player.getName() + " 750000");
		} else if (mission == 11) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the break ores mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6gamble voucher&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "voucher give Random_11 " + 
					player.getName());
		} else if (mission == 12) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the mine 500 blocks mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6heroic book&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ae giverandombook " + 
					player.getName() + " Heroic 1");
		} else if (mission == 13) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the place 500 blocks mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &63 Spawner Keys&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key " + 
					player.getName() + " Spawner 3");
		} else if (mission == 14) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the place and craft 1 block of every color of concrete powder mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$1,000,000 &4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
					player.getName() + " 1000000");
		} else if (mission == 15) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the kill 500 hostile mobs mission!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 beacon&4!"));
			
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + 
					player.getName() + " beacon 1");
			
			plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + player.getName() + " has &c&lfinished &6&lall the missions!"));

			for (Player online : plugin.getServer().getOnlinePlayers())
				online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
		}
	}
	
}
