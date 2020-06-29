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

import dbAccess.BuyItem;
import dbAccess.DBAccess;
import dbAccess.SelectAll;
import dbAccess.SelectOne;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBAccess dbAccess;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("code") == null) {
			System.out.println("リダイレクト");
			response.sendRedirect("http://localhost:8080/webExam2/ClientServlet");
			return;
		}

		dbAccess = new SelectOne();
		try {
			dbAccess.execute(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/buy.jsp");
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btn").equals("yes")) {
			dbAccess = new BuyItem();
		}else {
			request.setAttribute("message", "操作をキャンセルしました");
			dbAccess = new SelectAll();
		}
			try {
				dbAccess.execute(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/client.jsp");
		dis.forward(request, response);
	}

}
