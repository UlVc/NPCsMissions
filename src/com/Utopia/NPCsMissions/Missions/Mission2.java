package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.Plugin;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission2 implements Listener {
	
	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);
	
	@EventHandler
    public void wentToNether(PlayerChangedWorldEvent event) {
		
		FileConfiguration file = Main.getData();
        
        if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 2 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
			        World world = player.getWorld();
					
					if (world.getName().contentEquals("world_nether")) {
						
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You were given " + ChatColor.GREEN + " 10 points of experience");
						player.giveExp(10);

						plugin.getServer().broadcastMessage(prefix + ChatColor.YELLOW + player.getName() + ChatColor.LIGHT_PURPLE
								+ " went to the nether.");

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Main.getData().set("missions_and_users." + key + ".mission", 3);
						Main.saveData();
						
						return;
					}
				}
				
			});
		}
       
    }
	
}
