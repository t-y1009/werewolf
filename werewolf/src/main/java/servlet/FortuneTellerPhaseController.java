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

import model.Player;
import model.PlayerLogic;
import model.RoleSerch;

@WebServlet("/FortuneTellerPhaseController")
public class FortuneTellerPhaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		String	fortune = (String)application.getAttribute("fortune");
		PlayerLogic playerLogic = new PlayerLogic();
		List<Player> playerList = playerLogic.checkAccountExecute();
		RequestDispatcher dispatcher;
			//占師が投票画面にいかない限り占師待機画面に移行し続ける
			if(fortune == null) {
				request.setAttribute("playerList", playerList);
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/processWaitingTime.jsp");
				dispatcher.forward(request, response);
			}else {
				response.sendRedirect("/werewolf/Discussion");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		ServletContext application = this.getServletContext();
		application.setAttribute("fortune","end");
		int roleNumber = Integer.parseInt(request.getParameter("roleNumber"));
		PlayerLogic playerLogic = new PlayerLogic();
		Player fortuneResultPlayer = playerLogic.confirmRoleNumber(roleNumber);
		RoleSerch roleSerch = new RoleSerch();
		fortuneResultPlayer.setRoleName(roleSerch.confirmRoleName(fortuneResultPlayer)); 
		request.setAttribute("fortuneResultPlayer", fortuneResultPlayer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/fotuneResult.jsp");
		dispatcher.forward(request, response);
		
	}

}
