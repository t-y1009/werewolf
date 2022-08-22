package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Player;

public class PlayerDAO {
	private final String JDBC_URL= "jdbc:postgresql://34.168.81.118:5432/postgres";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "second";
	
	// Player情報を追加する
	public boolean createByPlayer(Account loginUser, int roleNumber)  {
		// ドライバ読み込み
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			// SQL文を準備
			String sql = "INSERT INTO player (ACCOUNT_ID, NAME, ROLE) values(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// ?を置き換え
			pStmt.setInt(1, loginUser.getId());
			pStmt.setString(2, loginUser.getName());
			pStmt.setInt(3, roleNumber);
			// 実行
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
	
	//Player情報を取得
	public List<Player> findByPlayer()  {
		List<Player> playerList = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, ACCOUNT_ID, NAME, ROLE, DEADFLAG, DEFENCE, VOTE_COUNT FROM Player ORDER BY ID ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行し、結果表を取得
		    ResultSet rs = pStmt.executeQuery();
		    //登録が合った場合(Accountインスタンスを作成)
		    //一致したユーザーが存在した場合
		    //そのユーザーを表すAccountｲﾝｽﾀﾝｽを生成
		    while(rs.next()) {
		    	//結果表からﾃﾞｰﾀを取得
		    	int id = rs.getInt("ID");
		    	int account = rs.getInt("ACCOUNT_ID");
		    	String name = rs.getString("NAME");
		    	int role = rs.getInt("ROLE");
		    	boolean deadFlag = rs.getBoolean("DEADFLAG");
		    	boolean defence = rs.getBoolean("DEFENCE");
		    	int voteCount = rs.getInt("VOTE_COUNT");
		    	Player player = new Player(id, account, name, role, deadFlag, defence, voteCount);
		        playerList.add(player);
		    }
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("リスト取得中にエラーです");
			return null;
		}
		//見つかったユーザーまたはnullを返す
		return playerList;
	}
	
	//ロールの重複を確認する
	public void roleDuplicationCheck() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			int result = pStmt.executeUpdate();
			if(result != 1) {
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//プレイヤーのroleを確認する
	public Player selectByRoleNumbler(int roleNumber) {
		Player fortuneResultPlayer = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM player WHERE role = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, roleNumber);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int account = rs.getInt("account_id");
				String name = rs.getString("name");
				int role = rs.getInt("role");
				boolean deadFlag = rs.getBoolean("deadFlag");
				boolean defence = rs.getBoolean("defence");
				int voteCount = rs.getInt("VOTE_COUNT");
				fortuneResultPlayer = new Player(id, account, name, role, deadFlag, defence, voteCount);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
			return fortuneResultPlayer;
	}
	
	// 騎士が守るプレイヤーのdefenceをtrueに更新（update文）
	public boolean updateByKnight(int account_id) {
		
		// ドライバ読み込み
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			// SQL文を準備
			String sql = "UPDATE player SET DEFENCE = TRUE WHERE account_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// ?を置き換え
			pStmt.setInt(1, account_id);
			
			// 実行、結果表取得
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 人狼が行動してもよいか確認
	public boolean confirmByDefenceFlag() {
		// ドライバ読み込み
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			// SQL文を準備
			String sql = "SELECT DEFENCE FROM player WHERE defence = TRUE";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// 実行、結果表取得
			ResultSet rs = pStmt.executeQuery();
			if(!(rs.isBeforeFirst())) {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// 人狼が指定したプレイヤーのdeadFlagをtrueに更新（update文）
	public boolean updateByWerewolf(int eat) {
		// ドライバ読み込み
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			// SQL文を準備
			String sql = "UPDATE player SET DEADFLAG = TRUE WHERE account_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// ?を置き換え
			pStmt.setInt(1, eat);
			
			// 実行、結果表取得
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//該当プレイヤーの投票数を増やす
	public boolean updateByVote(int id) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		// SQL文を準備
			String sql = "UPDATE player SET VOTE_COUNT = VOTE_COUNT + 1 WHERE ID = ? " ;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean updateByDeadFlag() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		// SQL文を準備(投票数が一番多いレコードを条件にしてデッドフラグを変更する)
			String sql = "UPDATE player SET DEADFLAG = true WHERE VOTE_COUNT =  (SELECT max(VOTE_COUNT) FROM player); " ;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return true;
	}
	
	// Player情報を削除する
		public boolean deleteByPlayer()  {
			// データベース接続
			try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				String sql = "DELETE FROM player";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// 実行
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
