package me.shawshark.kitpvp.Methods;

import org.bukkit.entity.Player;

import me.shawshark.kitpvp.main;

public class SetDefaults {
	
	public main m;

	public SetDefaults(main m) {
		this.m = m;
	}
	
	public void setdefaults(Player p ) {
		m.getConfig().set("server.players.player.kills." + p.getName(), 0);
		m.getConfig().set("server.players.player.deaths." + p.getName(), 0);
		m.getConfig().set("server.players.player.credits." + p.getName(), 0);
		m.saveConfig();
		
		System.out.print(p.getName() + " Has been added to the config!");
	}
}
