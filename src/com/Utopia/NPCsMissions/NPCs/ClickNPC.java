package com.Utopia.NPCsMissions.NPCs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Utopia.NPCsMissions.Main;

public class ClickNPC implements Listener {
	
	private Main plugin;
	private RightClickNPC npcSelected;
	private boolean canHeDoMissionsAgain = true;
	private int missionPlayer = 0;
	
	public ClickNPC(Main plugin) {
		this.plugin = plugin;
	}

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onClick(RightClickNPC event) {
    	FileConfiguration file = Main.getData();
    	Player player = event.getPlayer();
    	
    	if (player.getItemInHand().getType().equals(Material.DIAMOND_AXE) && player.hasPermission("NPCsMissions.select")) {
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lNPC Selected! &6&l(&6&l" + event.getNPC().getName() + "&6&l)"));
        	this.npcSelected = event;
        } else {
        	
        	if (file.contains("missions_and_users"))
	        	file.getConfigurationSection("missions_and_users").getKeys(false).forEach(npcKey -> {
	        		if (file.getString("missions_and_users." + npcKey + ".username").equalsIgnoreCase(player.getName())) 
	        			canHeDoMissionsAgain = file.getInt("missions_and_users." + npcKey + ".can_he_do_missions_again") != -1;
        	});
        	
        	if (this.canHeDoMissionsAgain) {
        		checkIfItsTheFirstMission(player);
        		checkTheMissionOfAPlayer(player);
    			
    			int npcX = (int) event.getNPC().locX;
            	int npcY = (int) event.getNPC().locY;
            	int npcZ = (int) event.getNPC().locZ;
            	
            	file.getConfigurationSection("data").getKeys(false).forEach(npcKey -> {
	    			
	    			if (file.getInt("data." + npcKey + ".x") == npcX && file.getInt("data." + npcKey + ".y") == npcY && 
	    					file.getInt("data." + npcKey + ".z") == npcZ) {
	
	        			int numberOfMission = file.getInt("data." + npcKey + ".assigned_number_of_mission");

	        			if (missionPlayer == 0 && numberOfMission == 1) {
	        				printMessages(event, 1);
	        				file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
	        		    		if (player.toString().contains(file.getString("missions_and_users." + key + ".username"))) {
	        		    			file.set("missions_and_users." + key + ".mission", 1);
	        		    			Main.saveData();
	        		    			return;
	        		    		}
	        				});
	        			}
	        			else if (missionPlayer == numberOfMission)
	        				printMessages(event, numberOfMission);
	        			else if (numberOfMission == 0)
	        				printMessages(event, 0);
	        			else {
	        				player.sendMessage(ChatColor.translateAlternateColorCodes('&', event.getNPC().getName() + " &6&l>> &fI'm not the guy you are looking for."));
	        				return;
	        			}
	        			return;
	    			}
	    			
	    		}); 
        	}
	        	       
        	else
        		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "You can't do missions again."));
            
        }
    }
    
    private void printMessages(RightClickNPC event, int numberOfMission) {
    	List<String> list = this.plugin.getConfig().getStringList("mission-" + numberOfMission + "-display-message");
		
		for (String s : list)
        	if (s.contains("<name_of_the_npc>")) {
        		String replaced = s.replace("<name_of_the_npc>", event.getNPC().getName());
        		event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', replaced));
        	} else 
        		event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }
    
    private void checkTheMissionOfAPlayer(Player player) {
    	
    	FileConfiguration file = Main.getData();
    	
    	if (file.contains("missions_and_users"))
	    	file.getConfigurationSection("missions_and_users").getKeys(false).forEach(key -> {
	    		if (player.toString().contains(file.getString("missions_and_users." + key + ".username"))) {
	    			missionPlayer = file.getInt("missions_and_users." + key + ".mission");
	    			return;
	    		}
			});

    }
    
    private void checkIfItsTheFirstMission(Player player) {
    	FileConfiguration file = Main.getData();
    	
    	if (file.contains("missions_and_users")) {
    		boolean detecter = true;
    		int var = Main.getData().getConfigurationSection("missions_and_users").getKeys(false).size();
    		
    		for (int i = 1; i <= var; i++) {
    			if (player.getName().equalsIgnoreCase(file.getString("missions_and_users." + i + ".username"))) {
					detecter = false;
					return;
				}
    		}
    		
    		if (detecter) {
    			file.set("missions_and_users." + (var+1) + ".username", player.getName());
        		file.set("missions_and_users." + (var+1) + ".mission", 0);
        		
        		Main.saveData();
        		return;
    		}
    			
        } else {
			file.set("missions_and_users." + 1 + ".username", player.getName());
    		file.set("missions_and_users." + 1 + ".mission", 0);
    		Main.saveData();
    		return;
		}
    }
    
    public RightClickNPC getNPCSelected() {
    	return this.npcSelected;
    }
    
    public void resetNPCSelected() {
    	this.npcSelected = null;
    }

}
	