package com.Utopia.NPCsMissions.Missions;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.Utopia.NPCsMissions.Main;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Mission2 implements Listener {
	
	public WorldGuardPlugin worldGuardPlugin;
	private ArrayList<Player> entered = new ArrayList<>();
	private boolean detecter = false;
    private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

    public Mission2(WorldGuardPlugin worldGuardPlugin) {
        this.worldGuardPlugin = worldGuardPlugin;
    }
	
	@EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        enterRegion(player);
    }
	
	public void enterRegion(Player player) {
		
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 2 &&
						player.toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					LocalPlayer localPlayer = worldGuardPlugin.wrapPlayer(player);
			        Vector playerVector = localPlayer.getPosition();
			        RegionManager regionManager = worldGuardPlugin.getRegionManager(player.getWorld());
			        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);

			        for (ProtectedRegion regions : applicableRegionSet)
			            if (regions.contains(playerVector))
			                if (!entered.contains(player))
			                    try {
			                    	if (regions.getId().equalsIgnoreCase("Arena")) {
			                    		detecter = true;
			                    		continue;
			                    	}

			                    } catch (Exception e) {
			                        e.printStackTrace();
			                    }
					
					if (detecter) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix 
								+ "You've finished the visit the Boss Arena mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &6$300000&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
								file.getString("missions_and_users." + key + ".username") + " 300000");
						
						file.set("missions_and_users." + key + ".mission", 3);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
		
    }
    
}
