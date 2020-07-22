package eu.grayroot.anarchycore.utils;

import eu.grayroot.anarchycore.AnarchyCore;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeUtils {

	public BungeeUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sendPlayerToServer(Player player, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);

		player.sendPluginMessage(AnarchyCore.getInstance(), "BungeeCord", out.toByteArray());
	}

}
