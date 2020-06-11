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

public class RemoveNPC implements CommandExecutor {
	
	private Main plugin;
	private ClickNPC npcClicked;
	
	public RemoveNPC(Main plugin, ClickNPC npcClicked) {
		this.plugin = plugin;
		this.npcClicked = npcClicked;
		this.plugin.getCommand("removeNPC").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String noPermission = this.plugin.getConfig().getString("dont-have-permission");
		String npcRemoved = this.plugin.getConfig().getString("npc-removed");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
			return true;
		}
		
		if (!(sender.hasPermission("NPCsMissions.remove"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
			return true;
		}
		
		Player player = (Player) sender;
		RightClickNPC npcSelected = npcClicked.getNPCSelected();
		
		if (npcSelected == null) {
			player.sendMessage(ChatColor.RED + "Use /removeNPC after selecting the NPC.");
			return true;
		}
		
		for (Player p : Bukkit.getOnlinePlayers())
			NPC.removeNPC(p, npcSelected.getNPC());
		
		NPC.removeNPC(npcSelected);

		npcClicked.resetNPCSelected();
		Main.loadNPC();
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcRemoved));
		
		return true;
		
	}
	
}
