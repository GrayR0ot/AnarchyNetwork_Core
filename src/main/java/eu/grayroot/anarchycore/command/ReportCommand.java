package eu.grayroot.anarchycore.command;

import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.Report;
import eu.grayroot.anarchycore.sql.ReportData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length < 1) {
				player.sendMessage("§8[§b!§8] §b» §fAide: §7/report (EX: Le joueur Test insulte les autres)");
			} else {
				String reason = String.join(" ", args);
				Report report = new Report(0, reason, AnarchyCore.getInstance().getServer().getServerName(), player.getName(), null, false);
				new ReportData(AnarchyCore.getInstance().getConnection()).createReport(report);
				player.sendMessage("§8[§b!§8] §b» §7Merci, votre signalement à été envoyé, nous allons le traiter au plus vite !");
			}
		}
		return false;
	}
}
