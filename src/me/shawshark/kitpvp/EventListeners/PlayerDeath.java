package me.shawshark.kitpvp.EventListeners;

import java.util.Random;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
	
	public main m;
	
	public PlayerDeath(main m) {
		this.m = m;
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
				
				/* Get a number between 0 and 200 and give them that number in credits. */
				Random rand = new Random();
				int randomNumber = rand.nextInt(200);
				int i = Math.round(randomNumber);
				  
				pkiller.sendMessage(ChatColor.GOLD + 
						"You got " + ChatColor.RED  + i + ChatColor.GOLD + " credits for killing " +  p.getName());
				
				m.sc.setcredits(pkiller, p, i);
				
				m.ss.setscore(p, e.getEntity().getKiller());
				m.usb.updatescoreboard(e.getEntity().getKiller());
				
				m.ks.killstreak(pkiller, p);
				
			} else {
				e.setDeathMessage(ChatColor.RED + ""+p.getName() + " died!");
			}
			m.s.spawn(p);
			m.usb.updatescoreboard(p);
		}
	}
}
