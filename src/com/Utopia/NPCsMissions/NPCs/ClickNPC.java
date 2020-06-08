package com.Utopia.NPCsMissions.NPCs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Utopia.NPCsMissions.Main;

public class ClickNPC implements Listener {
	
	private Main plugin;
	private RightClickNPC npcSelected;
	private boolean detecter = true;
	
	public ClickNPC(Main plugin) {
		this.plugin = plugin;
	}

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onClick(RightClickNPC event) {
    	Player player = event.getPlayer();
    	
    	if (player.getItemInHand().getType().equals(Material.DIAMOND_AXE) && player.hasPermission("NPCsMissions.select")) {
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Selected! &6&l(&6&l" + event.getNPC().getName() + "&6&l)"));
        	npcSelected = event;
        } else {  
            List<String> list = this.plugin.getConfig().getStringList("npc-message");
            FileConfiguration file = Main.getData();
            
            for (String s : list)
            	if (s.contains("<name_of_the_npc>")) {
            		String replaced = s.replace("<name_of_the_npc>", event.getNPC().getName());
            		player.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
            	} else 
            		player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));  	
            
            if (file.contains("missions_and_users")) {
    			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
    				
    				if (event.getPlayer().toString().contains(file.getString("missions_and_users." + key + ".username"))) {
    					detecter = false;
    					return;
    				}  					
			
    			});
    			
    			if (detecter) {
    				int var = Main.getData().getConfigurationSection("missions_and_users").getKeys(false).size() + 1; // Only childs
        			
        			file.set("missions_and_users." + var + ".username", player.getName());
            		file.set("missions_and_users." + var + ".mission", 1);
            		
            		Main.saveData();
    			}	
        		
        		return;
        			
            } else {
    			file.set("missions_and_users." + 1 + ".username", player.getName());
        		file.set("missions_and_users." + 1 + ".mission", 1);
        		Main.saveData();
        		return;
    		}
            
        }
    }
    
    public RightClickNPC getNPCSelected() {
    	return this.npcSelected;
    }
    
    public void resetNPCSelected() {
    	this.npcSelected = null;
    }

}
	