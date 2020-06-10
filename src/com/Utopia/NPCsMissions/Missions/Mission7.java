package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission7 implements Listener {

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void fishingEvent(PlayerFishEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 7 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					int count = Main.getData().getInt("missions_and_users." + key + ".fishing_counter");
					Player player = (Player) event.getPlayer();

					if (State.CAUGHT_FISH.equals(event.getState()) && count < 128) {
						
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',"You fished! &9[&a" + (count+1) + "&6/&a128&9]"));
						
						file.set("missions_and_users." + key + ".fishing_counter", count + 1);
						Main.saveData();
						count += 1;

					}
					
					if (count > 127) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the fish 128 things mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 Mythic Crate Key&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "crate key " + 
								file.getString("missions_and_users." + key + ".username") + " Mythic 1");
						
						file.set("missions_and_users." + key + ".mission", 8);
						file.set("missions_and_users." + key + ".fishing_counter", null);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}