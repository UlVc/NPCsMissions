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

public class Mission14CraftedBlock implements Listener {

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void craftedConcretePowder(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 14 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();
					
					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("0") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.white") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a white concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.white", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("1") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.orange") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted an orange concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.orange", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("2") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.magenta") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a magenta concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.magenta", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("3") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.light_blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light blue concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.light_blue", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("4") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.yellow") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a yellow concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.yellow", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("5") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.lime") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a lime concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.lime", true);
						Main.saveData();
						return;
					}
	
					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("6") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.pink") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a pink concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.pink", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("7") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a gray concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.gray", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("8") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.light_gray") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a light gray concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.light_gray", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("9") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.cyan") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a cyan concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.cyan", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("10") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.purple") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a purple concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.purple", true);
						Main.saveData();
						return;
					}
			
					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("11") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.blue") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a blue concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.blue", true);
						Main.saveData();
						return;
					}
			
					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("12") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.brown") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a brown concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.brown", true);
						Main.saveData();
						return;
					}
	
					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("13") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.green") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a green concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.green", true);
						Main.saveData();
						return;
					}

					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("14") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.red") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a red concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.red", true);
						Main.saveData();
						return;
					}
					
					if (item.getType().equals(Material.CONCRETE_POWDER) && item.getData().toString().contains("15") &&
							file.getBoolean("missions_and_users." + key + ".crafted_concrete_powder.black") == false) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a black concrete powder."));
						Main.getData().set("missions_and_users." + key + ".crafted_concrete_powder.black", true);
						Main.saveData();
						return;
					}
					
				}
				
			});
		}
	}
	
}