package model;

import java.util.List;

import dao.PlayRoomDAO;

public class PlayRoomChatListLogic {
	public List<Mutter> getAllMutter(){
		PlayRoomDAO dao = new PlayRoomDAO();
		List<Mutter> mutterList = dao.findByMutterAll();
		return mutterList;
		
	}
	public boolean addMutter(String name,String text) {
		PlayRoomDAO dao = new PlayRoomDAO();
		dao.InsertByText(name, text);
		return true;
	}
	
	public boolean deleteMutterList() {
		PlayRoomDAO dao = new PlayRoomDAO();
		return dao.deleteFromTable();
	}
}
