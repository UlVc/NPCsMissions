package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission10PlacedBlock implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void craftingReward(BlockPlaceEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 1 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
					Block block = event.getBlockPlaced();
					ItemStack item = event.getItemInHand();
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 0) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a white wool."));
						Main.getData().set("missions_and_users." + key + ".placed.white_whool", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 1) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed an orange wool."));
						Main.getData().set("missions_and_users." + key + ".placed.orange_whool", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 2) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a magenta wool."));
						Main.getData().set("missions_and_users." + key + ".placed.magenta_whool", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 3) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a light blue wool."));
						Main.getData().set("missions_and_users." + key + ".placed.light_blue_wool", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 4) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a yellow wool."));
						Main.getData().set("missions_and_users." + key + ".placed.yellow_wool", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 5) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a lime wool."));
						Main.getData().set("missions_and_users." + key + ".placed.lime_wool", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 6) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a pink wool."));
						Main.getData().set("missions_and_users." + key + ".placed.pink_wool", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 7) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a gray wool."));
						Main.getData().set("missions_and_users." + key + ".placed.gray_wool", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 8) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a light gray wool."));
						Main.getData().set("missions_and_users." + key + ".placed.light_gray_wool", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 9) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a cyan wool."));
						Main.getData().set("missions_and_users." + key + ".placed.cyan_wool", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 10) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a purple wool."));
						Main.getData().set("missions_and_users." + key + ".placed.purple_wool", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 11) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a blue wool."));
						Main.getData().set("missions_and_users." + key + ".placed.blue_wool", true);
						Main.saveData();
					}
						
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 12) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a brown wool."));
						Main.getData().set("missions_and_users." + key + ".placed.brown_wool", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 13) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a green wool."));
						Main.getData().set("missions_and_users." + key + ".placed.green", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 14) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a red wool."));
						Main.getData().set("missions_and_users." + key + ".placed.red_wool", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 15) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a black wool."));
						Main.getData().set("missions_and_users." + key + ".placed.black_wool", true);
						Main.saveData();
					}
					
					if (Main.getData().getBoolean("missions_and_users." + key + ".crafted.white_whool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.orange_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.magenta_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.light_blue_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.yellow_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.lime_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.pink_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.gray_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.light_gray_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.cyan_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.purple_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.blue_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.brown_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.green_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.red_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafted.black_wool") &&
							
							Main.getData().getBoolean("missions_and_users." + key + ".placed.white_whool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.orange_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.magenta_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.light_blue_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.yellow_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.lime_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.pink_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.gray_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.light_gray_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.cyan_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.purple_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.blue_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.brown_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.green_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.red_wool") &&
							Main.getData().getBoolean("missions_and_users." + key + ".placed.black_wool")) {
						
						player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "You were given " + ChatColor.GREEN + " 10 points of experience");
						player.giveExp(10);

						plugin.getServer().broadcastMessage(prefix + ChatColor.YELLOW + player.getName() + ChatColor.LIGHT_PURPLE
								+ " completed the wool colors mission.");

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Main.getData().set("missions_and_users." + key + ".crafted", null);
						Main.getData().set("missions_and_users." + key + ".placed", null);
						Main.getData().set("missions_and_users." + key + ".mission", 11);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}