package dbAccess;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.ItemDao;
import dto.ItemDto;
import filter.Judge;

/**
 * DBAccessインターフェースを実装する在庫補充クラス<br>
 * DBからパラメータに受取った商品コードに対応する在庫に加算する<br>
 */
public class AddStock implements DBAccess {

	@Override
	public void execute(HttpServletRequest request) throws SQLException { 
		ItemDao dao = null;
		int n = 0;
		
		int code = Integer.parseInt(request.getParameter("code"));
		String stock = request.getParameter("stock");
		
		if(stock == null || stock.isEmpty() || !Judge.isNumber(stock)) {
			request.setAttribute("message", "入力が不正です");
			return;
		}
		
		ItemDto dto = new ItemDto();
		dto.setCode(code);
		dto.setStock(Integer.parseInt(stock));
		
		try {
			dao = new ItemDao();
			n = dao.addstock(dto);
			if(n > 0) {
				request.setAttribute("message", "補充完了");
			}else {
				request.setAttribute("message", "補充失敗");
			}
		}finally {
			if(dao != null) dao.close();
		}
    }

}
