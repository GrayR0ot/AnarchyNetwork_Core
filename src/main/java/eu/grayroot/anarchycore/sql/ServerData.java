package eu.grayroot.anarchycore.sql;

import com.google.common.hash.Hashing;
import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.AnarchyPlayer;
import eu.grayroot.anarchycore.object.AnarchyServer;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class ServerData {

	private Connection connection;

	public ServerData(Connection connection) {
		this.connection = AnarchyCore.getInstance().getConnection();
	}
	
	public boolean isRegistered(AnarchyServer server){
		try {
			PreparedStatement q = connection.prepareStatement("SELECT `name` FROM `servers` WHERE `name` = ?");
			q.setString(1, server.getName());
			ResultSet resultat = q.executeQuery();
			boolean isRegistered = resultat.next();
			q.close();
			return isRegistered;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void registerServer(AnarchyServer anarchyServer){
		try {
			PreparedStatement rs = connection.prepareStatement("INSERT INTO `servers` (name, players, max_players, status) VALUES (?,?,?,?)");
			rs.setString(1, anarchyServer.getName());
			rs.setInt(2, anarchyServer.getPlayers());
			rs.setInt(3, anarchyServer.getMaxPlayers());
			rs.setBoolean(4, anarchyServer.isStatus());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public AnarchyServer getServerData(String name) {
		AnarchyServer anarchyServer = null;
		try {
			PreparedStatement q = connection.prepareStatement("SELECT * FROM `servers` WHERE `name` = ?");
			q.setString(1, name);
			ResultSet rs = q.executeQuery();
			while (rs.next()) {
				anarchyServer = new AnarchyServer(rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("players"),
						rs.getInt("max_players"),
						rs.getBoolean("status"));
			}
			q.close();
			return anarchyServer;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anarchyServer;
	}

	public void updateServerData(AnarchyServer anarchyServer){
		try {
			PreparedStatement rs = connection.prepareStatement("UPDATE `servers` SET `players`=?, `max_players`=?, `status`=? WHERE `name`=?");
			rs.setInt(1, anarchyServer.getPlayers());
			rs.setInt(2, anarchyServer.getMaxPlayers());
			rs.setBoolean(3, anarchyServer.isStatus());
			rs.setString(4, anarchyServer.getName());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void registerVote(int playerId, String serverName, String rewardName){
		try {
			PreparedStatement rs = connection.prepareStatement("INSERT INTO `vote_rewards` (player, server, reward) VALUES (?,?,?)");
			rs.setInt(1, playerId);
			rs.setString(2, serverName);
			rs.setString(3, rewardName);
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Timestamp getLastVote(AnarchyPlayer player){
		try {
			PreparedStatement q = connection.prepareStatement("SELECT * FROM `votes` WHERE `player` = ? ORDER BY `id` DESC");
			q.setInt(1, player.getId());
			ResultSet resultat = q.executeQuery();
			Timestamp lastVote = resultat.getTimestamp("created_at");
			q.close();
			return lastVote;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
