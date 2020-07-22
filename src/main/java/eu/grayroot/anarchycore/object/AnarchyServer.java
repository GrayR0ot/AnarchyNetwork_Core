package eu.grayroot.anarchycore.object;

import java.sql.Timestamp;

public class AnarchyServer {

	int id;
	String name;
	int players;
	int maxPlayers;
	boolean status;

	public AnarchyServer(int id, String name, int players, int maxPlayers, boolean status) {
		this.id = id;
		this.name = name;
		this.players = players;
		this.maxPlayers = maxPlayers;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayers() {
		return players;
	}

	public void setPlayers(int players) {
		this.players = players;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
