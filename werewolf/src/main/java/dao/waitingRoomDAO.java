package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Mutter;

public class waitingRoomDAO {
	private final String JDBC_URL = "jdbc:postgresql://34.168.81.118:5432/postgres";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "second";
	
	//投稿内容を全件取得する
	public List<Mutter> findByAll()   {
		List<Mutter> mutterList = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, NAME, TEXT FROM waitingRoom ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECT文を実行し、結果表を取得
		    ResultSet rs = pStmt.executeQuery();
		    while(rs.next()) {
		    	//結果表から投稿内容を生成してリストに加えていく
		    	int id = rs.getInt("ID");
		    	String name = rs.getString("NAME");
		    	String text = rs.getString("TEXT");
		    	Mutter  mutter= new Mutter(id, name, text);
		        mutterList.add(mutter);
		    }
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	//メッセージを登録する
	public boolean create(Account loginUser,String text) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Connection conn = DriverManager .getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文(INSERT　INTO)を準備※?はJSPで入力された情報を与える
			String sql = "INSERT INTO  waitingRoom(name, text) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//？部分を置き換えてSQL文を完成させる
			pStmt.setString(1,loginUser.getName());
			pStmt.setString(2,text);
			//SQL文(INSERT　INTO)を実行してレコードの更新結果数を受け取る
			int result = pStmt.executeUpdate();
			//更新失敗
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
			String sql = "DELETE FROM waitingroom";
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
