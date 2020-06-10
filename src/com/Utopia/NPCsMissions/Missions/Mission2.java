package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission2 implements Listener {
	
	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");
	
	@EventHandler
    public void visitTheNether(PlayerChangedWorldEvent event) {
		
		FileConfiguration file = Main.getData();
        
        if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 2 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
			        World world = player.getWorld();
					
					if (world.getName().contentEquals("world_nether")) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You finished the go to the nether mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$300,000&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
								file.getString("missions_and_users." + key + ".username") + " 300000");
						
						file.set("missions_and_users." + key + ".crafting", null);
						file.set("missions_and_users." + key + ".mission", 3);
						Main.saveData();
						return;

					}
				}
				
			});
		}
       
    }
	
}
