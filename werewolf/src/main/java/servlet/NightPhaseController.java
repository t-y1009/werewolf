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
import model.Player;
import model.PlayerLogic;

@WebServlet("/NightPhaseController")
public class NightPhaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Account loginUser = (Account)session.getAttribute("loginUser");
	    PlayerLogic playerLogic = new PlayerLogic();
	    List<Player> playerList = playerLogic.checkAccountExecute();
	    RequestDispatcher dispatcher;
	    for(Player player : playerList) {
	    	//現在処理しているプレイヤーを確認してフォワードする
	    	if(loginUser.getId() == player.getAccount_id()) {
	    		switch(player.getRole()) {
	    			case 1:
	    				RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/night.jsp");
	    				dispathcer.forward(request, response);
	    				break;
	    			case 2:
	    				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/werewolfWait.jsp");
	    				dispatcher.forward(request, response);
	    				break;
	    			case 3:
	    				dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/night.jsp");
	    				dispathcer.forward(request, response);
	    				break;
	    			case 4:
	    				//プレイヤーリストから自分を削除する
	    				playerList.remove(playerList.indexOf(player));
	    				request.setAttribute("playerList", playerList);
	    				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/knight.jsp");
	    				dispatcher.forward(request, response);
	    				break;
	    			case 5:
	    				dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/night.jsp");
	    				dispathcer.forward(request, response);
	    				break;
	    		}
	    		break;
	    	}
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
