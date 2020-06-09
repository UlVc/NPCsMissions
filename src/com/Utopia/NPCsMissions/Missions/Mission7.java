package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.plugin.Plugin;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission7 implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void fishingEvent(PlayerFishEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 1 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					int count = Main.getData().getInt("missions_and_users." + key + ".fishing_count");
					
					Player player = (Player) event.getPlayer();

					if (State.CAUGHT_FISH.equals(event.getState())) {
						
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You fished! Fishes left: " + (127-count));
						
						Main.getData().set("missions_and_users." + key + ".fishing_count", count + 1);
						Main.saveData();

					}
					
					if (count == 128) {
						plugin.getServer().broadcastMessage(prefix + ChatColor.YELLOW + player.getName() + ChatColor.LIGHT_PURPLE
								+ " has finished the fishing mission.");

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Main.getData().set("missions_and_users." + key + ".mission", 8);
						Main.getData().set("missions_and_users." + key + ".fishing_count", null);
						Main.saveData();
						return;
					}
					
				}
				
			});
		}
	}
	
}