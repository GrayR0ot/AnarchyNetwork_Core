package eu.grayroot.anarchycore.sql;

import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.AnarchyPlayer;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerData {
	
	private Connection connection;
	
	public PlayerData(Connection connection) {
		this.connection = AnarchyCore.getInstance().getConnection();
	}
	
	public boolean isRegistered(Player player){
		try {
			PreparedStatement q = connection.prepareStatement("SELECT `name` FROM `players` WHERE `name` = ?");
			q.setString(1, player.getName());
			ResultSet resultat = q.executeQuery();
			boolean isRegistered = resultat.next();
			q.close();
			return isRegistered;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public AnarchyPlayer getPlayerData(Player player) {
		AnarchyPlayer anarchyPlayer = null;
		try {
			PreparedStatement q = connection.prepareStatement("SELECT * FROM `players` WHERE `name` = ?");
			q.setString(1, player.getName());
			ResultSet rs = q.executeQuery();
			while (rs.next()) {
				anarchyPlayer = new AnarchyPlayer(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("uuid"),
						rs.getString("password"),
						rs.getString("registration_ip"),
						rs.getBoolean("auth"),
						rs.getInt("votes"),
						rs.getInt("vote_rewards"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at"));
			}
			q.close();
			return anarchyPlayer;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anarchyPlayer;
	}

	public void updatePlayer(AnarchyPlayer player) {
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE `players` SET `vote_rewards`=? WHERE `uuid`=?");
			rs.setInt(1, player.getVote_rewards());
			rs.setString(2, player.getUuid());
			rs.executeUpdate();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
