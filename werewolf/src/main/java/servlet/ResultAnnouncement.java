package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PlayRoomChatListLogic;
import model.Player;
import model.PlayerLogic;
import model.WaitingRoomChatListLogic;


@WebServlet("/ResultAnnouncement")
public class ResultAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		PlayerLogic playerLogic = new PlayerLogic();
		playerLogic.playerDeadFlagExecute();
		List<Player> playerList = playerLogic.checkAccountExecute();
		for(Player player  : playerList) {
			if(player.getRole() == 2) {
				//人狼勝利時
				if(!(player.getDeadFlag())) {
					session.removeAttribute("finalPlayerList");
					session.removeAttribute("loginUser");
					session.removeAttribute("phaseCount");
					int count = (int) application.getAttribute("count");
					count--;
					application.setAttribute("count", count);
					//最後の人はアプリケーションスコープとチャットルームを消す
					if(count == 0) {
						application.removeAttribute("countMember");
						application.removeAttribute("voteCount");
						application.removeAttribute("roleNumberList");
						application.removeAttribute("count");
						application.removeAttribute("fortune");
						WaitingRoomChatListLogic  wrcll = new WaitingRoomChatListLogic();
						PlayRoomChatListLogic playRoom = new PlayRoomChatListLogic();
						wrcll.deleteMutterAll();
						playRoom.deleteMutterList();
						playerLogic.playerDelete();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/wereWolfWin.jsp");
					dispatcher.forward(request, response);
					break;
				//市民勝利時
				}else {
					session.removeAttribute("finalPlayerList");
					session.removeAttribute("loginUser");
					session.removeAttribute("phaseCount");
					session.removeAttribute("yourRole");
					int count = (int) application.getAttribute("count");
					count--;
					application.setAttribute("count", count);
					//最後の人はアプリケーションスコープとチャットルームを消す
					if(count == 0) {
						application.removeAttribute("countMember");
						application.removeAttribute("voteCount");
						application.removeAttribute("roleNumberList");
						application.removeAttribute("count");
						application.removeAttribute("fortune");
						WaitingRoomChatListLogic  wrcll = new WaitingRoomChatListLogic();
						PlayRoomChatListLogic playRoom = new PlayRoomChatListLogic();
						wrcll.deleteMutterAll();
						playRoom.deleteMutterList();
						playerLogic.playerDelete();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/wereWolfLose.jsp");
					dispatcher.forward(request, response);
					break;
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
