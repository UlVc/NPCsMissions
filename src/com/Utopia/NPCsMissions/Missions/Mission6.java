package com.Utopia.NPCsMissions.Missions;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.Utopia.NPCsMissions.Main;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Mission6 implements Listener {
	
	public WorldGuardPlugin worldGuardPlugin;
	private ArrayList<Player> entered = new ArrayList<>();
    private String prefix = Main.getPlugin(Main.class).getConfig().getString("plugin-prefix");

    public Mission6(WorldGuardPlugin worldGuardPlugin) {
        this.worldGuardPlugin = worldGuardPlugin;
    }
	@EventHandler
	public void craftingReward(PlayerDeathEvent event) {
		enterRegion(event.getEntity().getKiller());
	}
	
	public void enterRegion(Player player) {
		
		FileConfiguration file = Main.getData();
		
		if (file.contains("missions_and_users")) {
			file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
				
				if (file.getInt("missions_and_users." + key + ".mission") == 6 &&
						player.toString().contains(file.getString("missions_and_users." + key + ".username"))) {
					
					LocalPlayer localPlayer = worldGuardPlugin.wrapPlayer(player);
			        Vector playerVector = localPlayer.getPosition();
			        RegionManager regionManager = worldGuardPlugin.getRegionManager(player.getWorld());
			        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector);

			        for (ProtectedRegion regions : applicableRegionSet)
			            if (regions.contains(playerVector))
			                if (!entered.contains(player))
			                    try {
			                    	System.out.println(regions.getId());
			                    	if (regions.getId().equalsIgnoreCase("warzone") && 
			                    			file.getInt("missions_and_users." + key + ".killed_players_warzone_counter") < 3) {
			                    		int count = file.getInt("missions_and_users." + key + ".killed_players_warzone_counter");
			                    		
			                    		file.set("missions_and_users." + key + ".killed_players_warzone_counter", count + 1);
			                    		Main.saveData();
			                    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + 
			                    				"You killed a player in the Warzone. &9[&a" + (count+1) + "&6/&a3&9]"));
			                    		continue;
			                    	}

			                    } catch (Exception e) {
			                        e.printStackTrace();
			                    }
					
					if (file.getInt("missions_and_users." + key + ".killed_players_warzone_counter") == 3) {
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix 
								+ "You've finished the kill 3 players in the Warzone mission!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "You've recieved &625000 XP &fand &6$500000&4!"));
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + 
								file.getString("missions_and_users." + key + ".username") + " 500000");
						
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "xp give " + 
								file.getString("missions_and_users." + key + ".username") + " 1000");
						
						file.set("missions_and_users." + key + ".mission", 7);
						file.set("missions_and_users." + key + ".killed_players_warzone_counter", null);
						Main.saveData();
						
						return;
						
					}
					
				}
				
			});
		}
		
    }

}
