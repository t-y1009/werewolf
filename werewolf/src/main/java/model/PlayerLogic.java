package model;

import java.util.List;

import dao.PlayerDAO;

public class PlayerLogic {
	
	// Player情報を追加
	public void addDateBaseExecute(Account loginUser, int roleNumber) {
		PlayerDAO dao = new PlayerDAO();
		dao.createByPlayer(loginUser,roleNumber);
	}
	
	//Playerの役職を確認する
	public Player confirmRoleNumber(int roleNumber){
		PlayerDAO dao = new PlayerDAO();
		Player fortuneResultPlayer = dao.selectByRoleNumbler(roleNumber);
		return fortuneResultPlayer;
	}
	
	//プレイヤーリストを取得する
	public  List<Player> checkAccountExecute() {
		PlayerDAO dao = new PlayerDAO();
		List<Player> playerList = dao.findByPlayer();
		return playerList ;
	}
	
	// 騎士が守った後の処理
	public boolean knightActionExecute(int account_id) {
		PlayerDAO dao = new PlayerDAO();
		return dao.updateByKnight(account_id);
	}
	
	//人狼の行動してよいか確認する
	public boolean werewolfAcitionJudge() {
		PlayerDAO dao = new PlayerDAO();
		return dao.confirmByDefenceFlag();
	}
	
	//人狼の判定が成功した場合
	public boolean werewolfActionExecute(int eat) {
		PlayerDAO dao = new PlayerDAO();
		return dao.updateByWerewolf(eat);
	}
	
	//プレイヤーの投票数を増やす
	public boolean playerVoteCountExecute(int id) {
		PlayerDAO dao = new PlayerDAO();
		return dao.updateByVote(id);
	}
	
	//投票数が多い人のデッドフラグをTRUEにする
	public boolean playerDeadFlagExecute() {
		PlayerDAO dao = new PlayerDAO();
		return dao.updateByDeadFlag();
	}
	
	//プレイヤーテーブルの削除
	public boolean playerDelete() {
	PlayerDAO dao = new PlayerDAO();
	return dao.deleteByPlayer();
	}
}
