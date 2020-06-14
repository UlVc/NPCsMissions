package com.Utopia.NPCsMissions.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import com.Utopia.NPCsMissions.Main;

import net.md_5.bungee.api.ChatColor;

public class Mission5 implements Listener {

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void craftingReward(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 5 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();
					
					if (item.getType().equals(Material.OBSERVER) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.observer") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.observer", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted an observer."));
						
					}
					
					if (item.getType().equals(Material.DROPPER) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.dropper") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.dropper", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a dropper."));
						
					}
					
					if (item.getType().equals(Material.DISPENSER) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.dispenser") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.dispenser", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a dispenser."));
						
					}
					
					if (item.getType().equals(Material.REDSTONE_COMPARATOR) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.redstone_comparator") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.redstone_comparator", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a redstone comparator."));
						
					}
					
					if (item.getType().equals(Material.DIODE) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.redstone_repeater") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.redstone_repeater", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a redstone repeater."));
						
					}

					if (item.getType().equals(Material.RAILS) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.rail") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.rail", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a rail."));
						
					}
						
					if (item.getType().equals(Material.POWERED_RAIL) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.powered_rail") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.powered_rail", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a powered rail."));
						
					}
					
					if (item.getType().equals(Material.NOTE_BLOCK) && 
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.note_block") == false) {
						
						Main.getData().set("missions_and_users." + key + ".crafting.note_block", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a note block."));
						
					}
					
					if (Main.getData().getBoolean("missions_and_users." + key + ".crafting.observer") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.dropper") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.dispenser") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.redstone_comparator") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.redstone_repeater") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.rail") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.powered_rail") &&
							Main.getData().getBoolean("missions_and_users." + key + ".crafting.note_block")) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the crafting redstone mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6Random Legendary Enchant&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ae giverandombook " + 
								file.getString("missions_and_users." + key + ".username") + " Legendary 1");
						
						Main.getData().set("missions_and_users." + key + ".crafting", null);
						Main.getData().set("missions_and_users." + key + ".mission", 6);
						Main.saveData();
						return;
						
					}
					
				}
				
			});
		}
	}
	
}