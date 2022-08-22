package model;

import java.io.Serializable;

public class Account implements Serializable {
	private int id;
	private String userId;
	private String pass;
	private String name;
	
	public Account(int id, String userId, String pass, String name) {
		this.id = id;
		this.userId = userId;
		this.pass = pass;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public String getPass() {
		return pass;
	}
	public String getName() {
		return name;
	}

}
