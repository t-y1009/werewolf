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

import model.Account;
import model.Player;
import model.PlayerLogic;
import model.RoleSerch;


@WebServlet("/RoleController")
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//このサーブレットのGETリクエストでは占い師と人狼の行動を扱う
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String roleName =request.getParameter("roleName");
		PlayerLogic playerLogic = new PlayerLogic();
		RequestDispatcher dispatcher;
		//roleNameがNULLの時は人狼からのリクエストではない
		if(roleName == null) {
			//占い師からのリクエスト
			if(action != null) {
				//プレイヤーデータベースの数が5人でなければ揃うまで待機画面に行かせる
				List<Player> playerList = playerLogic.checkAccountExecute();
				int playerNumber = 0;
				for(Player player : playerList) {
					if(player.getRole() == 3) {
						playerNumber = playerList.indexOf(player);
						break;
					}
				}
				if(playerList.size() != 5) {
					request.setAttribute("playerList", playerList);
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fortuneTellerWait.jsp");
					dispatcher.forward(request, response);
					
				}else {
					playerList.remove(playerNumber);
					request.setAttribute("playerList", playerList);
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fortuneTeller.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				HttpSession session = request.getSession();
				Account loginUser = (Account)session.getAttribute("loginUser");
				List<Player> playerList = playerLogic.checkAccountExecute();
				for(Player player : playerList) {
					if(loginUser.getId() == player.getAccount_id()) {
						if(player.getRole() == 3) {
							request.setAttribute("playerList", playerList);
							dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fortuneTellerWait.jsp");
							dispatcher.forward(request, response);
							break;
						}else{
							RoleSerch roleSerch = new RoleSerch();
							String yourRole = roleSerch.confirmRoleName(player);
							request.setAttribute("playerList", playerList);
							session.setAttribute("yourRole", yourRole);
							dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/processWaitingTime.jsp");
							dispatcher.forward(request, response);
							break;
						}
					}
				}
			}
		//人狼の行動処理
		}else {
			switch(roleName) {
				case "werewolf":
					HttpSession session = request.getSession();
					Account loginUser = (Account)session.getAttribute("loginUser");
					//騎士が行動済みか確認
					if(playerLogic.werewolfAcitionJudge()) {
						int myPlayerNumber = 0;
						List<Player> playerList = playerLogic.checkAccountExecute();
						 for(Player player : playerList) {
						    if(loginUser.getId() == player.getAccount_id()) {
						    	myPlayerNumber = playerList.indexOf(player);
						    }
						 }
						//プレイヤーリストから自分を削除する
						playerList.remove(myPlayerNumber);
						request.setAttribute("playerList", playerList);
						dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/werewolf.jsp");
						dispatcher.forward(request, response);
					}else {
					//まだなら再び待機画面へ
						dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/werewolfWait.jsp");
						dispatcher.forward(request, response);
					}
					break;
					
				case "knight":
					dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/night.jsp");
					dispatcher.forward(request, response);
					break;
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String roleName = request.getParameter("roleName");
		PlayerLogic playerLogic = new PlayerLogic();
		List<Player> playerList;
		RequestDispatcher dispatcher;
		ServletContext application = this.getServletContext();
		int account_id = 0;
		switch(roleName) {
			case "knight":
				account_id = Integer.parseInt(request.getParameter("account_id"));
				playerLogic.knightActionExecute(account_id);
				dispatcher = request.getRequestDispatcher("//WEB-INF/jsp/night.jsp");
				dispatcher.forward(request, response);
				break;
			case "werewolf":
				account_id = Integer.parseInt(request.getParameter("account_id"));
				playerList = playerLogic.checkAccountExecute();
				for(Player player : playerList) {
				//受け取ったアカウントIDのディフェンスがONになっていればそのまま次の画面へオフなら指定したプレイヤ―を倒す
					if(player.getAccount_id() == account_id) {
						if(player.getDefence() == false) {
							playerLogic.werewolfActionExecute(account_id);
						}
						application.setAttribute("werewolf", "end");
						int voteCount = 0;
						application.setAttribute("voteCount", voteCount);
						response.sendRedirect("/werewolf/WerewolfController");
						break;
						}
					}
					break;
		}
		
	}

}
