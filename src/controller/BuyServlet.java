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
 * 商品購入時に呼び出されるサーブレット<br>
 * ・doGet...対象の商品情報をDBから取得し、購入ページに遷移<br>
 * ・doPost...購入処理を呼び出し、結果表示ページに遷移
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
