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

public class Mission10 implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);

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
						Main.getData().set("missions_and_users." + key + ".crafting.white_whool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("ORANGE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a orange wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.orange_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("MAGENTA WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a magenta wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.magenta_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("LIGHT_BLUE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light blue wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.light_blue_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("YELLOW WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a yellow wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.yellow_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("LIME WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a lime wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.lime_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("PINK WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a pink wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.pink_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("GRAY WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a gray wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.gray_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("LIGHT_GRAY WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light gray wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.light_gray_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("CYAN WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a cyan wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.cyan_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("PURPLE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a purple wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.purple_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("BLUE WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a blue wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.blue_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("BROWN WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a brown wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.brown_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("GREEN WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a green wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.green_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("RED WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a red wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.red_wool", true);
						Main.saveData();
					}
					
					if (item.getType().equals(Material.WOOL) && item.getData().toString().contains("BLACK WOOL")) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a black wool."));
						Main.getData().set("missions_and_users." + key + ".crafting.black_wool", true);
						Main.saveData();
					}
					
					if (Main.getData().getBoolean("missions_and_users." + key + ".crafting.white_whool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.orange_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.magenta_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.light_blue_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.yellow_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.lime_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.pink_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.gray_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.light_gray_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.cyan_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.purple_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.blue_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.brown_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.green_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.red_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.black_wool")) {
						
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You were given " + ChatColor.GREEN + " 10 points of experience");
						player.giveExp(10);

						plugin.getServer().broadcastMessage(prefix + ChatColor.YELLOW + player.getName() + ChatColor.LIGHT_PURPLE
								+ " completed the wool colors craft mission.");

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Main.getData().set("missions_and_users." + key + ".crafting", null);
						Main.getData().set("missions_and_users." + key + ".mission", 11);
						Main.saveData();
						return;
						
					}
					
				}
				
			});
		}
	}
	
}