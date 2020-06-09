package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission12 implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void blockBreakOres(BlockBreakEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 12 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
					
					if (file.getInt("missions_and_users." + key + ".blocks_mined") < 500) {
						int count = file.getInt("missions_and_users." + key + ".blocks_mined");
						
						file.set("missions_and_users." + key + ".blocks_mined", count + 1);
						Main.saveData();
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You mined a block. &9[&a" + (count+1) + "&6/&a500&9]"));
					}

					if (file.getInt("missions_and_users." + key + ".blocks_mined") == 500) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You finished the mine 500 blocks mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You recieved a &6heroic book&4!"));

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ae giverandombook " + 
								file.getString("missions_and_users." + key + ".username") + " Heroic 1");
						
						file.set("missions_and_users." + key + ".blocks_mined", null);
						file.set("missions_and_users." + key + ".mission", 13);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}