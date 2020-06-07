package com.Utopia.NPCsMissions.Events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
    	
    	if (player.getItemInHand().getType().equals(Material.DIAMOND_AXE)) {
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Selected! &6&l(&6&l" + event.getNPC().getName() + "&6&l)"));
        	npcSelected = event;
        } else {  
            List<String> list = this.plugin.getConfig().getStringList("npc-message");
            
            for (String s : list)
            	if (s.contains("<name_of_the_npc>")) {
            		String replaced = s.replace("<name_of_the_npc>", event.getNPC().getName());
            		player.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
            	} else 
            		player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));      	
        }
    }
    
    public RightClickNPC getNPCSelected() {
    	return this.npcSelected;
    }
    
    public void resetNPCSelected() {
    	this.npcSelected = null;
    }

}
	