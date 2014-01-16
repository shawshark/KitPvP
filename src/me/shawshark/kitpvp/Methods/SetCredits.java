package me.shawshark.kitpvp.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.shawshark.kitpvp.main;

public class SetCredits {
	
	public main m;
	
	public SetCredits(main m) {
		this.m = m;
	}
	
	public void setcredits(Player p, Player killed, int credits) {
		
		int beforecheck = m.getConfig().getInt("server.players.player.credits." + p.getName());  
		int totalupscore = beforecheck + credits;
		
		m.getConfig().set("server.players.player.credits."  + p.getName(), totalupscore);	  
		m.saveConfig();
		
		p.sendMessage(ChatColor.GOLD + "You now have: " + ChatColor.RED + totalupscore + ChatColor.GOLD + " Credits!");
			  
		m.usb.updatescoreboard(p); 
	}

}
