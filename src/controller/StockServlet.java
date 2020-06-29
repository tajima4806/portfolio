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

import dbAccess.AddStock;
import dbAccess.DBAccess;
import dbAccess.SelectOne;

/**
 * 在庫補充時に呼び出されるサーブレット<br>
 * ・doGet...加算対象の商品情報をDBから取得し、補充ページに遷移<br>
 * ・doPost...加算処理を呼び出し、結果表示ページに遷移
 */
@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DBAccess dbAccess;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("code") == null) {
			System.out.println("リダイレクト");
			response.sendRedirect("http://localhost:8080/webExam2/ManageServlet");
			return;
		}

		dbAccess = new SelectOne();
		try {
			dbAccess.execute(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/stock.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btn").equals("yes")) {
			dbAccess = new AddStock();
			try {
				dbAccess.execute(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message", "操作をキャンセルしました");
		}

		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/manage.jsp");
		dis.forward(request, response);
	}

}
