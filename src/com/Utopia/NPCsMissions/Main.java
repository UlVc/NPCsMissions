package com.Utopia.NPCsMissions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.Utopia.NPCsMissions.Events.ClickNPC;
import com.Utopia.NPCsMissions.Events.Join;
import com.Utopia.NPCsMissions.Events.Missions;

public class Main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n NPCs Missions has been enabled \n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
		this.getServer().getPluginManager().registerEvents(new Join(), this);
		this.getServer().getPluginManager().registerEvents(new ClickNPC(), this);
		this.getServer().getPluginManager().registerEvents(new Missions(), this);
		
		if (!Bukkit.getOnlinePlayers().isEmpty())
			for (Player player: Bukkit.getOnlinePlayers()) {
				PacketReader reader = new PacketReader();
				reader.inject(player);
			}
				
	}
	
	@Override
	public void onDisable() {
		for (Player player: Bukkit.getOnlinePlayers()) {
			PacketReader reader = new PacketReader();
			reader.uninject(player);
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("createnpc")) {
			if (!(sender instanceof Player))
				return true;
			
			Player player = (Player) sender;
			
			if (args.length == 0) {
				NPC.createNPC(player, player.getName());
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Created! :)"));
				return true;
			}
			NPC.createNPC(player, args[0]);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Created! :)"));
			return true;
		}
		
		if (label.equalsIgnoreCase("printnpc")) {
			NPC.printList();
			return true;
		}
		
		return false;
	}

}
