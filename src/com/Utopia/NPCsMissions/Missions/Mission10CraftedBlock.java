package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission10CraftedBlock implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");

	@EventHandler
	public void craftingReward(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 1 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("WHITE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a white wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.white_whool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("ORANGE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted an orange wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.orange_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("MAGENTA WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a magenta wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.magenta_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("LIGHT_BLUE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light blue wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.light_blue_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("YELLOW WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a yellow wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.yellow_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("LIME WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a lime wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.lime_wool", true);
						Main.saveData();
						return;
					}
	
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("PINK WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a pink wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.pink_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("GRAY WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a gray wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.gray_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("LIGHT_GRAY WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light gray wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.light_gray_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("CYAN WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a cyan wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.cyan_wool", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("PURPLE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a purple wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.purple_wool", true);
						Main.saveData();
						return;
					}
			
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("BLUE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a blue wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.blue_wool", true);
						Main.saveData();
						return;
					}
			
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("BROWN WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a brown wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.brown_wool", true);
						Main.saveData();
						return;
					}
	
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("GREEN WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a green wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.green", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("RED WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a red wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.red_wool", true);
						Main.saveData();
						return;
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("BLACK WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a black wool."));
						Main.getData().set("missions_and_users." + key + ".crafted.black_wool", true);
						Main.saveData();
						return;
					}
					
				}
				
			});
		}
	}
	
}