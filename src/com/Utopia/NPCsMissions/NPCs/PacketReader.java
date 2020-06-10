package com.Utopia.NPCsMissions.NPCs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.Utopia.NPCsMissions.Main;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayInUseEntity;

public class PacketReader {

	Channel channel;
	public static Map<UUID, Channel> channels = new HashMap<UUID, Channel>();
	
	public void inject(Player player) {
		CraftPlayer craftPlayer = (CraftPlayer) player;
		channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
		channels.put(player.getUniqueId(), channel);
		
		if (channel.pipeline().get("PacketInjector") != null)
			return;
		
		channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>() {
			
			@Override
			protected void decode(ChannelHandlerContext channel, PacketPlayInUseEntity packet, List<Object> arg) throws Exception {
				arg.add(packet);
				readPacket(player, packet);
			}
			
		});
		
	}
	
	public void uninject(Player player) {
		Channel channel = getChannel(player);
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
	}
	
	Channel getChannel(Player player) {
        Channel channel = null;
        try {
            Class<?> cp = player.getClass();
            Method handle = cp.getMethod("getHandle");
            Object ep = handle.invoke(cp.cast(player));

            Field f = ep.getClass().getField("playerConnection");
            Field n = f.get(ep).getClass().getField("networkManager");
            Object x = null;
            x = n.get(f.get(ep));
            Field c = x.getClass().getField("channel");
            x = c.get(n.get(f.get(ep)));
            channel = (Channel) x;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return channel;
    }
	
	public void readPacket(Player player, Packet<?> packet) {
		
		if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
			
			if (getValue(packet, "action").toString().equalsIgnoreCase("ATTACK"))
				return;
			if (getValue(packet, "d").toString().equalsIgnoreCase("OFF_HAND"))
				return;
			if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT_AT"))
				return;
			
			int id = (int) getValue(packet, "a");
			
			if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
				for (EntityPlayer npc : NPC.getNPCs()) {
					if (npc.getId() == id) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {

							@Override
							public void run() {
								Bukkit.getPluginManager().callEvent(new RightClickNPC(player, npc));
							}
								
						}, 0);
					}
				}
			}
		}
	}
	
	private Object getValue(Object instance, String name) {
		Object result = null;
		
		try {
			
			Field field = instance.getClass().getDeclaredField(name);
			field.setAccessible(true);
			
			result = field.get(instance);
			
			field.setAccessible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}