package eu.grayroot.anarchycore.object;

import java.sql.Timestamp;

public class AnarchyPlayer {
	
	int id;
	String name;
	String uuid;
	String password;
	String registrationIP;
	Boolean auth;
	int votes;
	int vote_rewards;
	Timestamp created_at;
	Timestamp updated_at;

	public AnarchyPlayer(int id, String name, String uuid, String password, String registrationIP, Boolean auth, int votes, int vote_rewards, Timestamp created_at, Timestamp updated_at) {
		this.id = id;
		this.name = name;
		this.uuid = uuid;
		this.password = password;
		this.registrationIP = registrationIP;
		this.auth = auth;
		this.votes = votes;
		this.vote_rewards = vote_rewards;
		this.created_at = created_at;
		this.updated_at = updated_at;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegistrationIP() {
		return registrationIP;
	}

	public void setRegistrationIP(String registrationIP) {
		this.registrationIP = registrationIP;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getVote_rewards() {
		return vote_rewards;
	}

	public void setVote_rewards(int vote_rewards) {
		this.vote_rewards = vote_rewards;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}
