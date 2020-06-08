package com.Utopia.NPCsMissions.NPC;

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
    				System.out.println(key);
    				
    				if (file.getString("missions_and_users." + key + ".username").equalsIgnoreCase(event.getPlayer().toString())) 
    					return;
			
    			});
    			
    			return;
            }
            
            int var = 1;
    		if (Main.getData().contains("missions_and_users"))
    			var = Main.getData().getConfigurationSection("missions_and_users").getKeys(false).size() + 1; // Only childs
    		
    		Main.getData().set("missions_and_users." + var + ".username", player.getName());
    		Main.getData().set("missions_and_users." + var + ".mission", 1);
    		Main.saveData();
        }
    }
    
    public RightClickNPC getNPCSelected() {
    	return this.npcSelected;
    }
    
    public void resetNPCSelected() {
    	this.npcSelected = null;
    }

}
	