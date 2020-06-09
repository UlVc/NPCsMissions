package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
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

public class Mission14PlacedBlock implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");
	private Plugin plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void placedConcretePowder(BlockPlaceEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 14 &&
						event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getPlayer();
					Block block = event.getBlockPlaced();
					ItemStack item = event.getItemInHand();
					
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 0 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.white") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a white concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.white", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 1 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.orange") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed an orange concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.orange", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 2 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.magenta") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a magenta concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.magenta", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 3 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.light_blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a light blue concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.light_blue", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 4 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.yellow") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a yellow concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.yellow", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 5 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.lime") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a lime concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.lime", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 6 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.pink") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a pink concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.pink", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 7 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a gray concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.gray", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 8 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.light_gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a light gray concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.light_gray", true);
						Main.saveData();
					}
					
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 9 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.cyan") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a cyan concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.cyan", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 10 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.purple") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a purple concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.purple", true);
						Main.saveData();
					}
		
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 11 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a blue concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.blue", true);
						Main.saveData();
					}
						
					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 12 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.brown") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a brown concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.brown", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 13 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.green") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a green concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.green", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 14 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.red") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a red concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.red", true);
						Main.saveData();
					}

					if (block.getType().equals(Material.CONCRETE_POWDER) && item.getDurability() == 15 &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.black") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You placed a black concrete powder."));
						file.set("missions_and_users." + key + ".placed_concrete_powder.black", true);
						Main.saveData();
					}
					
					if (file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.white") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.orange") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.magenta") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.light_blue") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.yellow") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.lime") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.pink") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.gray") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.light_gray") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.cyan") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.purple") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.blue") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.brown") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.green") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.red") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.black") &&
							
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.white") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.orange") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.magenta") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.light_blue") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.yellow") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.lime") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.pink") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.gray") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.light_gray") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.cyan") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.purple") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.blue") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.brown") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.green") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.red") &&
							file.getBoolean("missions_and_users." + key + ".placed_concrete_powder.black")) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You finished the place and craft 1 block of every color of concrete powder mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$1,000,000 &4!"));

						for (Player online : plugin.getServer().getOnlinePlayers())
							online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
								file.getString("missions_and_users." + key + ".username") + " 1000000");
						
						file.set("missions_and_users." + key + ".placed_concrete_powder", null);
						file.set("missions_and_users." + key + ".placed_concrete_powder", null);
						file.set("missions_and_users." + key + ".mission", 15);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
}