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

public class Mission9 implements Listener {

	private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

	@EventHandler
	public void craftingReward(CraftItemEvent event) {
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 9 &&
						event.getWhoClicked().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					Player player = (Player) event.getWhoClicked();
					ItemStack item = event.getCurrentItem();				
					int count = file.getInt("missions_and_users." + key + ".crafted_fireworks");
					int fireworksCrafted = getAmountCraftItem(Material.FIREWORK, event);
					
					if (item.getType().equals(Material.FIREWORK) && count < 64) {		
						
						Main.getData().set("missions_and_users." + key + ".crafted_fireworks", count + fireworksCrafted);
						Main.saveData();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You crafted fireworks. &9[&a" + (count+fireworksCrafted) + "&6/&a64&9]"));
						
					}
					
					if (Main.getData().getInt("missions_and_users." + key + ".crafted_fireworks") > 63) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've finished the craft 64 fireworks mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved a &6zombie spawner&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "spawner give zombie " + 
								file.getString("missions_and_users." + key + ".username"));
						
						file.set("missions_and_users." + key + ".crafted_fireworks", null);
						file.set("missions_and_users." + key + ".mission", 10);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
	}
	
	private int getAmountCraftItem(Material m, CraftItemEvent e){

        if (e.isCancelled()) return 0;
        if(!e.getCurrentItem().getType().equals(m)) return 0;
        
        int amount = 3;
        
        if (e.isShiftClick()) {
        	
            int max = e.getInventory().getMaxStackSize();
            org.bukkit.inventory.ItemStack[] matrix = e.getInventory().getMatrix();
            
            for (org.bukkit.inventory.ItemStack is: matrix) {
            	
                if (is == null || is.getType().equals(Material.AIR))
                    continue;
                
                int tmp = is.getAmount();
                
                if (tmp < max && tmp > 0) 
                	max = tmp;
            }
            amount *= max;
        }
        
        return amount;
    }
	
}