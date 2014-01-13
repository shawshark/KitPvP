package me.shawshark.kitpvp;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
public class main extends JavaPlugin implements Listener {
	
	public Boolean alltohub = true;
	public String onSpawnTeleport = ChatColor.GREEN + "You have been teleported to spawn!";
	
	@Override
	public void onEnable() {
		
		alltohub = true;
		
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		if(alltohub) {
			for(Player p : getServer().getOnlinePlayers())  {
				p.performCommand("server hub");
			} 
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player) ) {
			sender.sendMessage(ChatColor.RED + "Error: Commands can only be done by in game!");
		} else {
			
			Player p = (Player)sender;
			
			if(command.getName().equalsIgnoreCase("spawn")) {
				//make main 5 seconds.
				spawn(p);
			} 
			
			else if(command.getName().equalsIgnoreCase("setspawn")) {
				if(p.hasPermission("craftshark.setspawn")) {
					setspawn(p);
				} else {
					p.sendMessage(ChatColor.RED + "Error: You're not alowed to set the server spawn.");
				}
			}
			
			else if(command.getName().equalsIgnoreCase("alltohub")) {
				if(p.isOp()) {
					if(alltohub)  {
						alltohub = false;
						p.sendMessage(ChatColor.GREEN  + "You have toggle all to hub: off, When reloading players will not get redirected!");
					} else {
						alltohub = true;
						p.sendMessage(ChatColor.GREEN  + "You have toggle all to hub: on, When reloading players will get redirected!");
					}
				}
			}
		}
		return false;
	}
	
	public void spawn(Player p) {
		Location l = p.getLocation();
		int x = getConfig().getInt("server.spawn.x");
		int y = getConfig().getInt("server.spawn.y");
		int z = getConfig().getInt("server.spawn.z");
		float pitch = getConfig().getInt("server.spawn.pitch");
		float yaw = getConfig().getInt("server.spawn.yaw");
		World world = Bukkit.getWorld("world");
		l.setPitch(pitch);
		l.setWorld(world);
		l.setX(x);
		l.setY(y);
		l.setYaw(yaw);
		l.setZ(z);
		p.teleport(l);
		p.sendMessage(onSpawnTeleport);
	}
	
	public void setspawn(Player p) {
		Location l = p.getLocation();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		float pitch = l.getPitch();
		float yaw = l.getYaw();
		getConfig().set("server.spawn.x", x);
		getConfig().set("server.spawn.y", y);
		getConfig().set("server.spawn.z", z);
		getConfig().set("server.spawn.pitch", pitch);
		getConfig().set("server.spawn.yaw", yaw);
		saveConfig();
		spawn(p);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if(p.isDead()) {
				p.setHealth(20);
				p.setFoodLevel(20);
				p.setExp(0);
				
				p.getInventory().setHelmet(null);
				p.getInventory().setChestplate(null);
				p.getInventory().setLeggings(null);
				p.getInventory().setBoots(null);
				
				p.getInventory().clear();
				e.getDrops().clear();
			}
			if(p instanceof Player && e.getEntity().getKiller() instanceof Player) {
				Player pkiller = e.getEntity().getKiller();
				e.setDeathMessage(ChatColor.RED + ""+p.getName() + " was killed by " + e.getEntity().getKiller().getName());
				
				Random rand = new Random();
				int randomNumber = rand.nextInt(200);
				int i = Math.round(randomNumber);
				  
				pkiller.sendMessage(ChatColor.GOLD + 
						"You got " + ChatColor.RED  + i + ChatColor.GOLD + " credits for killing " +  p.getName());
				
				setcredits(pkiller, p, i);
				
				setscore(p, e.getEntity().getKiller());
				updatescoreboard(e.getEntity().getKiller());
				
				killstreak(pkiller, p);
				
			} else {
				e.setDeathMessage(ChatColor.RED + ""+p.getName() + " died!");
			}
			spawn(p);
			updatescoreboard(p);
			
		}
		
	}
	
	public HashMap<Player, Integer> killstreak = new HashMap<Player, Integer>();
	
	public void killstreak( Player pkiller  , Player p  ) {
		
		if(killstreak.containsKey(p)) {
			killstreak.remove(p);
			getServer().broadcastMessage(ChatColor.GREEN + "" + p.getName() + " Has been defeated by " + pkiller.getName());
		}
		
		if(!killstreak.containsKey(pkiller)) {
			killstreak.put(pkiller, 1);
		} else {
			int score = killstreak.get(pkiller);
			
			int finalscore = score + 1;
			
			killstreak.put(pkiller, finalscore);
			
			if(killstreak.get(pkiller) > 1) {
				getServer().broadcastMessage(ChatColor.GREEN + "" + pkiller.getName() + " Is on a kill streak of " + killstreak.get(pkiller));
				
				if(killstreak.get(pkiller) == 10) {
					getServer().broadcastMessage(ChatColor.GOLD + "" + pkiller.getName() + " Has reached a kill streak of 10! Come on stop them!");
				}
				
			}
			
		}
		updatescoreboard(p);
		updatescoreboard(pkiller);
	}
	 
	public void setcredits(Player p, Player killed, int credits) {
		
		int beforecheck = getConfig().getInt("server.players.player.credits." + p.getName());  
		int totalupscore = beforecheck + credits;
		
		getConfig().set("server.players.player.credits."  + p.getName(), totalupscore);	  
		saveConfig();
		
		p.sendMessage(ChatColor.GOLD + "You now have: " + ChatColor.RED + totalupscore + ChatColor.GOLD + " Credits!");
			  
		updatescoreboard(p); 
	}
	
	public void setscore(Player p, Player killer) {
		
		killer.sendMessage(ChatColor.GOLD + "You killed " + p.getName());
		
		p.sendMessage(ChatColor.GOLD + "" + killer.getName() + " killed you!");
		
		int beforecheck_kills = getConfig().getInt("server.players.player.kills" + p.getName());
		int add1_kills = beforecheck_kills + 1;
		getConfig().set("server.players.player.kills", add1_kills);
		
		int beforecheck_deaths = getConfig().getInt("server.players.player.deaths" + p.getName());
		int add1_deaths = beforecheck_deaths + 1;
		getConfig().set("server.players.player.kills", add1_deaths);
		
		saveConfig();
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!p.hasPlayedBefore()) {
			e.setJoinMessage(ChatColor.GREEN + "[Welcome] " + p.getName() + " To the server!");
			/**
			 * Change above messge.. only for debugging now...
			 */
			setdefaults(p);
		} else {
			e.setJoinMessage(null);
		}
		spawn(p);
		updateforeveryone();
	}
	
	public void setdefaults(Player p ) {
		getConfig().set("server.players.player.kills." + p.getName(), 0);
		getConfig().set("server.players.player.deaths." + p.getName(), 0);
		getConfig().set("server.players.player.credits." + p.getName(), 0);
		saveConfig();
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		updateforeveryone();
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		e.setLeaveMessage(null);
		updateforeveryone();
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		
		PermissionUser user = PermissionsEx.getUser(p);
		
		String getprefix = user.getPrefix();
		String prefix = ChatColor.translateAlternateColorCodes('&', getprefix);
		
		String kills = getConfig().getString("server.players.player.kills." + p.getName());	
		String deaths = getConfig().getString("server.players.player.deaths." + p.getName());
		
		Bukkit.getServer().broadcastMessage(ChatColor.RED + "K: " + kills + " D: " + deaths + " " + prefix + " " + p.getName() + ": " + e.getMessage());
	}
	
	public void updateforeveryone() {
		for(Player online : getServer().getOnlinePlayers()) {
			updatescoreboard(online);
		}
	}
	
	public void updatescoreboard(Player p) {
		  			  
		ScoreboardManager sbm = Bukkit.getScoreboardManager();
		Scoreboard sb = sbm.getNewScoreboard();
		Objective ob = sb.registerNewObjective("ststa", "dummy");
		ob.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Stats");
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score online = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Online"));
		Score kills = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "kills"));
		Score deaths = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Deaths"));
		Score credits = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Credits"));
		online.setScore(Bukkit.getServer().getOnlinePlayers().length);
		kills.setScore(getConfig().getInt("server.players.player.kills." + p.getName()));
		deaths.setScore(getConfig().getInt("server.players.player.deaths." + p.getName()));
		credits.setScore(getConfig().getInt("server.players.player.credits." + p.getName()));
		
		if(killstreak.containsKey(p)) {
			int score = killstreak.get(p);
			Score killstreak = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Kill Streak"));
			killstreak.setScore(score);
		}
		
		p.setScoreboard(sb);
	}
}
