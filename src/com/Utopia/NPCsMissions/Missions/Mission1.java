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

public class Mission1 implements Listener {
	
	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockBreakOres(BlockBreakEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 1 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
					Block block = event.getBlock();
					
					if (block.getType().equals(Material.CROPS) && event.getBlock().getData() == 7 && 
							file.getInt("missions_and_users." + key + ".broken_blocks.wheat") < 200) {
						
						int count = file.getInt("missions_and_users." + key + ".broken_blocks.wheat") + 1;
						file.set("missions_and_users." + key + ".broken_blocks.wheat", count);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You harvest wheat. &9[&a" + count + "&6/&a200&9]"));
						
					}
					
					if (file.getInt("missions_and_users." + key + ".broken_blocks.wheat") == 200) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the harvest 200 wheat mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6knight kit&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "kit knight " + 
								file.getString("missions_and_users." + key + ".username"));
						
						file.set("missions_and_users." + key + ".broken_blocks", null);
						file.set("missions_and_users." + key + ".mission", 2);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}