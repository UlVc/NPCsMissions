package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission11 implements Listener {
	
	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void blockBreakOres(BlockBreakEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 11 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
					Block block = event.getBlock();
					
					if (block.getType().equals(Material.COAL_ORE) && file.getInt("missions_and_users." + key + ".ores_broken.coal") < 32) {
						
						int count = file.getInt("missions_and_users." + key + ".ores_broken.coal");
						file.set("missions_and_users." + key + ".ores_broken.coal", count + 1);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You broke a coal ore. &9[&a" + (count+1) + "&6/&a32&9]"));
						
					}
					
					if (block.getType().equals(Material.IRON_ORE) && file.getInt("missions_and_users." + key + ".ores_broken.iron") < 32) {
						
						int count = file.getInt("missions_and_users." + key + ".ores_broken.iron");
						file.set("missions_and_users." + key + ".ores_broken.iron", count + 1);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You broke an iron ore. &9[&a" + (count+1) + "&6/&a32&9]"));
						
					}
					
					if (block.getType().equals(Material.GOLD_ORE) && file.getInt("missions_and_users." + key + ".ores_broken.gold") < 32) {
						
						int count = file.getInt("missions_and_users." + key + ".ores_broken.gold");
						file.set("missions_and_users." + key + ".ores_broken.gold", count + 1);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You broke a gold ore. &9[&a" + (count+1) + "&6/&a32&9]"));
						
					}
					
					if (block.getType().equals(Material.DIAMOND_ORE) && file.getInt("missions_and_users." + key + ".ores_broken.diamond") < 16) {
						
						int count = file.getInt("missions_and_users." + key + ".ores_broken.diamond");
						file.set("missions_and_users." + key + ".ores_broken.diamond", count + 1);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You broke a diamond ore. &9[&a" + (count+1) + "&6/&a16&9]"));
						
					}
					
					if (block.getType().equals(Material.EMERALD_ORE) && file.getInt("missions_and_users." + key + ".ores_broken.emerald") < 16) {
						
						int count = file.getInt("missions_and_users." + key + ".ores_broken.emerald");
						file.set("missions_and_users." + key + ".ores_broken.emerald", count + 1);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You broke an emerald ore. &9[&a" + (count+1) + "&6/&a16&9]"));
						
					}
					
					if (file.getInt("missions_and_users." + key + ".ores_broken.coal") == 32 && 
							file.getInt("missions_and_users." + key + ".ores_broken.iron") == 32 &&
							file.getInt("missions_and_users." + key + ".ores_broken.gold") == 32 &&
							file.getInt("missions_and_users." + key + ".ores_broken.diamond") == 16 &&
							file.getInt("missions_and_users." + key + ".ores_broken.emerald") == 16) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the break ores mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6gamble voucher&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "voucher give Random_11 " + 
								file.getString("missions_and_users." + key + ".username"));
						
						file.set("missions_and_users." + key + ".ores_broken", null);
						file.set("missions_and_users." + key + ".mission", 12);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}