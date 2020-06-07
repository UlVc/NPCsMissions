package com.Utopia.NPCsMissions.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClickNPC implements Listener{

    @EventHandler
    public void onClick(RightClickNPC event) {
        Player player = event.getPlayer();
		player.sendMessage("Hey! I am " + event.getNPC().getName() + ChatColor.WHITE + ", here is your mission:");
		player.sendMessage("Mission sample");
		player.sendMessage("Good luck.");
    }

}
	