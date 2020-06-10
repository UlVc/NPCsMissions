package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission4 implements Listener {
	
	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");
	
	@EventHandler
    public void placeBlockSpawner(BlockPlaceEvent event) {
		
		FileConfiguration file = Main.getData();
        
        
        if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 4 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
			        Block block= event.getBlockPlaced();	        
					
					if (block.getType().equals(Material.MOB_SPAWNER)) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the place down a spawner mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 Crate Key &fand &6$150,000&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key " + 
								file.getString("missions_and_users." + key + ".username") + " Spawner 1");
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
								file.getString("missions_and_users." + key + ".username") + " 150000");
						
						file.set("missions_and_users." + key + ".mission", 5);
						Main.saveData();
						
						return;

					}
				}
				
			});
		}
       
    }
	
}
