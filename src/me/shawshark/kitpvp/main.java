package me.shawshark.kitpvp;

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

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
public class main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
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
				e.setDeathMessage(ChatColor.RED + ""+p.getName() + " was killed by " + e.getEntity().getKiller().getName());
				setscore(p, e.getEntity().getKiller());
			} else {
				e.setDeathMessage(ChatColor.RED + ""+p.getName() + " died!");
			}
			spawn(p);
		}
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
	}
	
	public void setdefaults(Player p ) {
		getConfig().set("server.players.player.kills" + p.getName(), 0);
		getConfig().set("server.players.player.deaths" + p.getName(), 0);
		getConfig().set("server.players.player.credits" + p.getName(), 0);
		saveConfig();
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		e.setLeaveMessage(null);
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
}
