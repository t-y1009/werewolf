package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.PlayerLogic;

@WebServlet("/StandByController")
public class StandByController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");
		ServletContext application = this.getServletContext();
		Random rnd = new Random();
		// PlayerLogicをインスタンス化
		PlayerLogic playerLogic = new PlayerLogic();
		//roleNumberはroleを割り振るための番号配列
		//スコープに保存して取り出した番号は削除して再度スコープを更新する
		List<Integer> roleNumberList =(List<Integer>)application.getAttribute("roleNumberList");
		if (roleNumberList == null) {
			roleNumberList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
			Collections.shuffle(roleNumberList);
			int roleNumber = roleNumberList.get(rnd.nextInt(roleNumberList.size()));
			playerLogic.addDateBaseExecute(loginUser, roleNumber);
			roleNumberList.remove(roleNumberList.indexOf(roleNumber));
		}else{
			int roleNumber = roleNumberList.get(rnd.nextInt(roleNumberList.size()));
			playerLogic.addDateBaseExecute(loginUser, roleNumber);
			roleNumberList.remove(roleNumberList.indexOf(roleNumber));
		}
		//役職管理サーブレットへフォワード
		int phaseCount = 0;
		session.setAttribute("phaseCount", phaseCount);
		application.setAttribute("roleNumberList", roleNumberList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RoleController");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
