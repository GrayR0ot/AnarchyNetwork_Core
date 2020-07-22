package eu.grayroot.anarchycore.listener;

import eu.grayroot.anarchycore.AnarchyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

	@EventHandler
	public void onCMD(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		if(AnarchyCore.getLoginPlayers().contains(player)) { e.setCancelled(true); }
	}
}
