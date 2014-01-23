package me.shawshark.kitpvp.EventListeners;

import me.shawshark.kitpvp.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerInteract implements Listener {
	
	public main m;
	
	public PlayerInteract(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onPlayerInteractDONOR(PlayerInteractEvent e) {
			
			Player p = e.getPlayer();
			
			/* Check to see if player is in world 'world' */
			if(p.getWorld() == Bukkit.getWorld("world")) {
				
				/* Check if it was a left or right click BLOCK */
			    if((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			    	
			    	
			    	/* Check if clicked block was a sign. */
			    	if(e.getClickedBlock().getState() instanceof Sign) {
			    		
				    	 Sign s = (Sign) e.getClickedBlock().getState();
				    	 
				    	 PermissionUser user = PermissionsEx.getUser(p);
				    	 
				    	 /* Get kit name */
				    	 if(s.getLine(1).contains("donor")) {
				    		
				    		/* Check to see if user is in group '?' */
				    		if(user.inGroup("donor")) {
				    			
				    			/* Add them to list so '/kit' can be run. */
				    			m.interwithsign.add(p);
				    			
				    			/* Run the kit command. */
				    			p.chat("/kit donor");
				    			
				    			/* They have choosen there kit, remove them from the list. */
				    			m.interwithsign.remove(p);
				    		} else {
				    			p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't use this kit, You are not in the donor group!");
				    		}	
					     } 
				    	 
				    	 else if(s.getLine(1).contains("supporter")) {
					    		
					    		/* Check to see if user is in group '?' */
					    		if(user.inGroup("supporter")) {
					    			
					    			/* Add them to list so '/kit' can be run. */
					    			m.interwithsign.add(p);
					    			
					    			/* Run the kit command. */
					    			p.chat("/kit supporter");
					    			
					    			/* They have choosen there kit, remove them from the list. */
					    			m.interwithsign.remove(p);
					    		} else {
					    			p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't use this kit, You are not in the supporter group!");
					    		}	
				    	 } 
				    	 
				    	 else if(s.getLine(1).contains("vip")) {
					    		
					    		/* Check to see if user is in group '?' */
					    		if(user.inGroup("vip")) {
					    			
					    			/* Add them to list so '/kit' can be run. */
					    			m.interwithsign.add(p);
					    			
					    			/* Run the kit command. */
					    			p.chat("/kit vip");
					    			
					    			/* They have choosen there kit, remove them from the list. */
					    			m.interwithsign.remove(p);
					    		} else {
					    			p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't use this kit, You are not in the vip group!");
					    		}	
				    	 } 
				    	 
				    	 else if(s.getLine(1).contains("elite")) {
					    		
					    		/* Check to see if user is in group '?' */
					    		if(user.inGroup("elite")) {
					    			
					    			/* Add them to list so '/kit' can be run. */
					    			m.interwithsign.add(p);
					    			
					    			/* Run the kit command. */
					    			p.chat("/kit elite");
					    			
					    			/* They have choosen there kit, remove them from the list. */
					    			m.interwithsign.remove(p);
					    		} else {
					    			p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't use this kit, You are not in the elite group!");
					    		}	
				    	 } 
				    	 
				    	 else if(s.getLine(1).contains("emperor")) {
					    		
					    		/* Check to see if user is in group '?' */
					    		if(user.inGroup("emperor")) {
					    			
					    			/* Add them to list so '/kit' can be run. */
					    			m.interwithsign.add(p);
					    			
					    			/* Run the kit command. */
					    			p.chat("/kit emperor");
					    			
					    			/* They have choosen there kit, remove them from the list. */
					    			m.interwithsign.remove(p);
					    		} else {
					    			p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't use this kit, You are not in the emperor group!");
					    		}	
				    	 } 
				    	 
				    	 else if(s.getLine(1).contains("god")) {
					    		
					    		/* Check to see if user is in group '?' */
					    		if(user.inGroup("god") || (user.inGroup("dev") || (user.inGroup("mod") || (user.inGroup("admin") || (user.inGroup("owner")))))) {
					    			
					    			/* Add them to list so '/kit' can be run. */
					    			m.interwithsign.add(p);
					    			
					    			/* Run the kit command. */
					    			p.chat("/kit god");
					    			
					    			/* They have choosen there kit, remove them from the list. */
					    			m.interwithsign.remove(p);
					    		} else {
					    			p.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + "You can't use this kit, You are not in the god group!");
					    		}	
				    	 }
				    	 
			    	}
			    	

			    }
				
			}    	
	}
	
	@EventHandler
	public void onPlayerInteractPUBLIC(PlayerInteractEvent e) {
			
			Player p = e.getPlayer();
			
			/* Check to see if player is in world 'world' */
			if(p.getWorld() == Bukkit.getWorld("world")) {
				
				/* Check if it was a left or right click BLOCK */
			    if((e.getAction() == Action.LEFT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			    	
			    	
			    	/* Check if clicked block was a sign. */
			    	if(e.getClickedBlock().getState() instanceof Sign) {
			    		
				    	 Sign s = (Sign) e.getClickedBlock().getState();
				    	 
				    	 /* Get players credits */
				    	 int playerscredits = m.getConfig().getInt("server.players.player.credits." + p.getName());
				    	 
				    	 /* Get kit name */
				    	 if(s.getLine(1).contains("defaultkit")) {
				    		 
				    		/* Check to see if user is has enough credits. */
				    		//if(playerscredits > 0) {
				    			
				    			/* Add them to list so '/kit' can be run. */
				    			m.interwithsign.add(p);
				    			
				    			/* Run the kit command. */
				    			p.chat("/kit default");
				    			
				    			/* They have choosen there kit, remove them from the list. */
				    			m.interwithsign.remove(p);
				    		}
					     //} 
				    	 
				    	 else if(s.getLine(1).contains("default")) {
				    		 
				    		/* Check to see if user is has enough credits. */
				    		if(playerscredits > 0) {
				    			
				    			/* Add them to list so '/kit' can be run. */
				    			m.interwithsign.add(p);
				    			
				    			/* Run the kit command. */
				    			p.chat("/kit default");
				    			
				    			/* They have choosen there kit, remove them from the list. */
				    			m.interwithsign.remove(p);
				    		}
					     } 
				    	 
				    	 
				    	 
			    	}
			    	
			    }
			    
			}
			
	}
	
	public void setplayerscredits(Player p, int price) {
		
		/* Get players credits */
		int playerscredits = m.getConfig().getInt("server.players.player.credits." + p.getName()); 
		
		/* Sum up the price */
		int finalscore = playerscredits - price;
		
		/* Set the players new credits. */
		m.getConfig().set("server.players.player.credits." + p.getName(), finalscore);
		
		/* Save config. */
		m.saveConfig();
		
		/* Warn them that they brought a kit. */
		p.sendMessage(ChatColor.RED + "Shop: " + ChatColor.GOLD + "You purchased a kit for " + ChatColor.GOLD + price + ChatColor.GOLD + " credits!");
		
		/* Update there scoreboard. */
		m.usb.updatescoreboard(p);
	}
}
