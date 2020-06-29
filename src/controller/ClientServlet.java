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
import dbAccess.SelectAll;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBAccess dbAccess;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		System.out.println("Gs:" + session);
		if (session == null) {
			System.out.println("無効なアクセス");
			response.sendRedirect("http://localhost:8080/webExam2/LoginServlet");
			return;
		}

		String user = (String)session.getAttribute("user");
		System.out.println("user:" + user);
		if (user == "admin") {
			System.out.println("管理者ページに遷移");
			response.sendRedirect("http://localhost:8080/webExam2/ManageServlet");
			return;
		}

		session.setAttribute("user", "guest");

		String btn = request.getParameter("btn");
		String next = null;

		if(btn != null) {
			next = "BuyServlet";
		}else {
			dbAccess = new SelectAll();
			next = "client.jsp";

			try {
				dbAccess.execute(request);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/" + next);
		dis.forward(request, response);
		}
}
