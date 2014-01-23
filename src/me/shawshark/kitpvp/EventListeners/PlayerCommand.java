package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerCommand implements Listener {
	
	public main m;
	
	public PlayerCommand(main m) {
		this.m = m;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void commandevent(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		
		/* Check if command was '/kit' */
		if (e.getMessage().startsWith("/kit")) {

			/* Players are added to this list when they have clicked the sign, they are sortly removed from it after they have got there kit. */
			if(!m.interwithsign.contains(p)) {
				
				/* Tell them that they can only purchase kits in one place. */
				p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "Choosing kits can only be done at the sign wall!, Type /spawn to return to spawn.");
				
				/* Cancel the event. */
				e.setCancelled(true);
				
			} else {
				
				/* Check if player is starting on stone. */
				if(p.getLocation().subtract(0,1,0).getBlock().getType() == Material.STONE) {
					
					/* Check to see if player is in world 'world' */
					if(!(p.getWorld() == Bukkit.getWorld("world"))) {
						
						/* Send them a message to say they can't choose a kit in a arena. */
						p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You can't choose a kit while you are in a arena!");
						
						/* Cancel the event. */
						e.setCancelled(true);
					} else {
						
						/* Check to see if player has already choosen there kit. */
						if(m.selectedkit.contains(p)) {
							
							/* They have, cancel the event. */
							e.setCancelled(true);
							
							/* Tell them that why already have a kit.*/
							p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You have already selected a kit, Please enter the arena.");
						} else {
							
							/* PermisisonsEx string. */
							PermissionUser user = PermissionsEx.getUser(p);
							
							/* Get players credits */
							int credits = m.getConfig().getInt("server.players.player.credits." + p.getName());
							
							/* Check if command was /kit donor. */
							if(e.getMessage().startsWith("/kit donor")) {
								
								/* Check if player is in group 'donor' */
								if(user.inGroup("donor")) {
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player isn't in group 'donor' */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You aren't in the donor group!");
								}
							}
							
							/* Check if command was /kit supporter. */
							else if(e.getMessage().startsWith("/kit supporter")) {
								
								/* Check if player is in group 'supporter' */
								if(user.inGroup("supporter")) {
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player isn't in group 'donor' */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You aren't in the supporter group!");
								}
							}
							
							/* Check if command was /kit vip. */
							else if(e.getMessage().startsWith("/kit vip")) {
								
								/* Check if player is in group 'vip' */
								if(user.inGroup("vip")) {
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player isn't in group 'vip' */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You aren't in the vip group!");
								}
							}
							
							/* Check if command was /kit elite. */
							else if(e.getMessage().startsWith("/kit elite")) {
								
								/* Check if player is in group 'supporter' */
								if(user.inGroup("elite")) {
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player isn't in group 'elite' */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You aren't in the elite group!");
								}
							}
							
							/* Check if command was /kit emperor. */
							else if(e.getMessage().startsWith("/kit emperor")) {
								
								/* Check if player is in group 'emperor' */
								if(user.inGroup("emperor")) {
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player isn't in group 'emperor' */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You aren't in the emperor group!");
								}
							}
							
							/* Check if command was /kit god. */
							else if(e.getMessage().startsWith("/kit god")) {
								
								/* Check if player is in group 'god' */
								if(user.inGroup("god")) {
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player isn't in group 'god' */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You aren't in the god group!");
								}
							}
							
							
							
							
							/* Check if command was /kit tank. */
							else if(e.getMessage().startsWith("/kit tank")) {
								
								/* Check if player has enough credits */
								if(credits > 1199) {
									
									/* purchase the kit. */
									setplayerscredits(p, 1200, "tank");
									
									/* Don't cancel the event. */
									e.setCancelled(false);
									
									/* Add them to selected kit list. */
									m.selectedkit.add(p);
								} else {
									
									/* Player doesn't have enough credits */
									e.setCancelled(true);
									
									/* Tell them that there not in the group. */
									p.sendMessage(ChatColor.RED + "Kits: " + ChatColor.GOLD + "You don't have enough credits for this purchase");
								}
							}
						}
					}
						
						
					} else {
						
						/* Player isn't starting on stone. So cancel the event. */
						e.setCancelled(true);
						
						/* [Debug message] Tell player there standing on stone. */
						p.sendMessage(ChatColor.GOLD + "Stand on stone while choosing kit.");
					}
				}
			}
			
	}

	public void setplayerscredits(Player p, int price, String kitname) {
	
		/* Get players credits */
		int playerscredits = m.getConfig().getInt("server.players.player.credits." + p.getName()); 

		/* Sum up the price */
		int finalprice = playerscredits - price;

		/* Set the players new credits. */
		m.getConfig().set("server.players.player.credits." + p.getName(), finalprice);

		/* Save config. */
		m.saveConfig();

		/* Warn them that they brought a kit. */
		p.sendMessage(ChatColor.RED + "Shop: " + ChatColor.GOLD + "You purchased kit " + ChatColor.GREEN + kitname + 
				ChatColor.GOLD + " for " + ChatColor.RED + price + ChatColor.GOLD + " credits!");

		/* Update there scoreboard. */
		m.usb.updatescoreboard(p);
	}
}
