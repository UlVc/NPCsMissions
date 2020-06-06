package com.Utopia.NPCsMissions.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class RightClickNPC extends Event implements Cancellable {
	
	private final Player player;
	private final EntityPlayer npc;
	private boolean isCancelled;
	//Must have:
	private static final HandlerList HANDLERS = new HandlerList();
	
	public RightClickNPC(Player player, EntityPlayer npc) {
		this.player = player;
		this.npc = npc;
	}
	
	//Must have:
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	//Must have:
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public EntityPlayer getNPC() {
		return npc;
	}
	
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean arg) {
		isCancelled = arg;
	}

}