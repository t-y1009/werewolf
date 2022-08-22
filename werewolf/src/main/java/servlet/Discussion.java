package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Mutter;
import model.PlayRoomChatListLogic;
import model.Player;
import model.PlayerLogic;
@WebServlet("/Discussion")
public class Discussion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int phaseCount = (int)session.getAttribute("phaseCount");
		String phase = request.getParameter("phase");
		String deadPlayer = request.getParameter("player");
		//2週目且死亡したプレイヤーは別画面で経過を見守る
		if(deadPlayer != null) {
			PlayRoomChatListLogic playRoomChatListLogic = new PlayRoomChatListLogic();
			List<Mutter> mutterList = playRoomChatListLogic.getAllMutter();
			request.setAttribute("mutterList", mutterList);
			RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/deadManPlayRoom.jsp");
			dispacher.forward(request, response);
		}else {
			//1回目の話し合い
			if(phaseCount == 0) {
			//phaseCountは話合いのカウントダウンが終了したらNULLではなくなる
				if(phase == null) {
					PlayRoomChatListLogic playRoomChatListLogic = new PlayRoomChatListLogic();
					List<Mutter> mutterList = playRoomChatListLogic.getAllMutter();
					request.setAttribute("mutterList", mutterList);
					RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/playRoom.jsp");
					dispacher.forward(request, response);
				}else {
					phaseCount++;
					session.setAttribute("phaseCount", phaseCount);
					response.sendRedirect("/werewolf/NightPhaseController");
				}
			//2回目の話し合い
			}else {
				//phaseは話合いの時間が終了したらNULLではなくなる
				if(phase == null) {
					PlayRoomChatListLogic playRoomChatListLogic = new PlayRoomChatListLogic();
					List<Mutter> mutterList = playRoomChatListLogic.getAllMutter();
					request.setAttribute("mutterList", mutterList);
					RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/playRoom.jsp");
					dispacher.forward(request, response);
				}else {
					Account loginUser = (Account)session.getAttribute("loginUser");
					PlayerLogic playerLogic = new PlayerLogic();
					List<Player> playerList = playerLogic.checkAccountExecute();
					int playerNumber = 100;
					boolean isDeadPlayer = false;
					//死亡している人をリストから削除する
					for(Player player : playerList) {
						if(player.getDeadFlag() == true && loginUser.getId() == player.getAccount_id()){
							isDeadPlayer = true;
							playerNumber = playerList.indexOf(player);
							break;
						}else if(player.getDeadFlag() == true){
							playerNumber = playerList.indexOf(player);
							break;
						}
					}
					if(playerNumber != 100) {
						playerList.remove(playerNumber);
					}
					session.setAttribute("finalPlayerList", playerList);
					//死亡プレイヤーは先に結果待機所へ移動させる
					if(isDeadPlayer) {
						RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/resultWaitRoom.jsp");
						dispacher.forward(request, response);
					}else {
						RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/vote.jsp");
						dispacher.forward(request, response);
					}
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメーターの取得
				request.setCharacterEncoding("UTF-8");
				String text = request.getParameter("text");
				//入力値ﾁｪｯｸ
				if(text != null && text.length()!= 0) {
					//ｾｯｼｮﾝｽｺｰﾌﾟに保存されたユーザー情報を取得
					HttpSession session = request.getSession();
					Account loginUser = (Account)session.getAttribute("loginUser");
					//つぶやきをつぶやきﾘｽﾄに追加
				    PlayRoomChatListLogic playRoomChatListLogic = new PlayRoomChatListLogic();
					playRoomChatListLogic.addMutter(loginUser.getName(), text);
				}else {
					request.setAttribute("errorMsg", "ﾒｯｾｰｼﾞを入力してください");
				}
				//取得
				PlayRoomChatListLogic playRoomChatListLogic = new PlayRoomChatListLogic();
			    List<Mutter> mutterList =  playRoomChatListLogic.getAllMutter();
			    request.setAttribute("mutterList", mutterList);
			    RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/playRoom.jsp");
			    dispacher.forward(request, response);
	}

}
