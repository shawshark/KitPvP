package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKick implements Listener {
	
	public main m;
	
	public PlayerKick(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		e.setLeaveMessage(null);
		m.usb.updateforeveryone();
	}
}
