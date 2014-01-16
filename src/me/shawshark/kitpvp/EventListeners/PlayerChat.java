package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerChat implements Listener { 
	
	public main m;
	
	public PlayerChat(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		
		PermissionUser user = PermissionsEx.getUser(p);
		
		String getprefix = user.getPrefix();
		String prefix = ChatColor.translateAlternateColorCodes('&', getprefix);
		
		String kills = m.getConfig().getString("server.players.player.kills." + p.getName());	
		String deaths = m.getConfig().getString("server.players.player.deaths." + p.getName());
		
		Bukkit.getServer().broadcastMessage(ChatColor.RED + "K: " + kills + " D: " + deaths + " " + prefix + " " + p.getName() + ": " + e.getMessage());
	}
}
