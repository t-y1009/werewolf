package model;

import dao.AccountDAO;

public class LoginLogic {
	public Account loginCheck(String userId,String pass) {
		AccountDAO dao = new AccountDAO();
		return dao.findByLogin(userId,pass);
	}
	
	public boolean registerCheck(String userId,String name) {
			AccountDAO dao = new AccountDAO();
			return dao.duplication(userId,name);
	}
	
	public boolean addAccountCheck(String userId,String password,String name) {
		AccountDAO dao = new AccountDAO();
		return dao.addAccount(userId,password,name);
	}

}
		
