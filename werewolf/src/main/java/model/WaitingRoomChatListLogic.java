package model;

import java.util.List;

import dao.waitingRoomDAO;

public class WaitingRoomChatListLogic {
	//メッセージを取得
	public List<Mutter> getAllMutter()  {
		waitingRoomDAO dao = new waitingRoomDAO();
		List<Mutter> mutterList = dao.findByAll();
		return mutterList;
	}
	
	//メッセージを登録
	public boolean addMutter(Account loginUser, String text)  {
		waitingRoomDAO dao = new waitingRoomDAO();
	    return dao.create(loginUser, text);
	}
	
	public boolean deleteMutterAll() {
		waitingRoomDAO dao = new waitingRoomDAO();
		return dao.deleteFromTable();
	}

}
