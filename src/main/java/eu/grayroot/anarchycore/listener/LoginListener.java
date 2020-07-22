package eu.grayroot.anarchycore.listener;

import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.AnarchyPlayer;
import eu.grayroot.anarchycore.sql.PlayerData;
import eu.grayroot.anarchycore.sql.ServerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LoginListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		e.setJoinMessage(null);
		AnarchyPlayer anarchyPlayer = new PlayerData(AnarchyCore.getInstance().getConnection()).getPlayerData(player);
		if(anarchyPlayer == null || anarchyPlayer.getAuth() == false) {
			AnarchyCore.getInstance().log(AnarchyCore.getInstance().getServer().getServerName().toLowerCase());
			if(AnarchyCore.getInstance().getServer().getServerName().equalsIgnoreCase("Login")) {
				AnarchyCore.getLoginPlayers().add(player);
			} else {
				player.kickPlayer("Vous devez vous connecter via l'IP: play.anarchynetwork.eu !");
			}
		}
		AnarchyCore.getInstance().getAnarchyServer().setPlayers(Bukkit.getServer().getOnlinePlayers().size());
		new ServerData(AnarchyCore.getInstance().getConnection()).updateServerData(AnarchyCore.getInstance().getAnarchyServer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		e.setQuitMessage(null);
		if(AnarchyCore.getLoginPlayers().contains(player)) {
			AnarchyCore.getLoginPlayers().remove(player);
		}
		AnarchyCore.getInstance().getAnarchyServer().setPlayers(Bukkit.getServer().getOnlinePlayers().size());
		new ServerData(AnarchyCore.getInstance().getConnection()).updateServerData(AnarchyCore.getInstance().getAnarchyServer());
	}
}
