package me.shawshark.kitpvp.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.shawshark.kitpvp.main;

public class Spawn {
	
	public main m;
	
	public Spawn(main m) {
		this.m= m;
	}
	
	public void spawn(Player p) {
		Location l = p.getLocation();
		int x = m.getConfig().getInt("server.spawn.x");
		int y = m.getConfig().getInt("server.spawn.y");
		int z = m.getConfig().getInt("server.spawn.z");
		float pitch = m.getConfig().getInt("server.spawn.pitch");
		float yaw = m.getConfig().getInt("server.spawn.yaw");
		World world = Bukkit.getWorld("world");
		l.setPitch(pitch);
		l.setWorld(world);
		l.setX(x);
		l.setY(y);
		l.setYaw(yaw);
		l.setZ(z);
		p.teleport(l);
		p.sendMessage(m.onSpawnTeleport);
	}
}
