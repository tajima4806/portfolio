package dbAccess;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.ItemDao;
import dto.ItemDto;

/**
 * DBAccessインターフェースを実装する購入クラス<br>
 * DBからパラメータに受取った商品コードに対応する売上に加算する<br>
 */
public class BuyItem implements DBAccess {

	@Override
	public void execute(HttpServletRequest request) throws SQLException {
		ItemDao dao = null;
		int n = 0;

		int code = Integer.parseInt(request.getParameter("code"));

		ItemDto dto = new ItemDto();
		dto.setCode(code);

		try {
			dao = new ItemDao();
			n = dao.buyitem(dto);
			if(n > 0) {
				request.setAttribute("message", "購入が完了しました");
			}else {
				request.setAttribute("message", "購入失敗");
			}
		}finally {
			if(dao != null) dao.close();
		}
    }

}
