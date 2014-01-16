package me.shawshark.kitpvp.commands;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlltohubCommand implements CommandExecutor {
	
	public main m;
	
	public AlltohubCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] arg3) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
				if(p.isOp()) {
					if(m.alltohub)  {
						m.alltohub = false;
						p.sendMessage(ChatColor.GREEN  + "You have toggle all to hub: off, When reloading players will not get redirected!");
					} else {
						m.alltohub = true;
						p.sendMessage(ChatColor.GREEN  + "You have toggle all to hub: on, When reloading players will get redirected!");
					}
				}
 			}
		return false;
	}
}
