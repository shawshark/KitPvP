package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
	
	public main m;
	
	public PlayerQuit(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.BLUE + "- " + e.getPlayer().getName() + " has left.");
		m.usb.updateforeveryone();
	}
}
