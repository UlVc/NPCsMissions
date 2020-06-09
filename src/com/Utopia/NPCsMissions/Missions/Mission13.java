package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission13 implements Listener {
	
	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");

	@EventHandler
    public void placeBlocks(BlockPlaceEvent event) {
		
		FileConfiguration file = Main.getData();
        
        
        if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 13 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();        
					
					if (file.getInt("missions_and_users." + key + ".placed_blocks") < 500) {
						int count = file.getInt("missions_and_users." + key + ".placed_blocks");
						
						file.set("missions_and_users." + key + ".placed_blocks", count + 1);
						Main.saveData();
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a block. &9[&a" + (count+1) + "&6/&a500&9]"));
					}

					if (file.getInt("missions_and_users." + key + ".placed_blocks") == 500) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You finished the place 500 blocks mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &63 Spawner Keys&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key " + 
								file.getString("missions_and_users." + key + ".username") + " Spawner 3");
						
						file.set("missions_and_users." + key + ".placed_blocks", null);
						file.set("missions_and_users." + key + ".mission", 14);
						Main.saveData();
						
						return;
						
					}
				}
				
			});
		}
       
    }
	
}
