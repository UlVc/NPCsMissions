package com.Utopia.NPCsMissions.Events;

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
        for (String s : this.plugin.getConfig().getStringList("npc-message")) {
        	try {
            	String replaced = s.replace("<name_of_the_npc>", event.getNPC().getName());
        		player.sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
            } catch(Exception e) {
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }
    }

}
	