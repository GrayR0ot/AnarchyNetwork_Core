package eu.grayroot.anarchycore.command;

import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.AnarchyPlayer;
import eu.grayroot.anarchycore.object.Report;
import eu.grayroot.anarchycore.sql.PlayerData;
import eu.grayroot.anarchycore.sql.ReportData;
import eu.grayroot.anarchycore.sql.ServerData;
import eu.grayroot.anarchyprison.AnarchyPrison;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			AnarchyPlayer anarchyPlayer = new PlayerData(AnarchyCore.getInstance().getConnection()).getPlayerData(player);
			player.sendMessage("§8[§b!§8] §b» §7Pour voter rendez vous sur ce lien: §fhttps://anarchynetwork.eu/vote\n§8[§b!§8] §b» §7Vous avez actuellement §b" + anarchyPlayer.getVote_rewards() + " §7récompenses en attente.");
			switch (Bukkit.getServer().getServerName()) {
				case "Prison":
					for(int i = 0; i < anarchyPlayer.getVote_rewards(); i++) {
						int random = (int)(Math.random()*9);
						int randomStack = (int)(Math.random()*2304);
						switch (random) {
							case 0:
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §b1 Mana §7!");
								AnarchyPrison.getPrisonPlayers().get(player).setMana(AnarchyPrison.getPrisonPlayers().get(player).getMana()+1);
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", "1 Mana");
								break;
							case 1:
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §b3 Mana §7!");
								AnarchyPrison.getPrisonPlayers().get(player).setMana(AnarchyPrison.getPrisonPlayers().get(player).getMana()+3);
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", "3 Mana");
								break;
							case 2:
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §b6 Mana §7!");
								AnarchyPrison.getPrisonPlayers().get(player).setMana(AnarchyPrison.getPrisonPlayers().get(player).getMana()+6);
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", "6 Mana");
								break;
							case 3:
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + player.getName() + " COAL_BLOCK " + randomStack);
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §bx" + randomStack + " bloc de charbon §7!");
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", randomStack + " COAL_BLOCK");
								break;
							case 4:
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + player.getName() + " IRON_BLOCK " + randomStack);
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §bx" + randomStack + " bloc de fer §7!");
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", randomStack + " IRON_BLOCK");
								break;
							case 5:
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + player.getName() + " GOLD_BLOCK " + randomStack);
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §bx" + randomStack + " bloc d'or §7!");
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", randomStack + " GOLD_BLOCK");
								break;
							case 6:
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + player.getName() + " DIAMOND_BLOCK " + randomStack);
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §bx" + randomStack + " bloc de diamant §7!");
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", randomStack + " DIAMOND_BLOCK");
								break;
							case 7:
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + player.getName() + " EMERALD_BLOCK " + randomStack);
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §bx" + randomStack + " bloc d'émeraude §7!");
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", randomStack + " EMERALD_BLOCK");
								break;
							case 8:
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "give " + player.getName() + " ENDER_STONE " + randomStack);
								player.sendMessage("§8[§b!§8] §b» §7Vous avez obtenu §bx" + randomStack + " enderstone §7!");
								new ServerData(AnarchyCore.getInstance().getConnection()).registerVote(anarchyPlayer.getId(), "Prison", randomStack + " OBSIDIAN");
								break;
						}
					}
					anarchyPlayer.setVote_rewards(0);
					new PlayerData(AnarchyCore.getInstance().getConnection()).updatePlayer(anarchyPlayer);
					break;
				default:
					player.sendMessage("§8[§b!§8] §b» §7Aucune récompense disponible sur ce mode de jeu !");
					break;
			}
		}
		return false;
	}
}
