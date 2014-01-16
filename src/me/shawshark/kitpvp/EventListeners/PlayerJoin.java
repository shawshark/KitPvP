package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
	
	public main m;
	
	public PlayerJoin(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!p.hasPlayedBefore()) {
			e.setJoinMessage(ChatColor.GREEN + "[Welcome] " + p.getName() + " To the server!");
			/**
			 * Change above messge.. only for debugging now...
			 */
			m.sd.setdefaults(p);
		} else {
			e.setJoinMessage(null);
		}
		m.s.spawn(p);
		m.usb.updateforeveryone();
	}
}
