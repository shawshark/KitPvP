package me.shawshark.kitpvp.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.shawshark.kitpvp.main;

public class SetScore {
	
	public main m;
	
	public SetScore(main m) {
		this.m = m;
	}

	
	public void setscore(Player p, Player killer) {
		
		killer.sendMessage(ChatColor.GOLD + "You killed " + p.getName());
		
		p.sendMessage(ChatColor.GOLD + "" + killer.getName() + " killed you!");
		
		int beforecheck_kills = m.getConfig().getInt("server.players.player.kills" + p.getName());
		int add1_kills = beforecheck_kills + 1;
		m.getConfig().set("server.players.player.kills", add1_kills);
		
		int beforecheck_deaths = m.getConfig().getInt("server.players.player.deaths" + p.getName());
		int add1_deaths = beforecheck_deaths + 1;
		m.getConfig().set("server.players.player.kills", add1_deaths);
		
		m.saveConfig();
	}
	
}
