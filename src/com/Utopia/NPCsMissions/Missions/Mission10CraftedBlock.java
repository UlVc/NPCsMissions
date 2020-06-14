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

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void craftedWool(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 10 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();
					
					System.out.println(item.getDurability());
					
					if (item.getType().equals(Material.WOOL) && item.getDurability() == 0 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.white") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a white wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.white", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 1 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.orange") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted an orange wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.orange", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 2 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.magenta") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a magenta wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.magenta", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 3 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.light_blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light blue wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.light_blue", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 4 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.yellow") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a yellow wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.yellow", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 5 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.lime") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a lime wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.lime", true);
						Main.saveData();
						return;
					}
	
					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 6 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.pink") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a pink wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.pink", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 7 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a gray wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.gray", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 8 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.light_gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light gray wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.light_gray", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 9 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.cyan") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a cyan wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.cyan", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 10 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.purple") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a purple wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.purple", true);
						Main.saveData();
						return;
					}
			
					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 11 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a blue wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.blue", true);
						Main.saveData();
						return;
					}
			
					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 12 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.brown") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a brown wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.brown", true);
						Main.saveData();
						return;
					}
	
					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 13 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.green") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a green wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.green", true);
						Main.saveData();
						return;
					}

					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 14 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.red") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a red wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.red", true);
						Main.saveData();
						return;
					}
					
					else if (item.getType().equals(Material.WOOL) && item.getDurability() == 15 &&
							file.getBoolean("missions_and_users." + key + ".crafted_wool.black") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a black wool."));
						Main.getData().set("missions_and_users." + key + ".crafted_wool.black", true);
						Main.saveData();
						return;
					}
					
				}
				
			});
		}
	}
	
}