package com.Utopia.NPCsMissions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Utopia.NPCsMissions.Main;
import com.Utopia.NPCsMissions.NPCs.NPC;

public class CreateNPC implements CommandExecutor {
	
	private Main plugin;
	
	public CreateNPC(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("createNPC").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String noPermission = this.plugin.getConfig().getString("dont-have-permission");
		String npcCreated = this.plugin.getConfig().getString("npc-created");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lSorry Console, you cannot use that command!"));
			return true;
		}
		if (!(sender.hasPermission("NPCsMissions.create"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			String name = player.getName();
			if (name.length() > 15) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lI can't soport 16 characters or more yet!"));
				return false;
			}
			NPC.createNPC(player, name, "&6&l");
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcCreated));
			return true;
		}
		
		if (args[0].length() > 15) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lI can't soport 16 characters or more yet!"));
			return false;
		}
		
		if (args.length < 2) {
			NPC.createNPC(player, args[0], "&6&l");
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcCreated));
			return true;
		}
		
		NPC.createNPC(player, args[0], args[1]);
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcCreated));
		return true;
	}
	
}
