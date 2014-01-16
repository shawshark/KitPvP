package me.shawshark.kitpvp.Methods;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.shawshark.kitpvp.main;

public class KillStreak {
	
	public main m;
	
	public KillStreak(main m) {
		this.m = m;
	}
	
	public HashMap<Player, Integer> killstreak = new HashMap<Player, Integer>();
	
	public void killstreak( Player pkiller  , Player p  ) {
		
		if(killstreak.containsKey(p)) {
			killstreak.remove(p);
			m.getServer().broadcastMessage(ChatColor.GREEN + "" + p.getName() + " Has been defeated by " + pkiller.getName());
		}
		
		if(!killstreak.containsKey(pkiller)) {
			killstreak.put(pkiller, 1);
		} else {
			int score = killstreak.get(pkiller);
			
			int finalscore = score + 1;
			
			killstreak.put(pkiller, finalscore);
			
			if(killstreak.get(pkiller) > 1) {
				m.getServer().broadcastMessage(ChatColor.GREEN + "" + pkiller.getName() + " Is on a kill streak of " + killstreak.get(pkiller));
				
				if(killstreak.get(pkiller) == 10) {
					m.getServer().broadcastMessage(ChatColor.GOLD + "" + pkiller.getName() + " Has reached a kill streak of 10! Come on stop them!");
				}
			}
		}
		m.usb.updatescoreboard(p);
		m.usb.updatescoreboard(pkiller);
	}

}
