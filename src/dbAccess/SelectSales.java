package dbAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.ItemDao;
import dto.ItemDto;

/**
 * DBAccessインターフェースを実装する検索クラス<br>
 * 売上を降順に並べたものをArrayListとして取得する<br>
 */
public class SelectSales implements DBAccess {
	
	@Override
    public void execute(HttpServletRequest request) throws SQLException { 
    	
    	ItemDao dao = null;
		
		try {
			dao = new ItemDao();
			ArrayList<ItemDto> list2 = dao.getItemsFromSales();
			if(list2.size() > 0) {
				request.setAttribute("list2", list2);
			}else {
				request.setAttribute("message", "まだデータがありません");
			}
		}finally {
			if(dao != null) dao.close();
		}
    }
}