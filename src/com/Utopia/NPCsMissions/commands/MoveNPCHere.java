package com.Utopia.NPCsMissions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Utopia.NPCsMissions.Main;
import com.Utopia.NPCsMissions.NPCs.ClickNPC;
import com.Utopia.NPCsMissions.NPCs.NPC;
import com.Utopia.NPCsMissions.NPCs.RightClickNPC;

public class MoveNPCHere implements CommandExecutor {
	
	private Main plugin;
	private ClickNPC npcClicked;
	
	public MoveNPCHere(Main plugin, ClickNPC npcClicked) {
		this.plugin = plugin;
		this.npcClicked = npcClicked;
		this.plugin.getCommand("moveNPCHere").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String noPermission = this.plugin.getConfig().getString("dont-have-permission");
		String npcMoved = this.plugin.getConfig().getString("npc-moved");	
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
			return true;
		}
		if (!(sender.hasPermission("NPCsMissions.movehere"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
			return true;
		}
		
		Player player = (Player) sender;
		RightClickNPC npcSelected = npcClicked.getNPCSelected();
		
		if (npcSelected == null) {
			player.sendMessage(ChatColor.RED + "Use /moveNPCHere after selecting the NPC.");
			return true;
		}	
		
		NPC.moveNPC(player, npcSelected);
		
		for (Player p : Bukkit.getOnlinePlayers())
			NPC.removeNPC(p, npcSelected.getNPC());
		
		npcClicked.resetNPCSelected();
		
		Main.loadNPC();
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcMoved));
		return true;
		
	}
	
}
