package com.Utopia.NPCsMissions.Events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Utopia.NPCsMissions.Main;

public class ClickNPC implements Listener {
	
	private Main plugin;
	
	public ClickNPC(Main plugin) {
		this.plugin = plugin;
	}

    @EventHandler
    public void onClick(RightClickNPC event) {
        Player player = event.getPlayer();
        
        List<String> list = this.plugin.getConfig().getStringList("npc-message");
        System.out.println(list);
        
        for (String s : list) {
        	System.out.println(s);
        	if (s.contains("<name_of_the_npc>")) {
        		String replaced = s.replace("<name_of_the_npc>", event.getNPC().getName());
        		player.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
        	} else 
        		player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));      	
        }
    }

}
	