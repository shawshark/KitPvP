package me.shawshark.kitpvp.Methods;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.shawshark.kitpvp.main;

public class SetSpawn {
	
	public main m;
	
	public SetSpawn(main m) {
		this.m = m;
	}
	
	public void setspawn(Player p) {
		Location l = p.getLocation();
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		float pitch = l.getPitch();
		float yaw = l.getYaw();
		m.getConfig().set("server.spawn.x", x);
		m.getConfig().set("server.spawn.y", y);
		m.getConfig().set("server.spawn.z", z);
		m.getConfig().set("server.spawn.pitch", pitch);
		m.getConfig().set("server.spawn.yaw", yaw);
		m.saveConfig();
		m.s.spawn(p);
	}
}
