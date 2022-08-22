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
import model.WaitingRoomChatListLogic;


@WebServlet("/SubControll")
public class SubControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String start = request.getParameter("start");
		//待機所のカウントダウンが終了するとスタートがNULLでなくなる
		if(start != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ruleConfirm.jsp");
			dispatcher.forward(request,response);
		}else {
		WaitingRoomChatListLogic WaitingRoomChatListLogic = new WaitingRoomChatListLogic();
		List<Mutter> mutterList = WaitingRoomChatListLogic.getAllMutter();
		request.setAttribute("mutterList",mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/waitingRoom.jsp");
		dispatcher.forward(request,response);
		}
	}	

	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		//入力されたリクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		//入力値ﾁｪｯｸ
		if(text != null && text.length()!= 0) {
			//ｾｯｼｮﾝｽｺｰﾌﾟに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account loginUser = (Account)session.getAttribute("loginUser");
			//DBに追加
		    WaitingRoomChatListLogic WaitingRoomChatListLogic = new WaitingRoomChatListLogic();
			boolean result = WaitingRoomChatListLogic.addMutter(loginUser, text);
			//データベースへの書き込みが失敗した場合
			if(!(result)) {
				request.setAttribute("errorMsg", "メッセージの登録が失敗しました");
			}
			//空文字の場合
		}else {
			request.setAttribute("errorMsg", "メッセージを入力してください");
		}
		//最新のチャット内容を取得してフォワード
	    WaitingRoomChatListLogic WaitingRoomChatListLogic = new WaitingRoomChatListLogic();
	    List<Mutter> mutterList = WaitingRoomChatListLogic.getAllMutter();
	    request.setAttribute("mutterList", mutterList);
	    RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/jsp/waitingRoom.jsp");
	    dispacher.forward(request, response);
	}
}
