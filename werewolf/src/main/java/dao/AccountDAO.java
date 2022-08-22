package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {

	private final String JDBC_URL="jdbc:postgresql://34.168.81.118:5432/postgres";
	private final String DB_USER ="postgres";
	private final String DB_PASS ="second";
	
	public Account findByLogin(String userId,String pass) {
		 Account account = null;
		 try {
			 Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 try(Connection conn = DriverManager.getConnection
				 (JDBC_URL,DB_USER,DB_PASS)){
			 String sql = "SELECT ID,USER_ID,PASSWORD,NAME FROM account WHERE USER_ID = ? AND PASSWORD = ?";
			 PreparedStatement pStmt = conn.prepareStatement(sql);
			 pStmt.setString(1, userId);
			 pStmt.setString(2, pass);
			 ResultSet rs = pStmt.executeQuery();
			 while(rs.next()){
				int id = rs.getInt("id");
				String user_id = rs.getString("user_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				account = new Account(id,user_id,password,name);
			 }
			 }catch(SQLException e) {
				 e.printStackTrace();
				 return null;
			 }
		       return account;
		       
	}
	
	public boolean duplication(String user_Id,String name) {
		 try {
			 Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		 try(Connection conn = DriverManager.getConnection
				 (JDBC_URL,DB_USER,DB_PASS)){
			 String sql = "SELECT user_id,name FROM account WHERE user_id =? OR name=?";
			 PreparedStatement pStmt = conn.prepareStatement(sql);
			 pStmt.setString(1, user_Id);
			 pStmt.setString(2, name);
			 ResultSet rs = pStmt.executeQuery();
			 if(!(rs.isBeforeFirst())){
				return true; 
			 }
		 } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		 return false;
	}
	
	  public boolean addAccount(String user_Id,String password,String name) {
			 try {
				 Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			 try(Connection conn = DriverManager.getConnection
					 (JDBC_URL,DB_USER,DB_PASS)){
				 String sql = "INSERT INTO account (user_id,password,name)"
				 		+ "VALUES(?,?,?)";
				 PreparedStatement pStmt = conn.prepareStatement(sql);
				 pStmt.setString(1, user_Id);
				 pStmt.setString(2, password); 
				 pStmt.setString(3, name);
				 int result = pStmt.executeUpdate();
					if(result !=1) {
					return false;	
					}	 
	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		   return true;	 
			 
	}  	
}




