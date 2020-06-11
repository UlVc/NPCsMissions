package com.Utopia.NPCsMissions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Utopia.NPCsMissions.Main;
import com.Utopia.NPCsMissions.NPCs.ClickNPC;
import com.Utopia.NPCsMissions.NPCs.NPC;
import com.Utopia.NPCsMissions.NPCs.RightClickNPC;

public class AssignNumberOfMission implements CommandExecutor {
	
	private Main plugin;
	private ClickNPC npcClicked;
	
	public AssignNumberOfMission(Main plugin, ClickNPC npcClicked) {
		this.plugin = plugin;
		this.npcClicked = npcClicked;
		this.plugin.getCommand("assignNumberOfMission").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String noPermission = this.plugin.getConfig().getString("dont-have-permission");
		String npcAssignedMission = this.plugin.getConfig().getString("npc-assigned-mission");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
			return true;
		}
		
		if (!(sender.hasPermission("NPCsMissions.assign_number_of_mission"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
			return true;
		}
		
		Player player = (Player) sender;
		RightClickNPC npcSelected = npcClicked.getNPCSelected();
		
		if (npcSelected == null) {
			player.sendMessage(ChatColor.RED + "Use /assignNumberOfMission <number of the mission> after selecting the NPC.");
			return true;
		}
		
		if (args.length != 1) {
			player.sendMessage(ChatColor.RED + "Use /assignNumberOfMission <number of the mission>");
			return true;
		}
		
		try {
			int mission = Integer.parseInt(args[0]);
			
			if (mission > 15) {
				player.sendMessage(ChatColor.RED + "Use /assignNumberOfMission <number of the mission> where <number of the mission> is a number from 1 to 15");
				return true;
			}
			
			NPC.assignNumberOfMission(npcSelected, mission);
			npcClicked.resetNPCSelected();
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcAssignedMission));
			
			return true;
		} catch (Exception e) {
			player.sendMessage(ChatColor.RED + "Use /assignNumberOfMission <number of the mission> where <number of the mission> is a number! (not decimal)");
			return true;
		}
		
	}
	
}
