package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission3 implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void craftingReward(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 3 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();
					
					if (item.getType().equals(Material.LEATHER_LEGGINGS)) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.leather_leggings", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted leggings of leather."));
						
					}
					
					if (item.getType().equals(Material.IRON_HELMET)) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.iron_helmet", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted an iron helmet."));
						
					}
					
					if (item.getType().equals(Material.GOLD_BOOTS)) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.gold_boots", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted boots of gold."));
						
					}
					
					if (item.getType().equals(Material.DIAMOND_CHESTPLATE)) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.diamond_chestplate", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a diamond chestplate."));
						
					}
					
					if (Main.getData().getBoolean("missions_and_users." + key + ".crafting.leather_leggings") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.iron_helmet") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.gold_boots") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.diamond_chestplate")) {
						
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You were given " + ChatColor.GREEN + " 10 points of experience");
						player.giveExp(10);

						plugin.getServer().broadcastMessage(prefix + ChatColor.YELLOW + player.getName() + ChatColor.LIGHT_PURPLE
								+ " completed the armor craft mission.");

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Main.getData().set("missions_and_users." + key + ".crafting", null);
						Main.getData().set("missions_and_users." + key + ".mission", 4);
						Main.saveData();
						return;
						
					}
					
				}
				
			});
		}
	}
	
}