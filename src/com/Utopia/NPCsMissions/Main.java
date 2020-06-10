package com.Utopia.NPCsMissions;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.Utopia.NPCsMissions.Events.Join;
import com.Utopia.NPCsMissions.Files.DataManager;
import com.Utopia.NPCsMissions.Missions.Mission10CraftedBlock;
import com.Utopia.NPCsMissions.Missions.Mission10PlacedBlock;
import com.Utopia.NPCsMissions.Missions.Mission11;
import com.Utopia.NPCsMissions.Missions.Mission12;
import com.Utopia.NPCsMissions.Missions.Mission13;
import com.Utopia.NPCsMissions.Missions.Mission14CraftedBlock;
import com.Utopia.NPCsMissions.Missions.Mission14PlacedBlock;
import com.Utopia.NPCsMissions.Missions.Mission15;
import com.Utopia.NPCsMissions.Missions.Mission2;
import com.Utopia.NPCsMissions.Missions.Mission3;
import com.Utopia.NPCsMissions.Missions.Mission4;
import com.Utopia.NPCsMissions.Missions.Mission5;
import com.Utopia.NPCsMissions.Missions.Mission6;
import com.Utopia.NPCsMissions.Missions.Mission7;
import com.Utopia.NPCsMissions.Missions.Mission8;
import com.Utopia.NPCsMissions.Missions.Mission9;
import com.Utopia.NPCsMissions.NPCs.ClickNPC;
import com.Utopia.NPCsMissions.NPCs.NPC;
import com.Utopia.NPCsMissions.NPCs.PacketReader;
import com.Utopia.NPCsMissions.commands.AssignNumberOfMission;
import com.Utopia.NPCsMissions.commands.CreateNPC;
import com.Utopia.NPCsMissions.commands.MoveNPCHere;
import com.Utopia.NPCsMissions.commands.RemoveNPC;
import com.Utopia.NPCsMissions.commands.RenameNPC;
import com.Utopia.NPCsMissions.commands.SkipMission;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import net.minecraft.server.v1_12_R1.EntityPlayer;

public class Main extends JavaPlugin implements Listener{
	
	public WorldGuardPlugin worldGuardPlugin;
	public static DataManager data;
	private ClickNPC npcClicked = new ClickNPC(this);
	
	//private Economy econ;

	@Override
	public void onEnable() {
		
		/*if (!setupEconomy()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }*/

		this.saveDefaultConfig();
		
		data = new DataManager(this);
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + 
				"\n\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n NPCs Missions has been enabled \n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");
		
		this.getServer().getPluginManager().registerEvents(new Join(), this);
		this.getServer().getPluginManager().registerEvents(npcClicked, this);
		//this.getServer().getPluginManager().registerEvents(new Mission1(econ), this);
		this.getServer().getPluginManager().registerEvents(new Mission2(getWorldGuard()), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission3(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission4(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission5(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission6(getWorldGuard()), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission7(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission8(getWorldGuard()), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission9(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission10CraftedBlock(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission10PlacedBlock(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission11(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission12(), this); //done
		this.getServer().getPluginManager().registerEvents(new Mission13(), this);//done
		this.getServer().getPluginManager().registerEvents(new Mission14CraftedBlock(), this);//done
		this.getServer().getPluginManager().registerEvents(new Mission14PlacedBlock(), this);//done
		this.getServer().getPluginManager().registerEvents(new Mission15(), this); //done
		
		new AssignNumberOfMission(this, npcClicked);
		new CreateNPC(this);
		new MoveNPCHere(this, npcClicked);
		new RemoveNPC(this, npcClicked);
		new RenameNPC(this, npcClicked);
		new SkipMission(this);

		if (data.getConfig().contains("data"))
			loadNPC();
		
		reInjectPlayers();
	}
	
	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			PacketReader reader = new PacketReader();
			reader.uninject(player);
			for (EntityPlayer npc : NPC.getNPCs())
				NPC.removeNPC(player, npc);
		}
	}
	
	public static FileConfiguration getData() {
		return data.getConfig();
	}
	
	public static void saveData() {
		data.saveConfig();
	}
	
	public void loadNPC() {
		FileConfiguration file = data.getConfig();
		file.getConfigurationSection("data").getKeys(false).forEach(npc -> {
			Location location = new Location(Bukkit.getWorld(file.getString("data." + npc + ".world")),
					file.getInt("data." + npc + ".x"), file.getInt("data." + npc + ".y"), file.getInt("data." + npc + ".z"));
			location.setPitch((float) file.getDouble("data." + npc + ".p"));
			location.setYaw((float) file.getDouble("data." + npc + ".yaw"));
			
			String name = file.getString("data." + npc + ".name");
			GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.GOLD + "" + ChatColor.BOLD + name);
			gameProfile.getProperties().put("textures",new Property("textures", file.getString("data." + npc + ".text"),
					file.getString("data." + npc + ".signature")));
			
			NPC.loadNPC(location, gameProfile);
		});
	}
	
	public void reInjectPlayers() {
		if (!Bukkit.getOnlinePlayers().isEmpty())
			for (Player p :Bukkit.getOnlinePlayers()) {
				PacketReader reader = new PacketReader();
				reader.inject(p);
			}
	}
	
	public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }
	
	/*private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Economy getEconomy() {
        return econ;
    }*/

}
