package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
	
	public main m;
	
	public PlayerMove(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void moveEvent(final PlayerMoveEvent e) {
		
		/* Check if it's a player. */
		if(e.getPlayer() instanceof Player ) {
			
			/* Create a variable. */
			Player p = e.getPlayer();
			
			/* Check if player is in world 'world' */
			if(p.getWorld().getName() == "world") {
				
				/* Check if player steped on presure plate. */
				if(e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType().equals(Material.STONE_PLATE)) {
					
					/* Check if player hasn't already choosen a kit. */
					if(!m.selectedkit.contains(p)) {
						
						/* Tell them to select a kit because there badasses for not doing so :o */
						p.sendMessage(ChatColor.GOLD + "Before entering the arena, Please select a kit!");
						
						/* They haven't :o Teleport them to kit signs. */
						m.sksc.selectkitspawnteleport(p);
					} else {
						
						/* They did :D Randomly teleport them into the arena */
						m.sst.selectspawnid(p);
						m.selectedkit.remove(p);
						
					}
				}
				
			}

		}
		
	}

}
