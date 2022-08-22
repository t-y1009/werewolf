package model;

import java.io.Serializable;

public class Result implements Serializable{
	
	private String id;
	private int account_id;
	private String name;
	private int role;
	private boolean deadFlag;
	private boolean defence;
	
	public Result() {}
	public Result(String id, int account_id, String name, int role, boolean deadFlag, boolean defence) {
		this.id = id;
		this.account_id = account_id;
		this.name = name;
		this.role = role;
		this.deadFlag = deadFlag;
		this.defence = defence;
	}
	public String getId() {
		return id;
	}
	public int getAccount_Id() {
		return account_id;
	}
	public String getName() {
		return name;
	}
	public int getRole() {
		return role;
	}
	public boolean getDeadFlag() {
		return deadFlag;
	}
	public boolean getDefence() {
		return defence;
	}

}
