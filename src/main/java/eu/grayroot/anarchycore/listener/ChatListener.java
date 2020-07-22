package eu.grayroot.anarchycore.listener;

import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.AnarchyPlayer;
import eu.grayroot.anarchycore.sql.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if(AnarchyCore.getLoginPlayers().contains(player)) { e.setCancelled(true); }
	}
}
