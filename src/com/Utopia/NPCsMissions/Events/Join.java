package com.Utopia.NPCsMissions.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.Utopia.NPCsMissions.NPC;
import com.Utopia.NPCsMissions.PacketReader;

public class Join implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		PacketReader reader = new PacketReader();
		reader.inject(event.getPlayer());	
		
		if (NPC.getNPCs() == null || NPC.getNPCs().isEmpty())
			return;
		
		NPC.addJoinNPCPacket(event.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		PacketReader reader = new PacketReader();
		reader.uninject(event.getPlayer());
	}
}