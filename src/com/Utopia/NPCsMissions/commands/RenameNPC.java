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
import com.Utopia.NPCsMissions.NPCs.PacketReader;
import com.Utopia.NPCsMissions.NPCs.RightClickNPC;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class RenameNPC implements CommandExecutor {
	
	private Main plugin;
	private ClickNPC npcClicked;
	
	public RenameNPC(Main plugin, ClickNPC npcClicked) {
		this.plugin = plugin;
		this.npcClicked = npcClicked;
		this.plugin.getCommand("renameNPC").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String noPermission = this.plugin.getConfig().getString("dont-have-permission");
		String npcRenamed = this.plugin.getConfig().getString("npc-renamed");
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry Console, you cannot use that command!");
			return true;
		}
		if (!(sender.hasPermission("NPCsMissions.rename"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission));
			return true;
		}
		Player player = (Player) sender;
		RightClickNPC npcSelected = npcClicked.getNPCSelected();
		
		if (args.length == 0 || npcSelected == null) {
			player.sendMessage(ChatColor.RED + "Use /renameNPC <new_name_of_the_npc> after selecting the NPC.");
			return true;
		}
		
		NPC.renameNPC(args[0], npcSelected);
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', npcRenamed));
		for (Player p : Bukkit.getOnlinePlayers()) {
			PacketReader reader = new PacketReader();
			reader.uninject(p);
			for (EntityPlayer npc : NPC.getNPCs())
				NPC.removeNPC(p, npc);
		}
		npcClicked.resetNPCSelected();
		this.plugin.loadNPC();
		return true;
		
	}
	
}
