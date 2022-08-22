package model;

import java.io.Serializable;

public class Player implements Serializable {
	
	private int id;
	private int account_id;
	private String name;
	private int role;
	private String roleName;
	private boolean deadFlag;
	private boolean defence;
	private int voteCount;
	
	public Player() {}
	public Player(int account_id, String name) {
		this.account_id = account_id;
		this.name = name;
	}
	public Player(int id, int account_id, String name, int role, boolean deadFlag, boolean defence, int voteCount) {
		this.id = id;
		this.account_id = account_id;
		this.name = name;
		this.role = role;
		this.deadFlag = deadFlag;
		this.defence = defence;
		this.voteCount = voteCount;
	}
	
	// ゲッター
	public int getId() {
		return this.id;
	}
	public int getAccount_id() {
		return this.account_id;
	}
	public String getName() {
		return this.name;
	}
	public int getRole() {
		return this.role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public boolean getDeadFlag() {
		return this.deadFlag;
	}
	public boolean getDefence() {
		return this.defence;
	}
	public String getRoleName() {
		return roleName;
	}
	
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
