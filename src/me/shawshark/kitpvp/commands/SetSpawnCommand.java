package me.shawshark.kitpvp.commands;

import me.shawshark.kitpvp.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
	
	public main m;
	
	public SetSpawnCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] arg3) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
				if(p.hasPermission("craftshark.setspawn")) {
					m.sets.setspawn(p);
				} else {
					p.sendMessage(ChatColor.RED + "Error: You're not alowed to set the server spawn.");
				}
 			}
		return false;
	}
}
