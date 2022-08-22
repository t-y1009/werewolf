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

import model.PlayRoomChatListLogic;
import model.Player;
import model.PlayerLogic;

@WebServlet("/WerewolfController")
public class WerewolfPhaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		String	werewolf =(String)application.getAttribute("werewolf");
		PlayerLogic playerLogic = new PlayerLogic();
		PlayRoomChatListLogic playRoomChatListLogic = new PlayRoomChatListLogic();
		List<Player> playerList = playerLogic.checkAccountExecute();
		RequestDispatcher dispatcher;
		//人狼が投票画面にいかない限りnightにループし続ける
			if(werewolf != null) {
				int countMember = (int)application.getAttribute("countMember");
				//死亡したプレイヤーがいる場合はそのプレイヤーをスコープに保存する
				for(Player deadPlayer : playerList) {
					if(deadPlayer.getDeadFlag() == true) {
						request.setAttribute("deadPlayer", deadPlayer);
						break;
					}
				}
				//処理されたプレイヤーの数が0～3以下であればカウントを1増やしてフォワードする
				if (countMember >=0 && countMember <= 3) {
					countMember++;
					application.setAttribute("countMember", countMember);
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/morning.jsp");
					dispatcher.forward(request, response);
				}else {
					//最後ここを通る人は1回目の話し合いのテーブルを削除しておく
					playRoomChatListLogic.deleteMutterList();
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/morning.jsp");
					dispatcher.forward(request, response);
				}
			//人狼が行動前なら引き続きnightへ戻す
			}else {
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/night.jsp");
				dispatcher.forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
