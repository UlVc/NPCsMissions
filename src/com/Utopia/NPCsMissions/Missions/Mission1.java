package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class Mission1 implements Listener {

    private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");
    private Economy econ;
    
    public Mission1(Economy econ) {
    	this.econ = econ;
    }

	@EventHandler
	public void commandMessage(PlayerCommandPreprocessEvent event) {
		FileConfiguration file = Main.getData();
		Player player = event.getPlayer();
		@SuppressWarnings("deprecation")
		double balance = econ.getBalance(event.getPlayer().getName());
		
		if (event.getMessage().equalsIgnoreCase("/lottery buy 5") && balance >= 250000 
				&& file.contains("missions_and_users")) {
			
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 1 && 
						player.toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the Buy 5 Lottery Tickets at the same time!"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6a Kit Serf&4!"));
					
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "kit Serf " + 
							file.getString("missions_and_users." + key + ".username"));
					
					file.set("missions_and_users." + key + ".mission", 2);
					Main.saveData();
					
					return;
					
				}
				
			});
		}
	}

}
