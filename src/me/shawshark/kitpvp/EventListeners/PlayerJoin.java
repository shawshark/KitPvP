package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

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
			
			m.sd.setdefaults(p);
		} else {
			e.setJoinMessage(ChatColor.BLUE + "+ " + e.getPlayer().getName() + " has joined.");
		}
		
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
	         
		
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		
		p.getInventory().clear();
		
		m.s.spawn(p);
		m.usb.updateforeveryone();
	}
}
