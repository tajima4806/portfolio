package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbAccess.DBAccess;
import dbAccess.SelectForLogin;

/**
 * ログイン時に呼び出されるサーブレット<br>
 * ・doGet...ログインページに遷移<br>
 * ・doPost...データ照合処理を呼び出し、成功時は管理ページにリダイレクト、失敗時はログインページに戻る
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBAccess dbAccess;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		System.out.println(session);

		if (session != null) {
			response.sendRedirect("http://localhost:8080/webExam2/ManageServlet");
			return;
		}

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/manageTop.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		System.out.println(session);

		dbAccess = new SelectForLogin();

		try {
			dbAccess.execute(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String login = (String)request.getAttribute("login");

		switch(login) {
		case "admin":
			response.sendRedirect("http://localhost:8080/webExam2/ManageServlet");
			break;
		case "client":
			response.sendRedirect("http://localhost:8080/webExam2/ClientServlet");
			break;
		case "error":
			session.invalidate();
			doGet(request, response);
			break;
		}
	}
}
