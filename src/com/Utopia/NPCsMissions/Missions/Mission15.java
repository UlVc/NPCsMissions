package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission15 implements Listener {

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void playerKilledHostileMob(EntityDeathEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 15 &&
						event.getEntity().getKiller().toString().contains(file.getString("missions_and_users." + key + ".username")) &&
						event.getEntity() instanceof Monster) {
					
					Player player = (Player) event.getEntity().getKiller();
					
					if (file.getInt("missions_and_users." + key + ".killed_mobs_counter") < 500) {
						
						int count = file.getInt("missions_and_users." + key + ".killed_mobs_counter");
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You killed a hostile mob. &9[&a" + (count+1) + "&6/&a500&9]"));
						Main.getData().set("missions_and_users." + key + ".killed_mobs_counter", count+1);
						Main.saveData();
						
					}
					
					if (file.getInt("missions_and_users." + key + ".killed_mobs_counter") == 500) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the kill 500 hostile mobs mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 beacon&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + 
								file.getString("missions_and_users." + key + ".username") + " beacon 1");
						
						plugin.getServer().broadcastMessage(prefix + ChatColor.translateAlternateColorCodes('&', player.getName() 
								+ " has &c&lfinished &6&lall the missions!"));

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						file.set("missions_and_users." + key + ".killed_mobs_counter", null);
						file.set("missions_and_users." + key + ".mission", null);
						file.set("missions_and_users." + key + ".can_he_do_missions_again", false);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}