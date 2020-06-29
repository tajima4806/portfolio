package dbAccess;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.ItemDao;

/**
 * DBAccessインターフェースを実装する検索クラス<br>
 * DBからパラメータに受取ったID,パスワードを照合する<br>
 */
public class SelectForLogin implements DBAccess {

	@Override
	public void execute(HttpServletRequest request) throws SQLException {

		ItemDao dao = null;
		int n = 0;

		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		if(name == null || name.isEmpty() || pass == null || pass.isEmpty()) {
			request.setAttribute("message", "ユーザ名、パスワードを入力してください");
			request.setAttribute("login", "error");
			return;
		}

		try {
			dao = new ItemDao();
			n = dao.getLoginInfo(name, pass);

			switch (n) {
			case 0:
				request.setAttribute("message", "入力内容に誤りがないかご確認ください");
				request.setAttribute("login", "error");
				break;
			case 1:
				request.setAttribute("login", "admin");
				break;
			case 2:
				request.setAttribute("login", "client");
				break;
			}
		}finally {
			if(dao != null) dao.close();
		}

	}

}
