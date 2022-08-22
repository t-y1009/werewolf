package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 新規登録をregister.jspにフォワード 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン情報の受け取り
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		Account loginUser = null;
		LoginLogic loginLogic = new LoginLogic();
		//ログインチェック
		if(name == null ) {
			loginUser = loginLogic.loginCheck(userId, pass);
			if(loginUser !=null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				ServletContext application  = this.getServletContext();
				//ログインできたらスコープにログインカウントを1増やして保存する
				if(application.getAttribute("count") != null) {
					int count = (int)application.getAttribute("count");
					count += 1;
					application.setAttribute("count", count);
				}else {
					int count = 1;
					application.setAttribute("count", count);
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
			dispatcher.forward(request, response);
		}else {	
		//登録確認
			boolean result = loginLogic.registerCheck(userId,name);
			if(result) {
				result=	loginLogic.addAccountCheck(userId,pass,name);
			}
			request.setAttribute("result",result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerResult.jsp");
			dispatcher.forward(request,response);
		}

	}

}