package eu.grayroot.anarchycore.command;

import eu.grayroot.anarchycore.AnarchyCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage("§8[§b!§8] §b» §7Retour vers le hub...");
			Bukkit.getScheduler().scheduleSyncDelayedTask(AnarchyCore.getInstance(), new Runnable() {
				@Override
				public void run() {
					AnarchyCore.getInstance().sendPlayerToServer(player, "hub");
				}
			}, 30);
		}
		return false;
	}
}
