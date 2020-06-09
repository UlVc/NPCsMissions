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

public class Mission3 implements Listener {

	private String prefix = (ChatColor.GREEN + "NPC's Missions >> ");

	@EventHandler
	public void craftingReward(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 3 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();
					
					if (item.getType().equals(Material.LEATHER_LEGGINGS) && 
							file.getBoolean("missions_and_users." + key + ".crafting.leather_leggings") == false) {
						
						file.set("missions_and_users." + key + ".crafting.leather_leggings", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted leggings of leather."));
						
					}
					
					if (item.getType().equals(Material.IRON_HELMET) && 
							file.getBoolean("missions_and_users." + key + ".crafting.iron_helmet") == false) {
						
						file.set("missions_and_users." + key + ".crafting.iron_helmet", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted an iron helmet."));
						
					}
					
					if (item.getType().equals(Material.GOLD_BOOTS) && 
							file.getBoolean("missions_and_users." + key + ".crafting.gold_boots") == false) {
						
						file.set("missions_and_users." + key + ".crafting.gold_boots", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted boots of gold."));
						
					}
					
					if (item.getType().equals(Material.DIAMOND_CHESTPLATE) && 
							file.getBoolean("missions_and_users." + key + ".crafting.diamond_chestplate") == false) {
						
						file.set("missions_and_users." + key + ".crafting.diamond_chestplate", true);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted a diamond chestplate."));
						
					}
					
					if (file.getBoolean("missions_and_users." + key + ".crafting.leather_leggings") &&
							file.getBoolean("missions_and_users." + key + ".crafting.iron_helmet") &&
							file.getBoolean("missions_and_users." + key + ".crafting.gold_boots") &&
							file.getBoolean("missions_and_users." + key + ".crafting.diamond_chestplate")) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You finished the crafting mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &61 Random Elite Enchant&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ae giverandombook " + 
								file.getString("missions_and_users." + key + ".username") + " Elite 1");
						
						file.set("missions_and_users." + key + ".crafting", null);
						file.set("missions_and_users." + key + ".mission", 4);
						Main.saveData();
						return;
						
					}
					
				}
				
			});
		}
	}
	
}