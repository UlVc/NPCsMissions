package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission10PlacedBlock implements Listener {

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void placedConcretePowder(BlockPlaceEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 10 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
					Block block = event.getBlockPlaced();
					ItemStack item = event.getItemInHand();
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 0 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.white") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a white wool."));
						file.set("missions_and_users." + key + ".placed_wool.white", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 1 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.orange") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed an orange wool."));
						file.set("missions_and_users." + key + ".placed_wool.orange", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 2 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.magenta") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a magenta wool."));
						file.set("missions_and_users." + key + ".placed_wool.magenta", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 3 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.light_blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a light blue wool."));
						file.set("missions_and_users." + key + ".placed_wool.light_blue", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 4 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.yellow") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a yellow wool."));
						file.set("missions_and_users." + key + ".placed_wool.yellow", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 5 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.lime") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a lime wool."));
						file.set("missions_and_users." + key + ".placed_wool.lime", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 6 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.pink") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a pink wool."));
						file.set("missions_and_users." + key + ".placed_wool.pink", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 7 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a gray wool."));
						file.set("missions_and_users." + key + ".placed_wool.gray", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 8 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.light_gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a light gray wool."));
						file.set("missions_and_users." + key + ".placed_wool.light_gray", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 9 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.cyan") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a cyan wool."));
						file.set("missions_and_users." + key + ".placed_wool.cyan", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 10 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.purple") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a purple wool."));
						file.set("missions_and_users." + key + ".placed_wool.purple", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 11 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a blue wool."));
						file.set("missions_and_users." + key + ".placed_wool.blue", true);
						Main.saveData();
					}
						
					if (block.getType().equals(Material.WOOL) && item.getDurability() == 12 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.brown") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a brown wool."));
						file.set("missions_and_users." + key + ".placed_wool.brown", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 13 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.green") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a green wool."));
						file.set("missions_and_users." + key + ".placed_wool.green", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 14 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.red") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a red wool."));
						file.set("missions_and_users." + key + ".placed_wool.red", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.WOOL) && item.getDurability() == 15 &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.black") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a black wool."));
						file.set("missions_and_users." + key + ".placed_wool.black", true);
						Main.saveData();
					}
					
					if (file.getBoolean("missions_and_users." + key + ".crafted_wool.white") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.orange") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.magenta") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.light_blue") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.yellow") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.lime") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.pink") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.gray") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.light_gray") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.cyan") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.purple") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.blue") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.brown") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.green") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.red") &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.black") &&
							
							file.getBoolean("missions_and_users." + key + ".placed_wool.white") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.orange") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.magenta") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.light_blue") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.yellow") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.lime") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.pink") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.gray") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.light_gray") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.cyan") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.purple") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.blue") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.brown") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.green") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.red") &&
							file.getBoolean("missions_and_users." + key + ".placed_wool.black")) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the place and craft 1 block of every color of wool mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$750,000 &4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
								file.getString("missions_and_users." + key + ".username") + " 750000");
						
						file.set("missions_and_users." + key + ".crafted_wool", null);
						file.set("missions_and_users." + key + ".placed_wool", null);
						file.set("missions_and_users." + key + ".mission", 11);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}