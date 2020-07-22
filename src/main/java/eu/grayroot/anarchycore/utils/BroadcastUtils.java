package eu.grayroot.anarchycore.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.AnarchyPlayer;
import eu.grayroot.anarchycore.sql.PlayerData;
import eu.grayroot.anarchyprison.AnarchyPrison;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastUtils {

	public BroadcastUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void broadcastScheduler() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(AnarchyCore.getInstance(), new Runnable() {
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle("§b§lAidez-nous !", "§fVotez et et soyez récompensés ! §a§l/vote"));
			}
		}, 20*60*15, 20*60*15);
	}

}
