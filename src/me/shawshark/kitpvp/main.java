package me.shawshark.kitpvp;

import java.util.ArrayList;
import java.util.List;

import me.shawshark.kitpvp.EventListeners.PlayerChat;
import me.shawshark.kitpvp.EventListeners.PlayerCommand;
import me.shawshark.kitpvp.EventListeners.PlayerDeath;
import me.shawshark.kitpvp.EventListeners.PlayerJoin;
import me.shawshark.kitpvp.EventListeners.PlayerKick;
import me.shawshark.kitpvp.EventListeners.PlayerMove;
import me.shawshark.kitpvp.EventListeners.PlayerQuit;
import me.shawshark.kitpvp.Methods.KillStreak;
import me.shawshark.kitpvp.Methods.SelectSpawnTeleport;
import me.shawshark.kitpvp.Methods.SetCredits;
import me.shawshark.kitpvp.Methods.SetDefaults;
import me.shawshark.kitpvp.Methods.SetScore;
import me.shawshark.kitpvp.Methods.SetSpawn;
import me.shawshark.kitpvp.Methods.Spawn;
import me.shawshark.kitpvp.Methods.UpdateScoreboard;
import me.shawshark.kitpvp.commands.AlltohubCommand;
import me.shawshark.kitpvp.commands.SelectkitspawnCommand;
import me.shawshark.kitpvp.commands.SetSpawnCommand;
import me.shawshark.kitpvp.commands.SetarenaspawnsCommand;
import me.shawshark.kitpvp.commands.SpawnCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	public Boolean alltohub = true;
	public KillStreak ks = new KillStreak(this);
	public SetCredits sc = new SetCredits(this);
	public SetDefaults sd = new SetDefaults(this);
	public SetScore ss = new SetScore(this);
	public SetSpawn sets = new SetSpawn(this);
	public Spawn s = new Spawn(this);
	public SelectkitspawnCommand sksc = new SelectkitspawnCommand(this);
	public List<Player> selectedkit = new ArrayList<Player>();
	public SelectSpawnTeleport sst = new SelectSpawnTeleport(this);
	public UpdateScoreboard usb = new UpdateScoreboard(this);
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		registerevents();
		registercommands();
	}
	
	@Override
	public void onDisable() {
		if(alltohub) {
			for(Player p : getServer().getOnlinePlayers())  {
				p.performCommand("server hub");
			} 
		}
	}
	
	public void registerevents() {
		Bukkit.getPluginManager().registerEvents(new PlayerChat(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeath(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerKick(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMove(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCommand(this), this);
		System.out.print("[Kit-PvP] Registered Events!");
	}
	
	public void registercommands() {
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("alltohub").setExecutor(new AlltohubCommand(this));
		getCommand("Selectkitspawn").setExecutor(new SelectkitspawnCommand(this));
		getCommand("Setarenaspawns").setExecutor(new SetarenaspawnsCommand(this));
		System.out.print("[Kit-PvP] Registered Commands!");
	}
}
