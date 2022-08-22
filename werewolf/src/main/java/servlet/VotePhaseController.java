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

import model.Player;
import model.PlayerLogic;

@WebServlet("/VotePhaseController")
public class VotePhaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		//スコープから投票数と最終プレイヤーリストを取得
		int voteCount = (int)application.getAttribute("voteCount");
		List<Player> playerList = (List<Player>)session.getAttribute("finalPlayerList");
		//プレイヤー数と投票数が一致すれば結果画面へ
		if(voteCount == playerList.size()) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}else {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultWaitRoom.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		//投票数をスコープから取得
		int voteCount = (int)application.getAttribute("voteCount");
		PlayerLogic playerLogic = new PlayerLogic();
		List<Player> playerList = (List<Player>)session.getAttribute("finalPlayerList"); 
		//プレイヤーテーブルの投票数を加算する
		playerLogic.playerVoteCountExecute(id);
		//スコープの投票カウントに加算して待機画面へ移動する
		if(voteCount >= 0 && voteCount <= playerList.size()) {
			voteCount++;
			application.setAttribute("voteCount", voteCount);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/resultWaitRoom.jsp");
		dispatcher.forward(request, response);
	}

}
