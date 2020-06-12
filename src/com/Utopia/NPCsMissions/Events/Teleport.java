package com.Utopia.NPCsMissions.Events;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.Utopia.NPCsMissions.Main;
import com.Utopia.NPCsMissions.NPCs.NPC;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class Teleport implements Listener {

    @EventHandler
    public void teleport(PlayerTeleportEvent event) {
    	
    	if (NPC.getNPCs() == null || NPC.getNPCs().isEmpty())
            return;
    	
    	Player player = event.getPlayer();
    	
    	new BukkitRunnable() {
            @Override
        public void run() {
            	for (EntityPlayer npc : NPC.getNPCs()) {
        			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        			connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        			connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
        			new BukkitRunnable() {
        	            @Override
        	        public void run() {
        	                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
        	            }
        	        }.runTaskLater(Main.getPlugin(Main.class), 40);
        		}
            	
            }
        }.runTaskLater(Main.getPlugin(Main.class), 3);

    }
    
}