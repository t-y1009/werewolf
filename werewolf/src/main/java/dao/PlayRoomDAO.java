package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class PlayRoomDAO {

	private final String JDBC_URL = "jdbc:postgresql://34.168.81.118:5432/postgres";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "second";
	
	public List<Mutter> findByMutterAll()  {
		// ArrayListの作成
		List<Mutter> mutterList = new ArrayList<>(); 
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			// SQL文を準備（サブクエリ）※修正必要！！！
			String sql = "SELECT ID, NAME, TEXT FROM playroom ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// 実行、結果表取得
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id, name, text);
				mutterList.add(mutter);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList; 
	}
	
	public boolean InsertByText(String name,String text) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//データベースに接続
		try(Connection conn = DriverManager .getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文(INSERT　INTO)を準備※?部分は2段階に分けて行う
			String sql = "INSERT INTO  playroom(name, text) VALUES(?, ?)";
			//PreparedStatement ｲﾝｽﾀﾝｽを使ってSQL文を送る準備
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//？部分を置き換えてSQL文を完成させる
			pStmt.setString(1,name);
			pStmt.setString(2,text);
			//SQL文(INSERT　INTO)を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteFromTable() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//データベースに接続
		try(Connection conn = DriverManager .getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文(INSERT　INTO)を準備※?部分は2段階に分けて行う
			String sql = "DELETE FROM playroom";
			//PreparedStatement ｲﾝｽﾀﾝｽを使ってSQL文を送る準備
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//？部分を置き換えてSQL文を完成させる
			//SQL文(INSERT　INTO)を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
