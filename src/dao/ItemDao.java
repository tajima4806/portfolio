package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ItemDto;

/**
 * DBとの接続、操作、切断を処理するクラス<br>
 * Dao...Data Access Objectの略
 * @author user
 *
 */
public class ItemDao {

	private Connection con;
	private String sql;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList<ItemDto> list = null;

	/**
	 * testdbに接続するためのコンストラクタ
	 * @throws SQLException
	 */
	public ItemDao() throws SQLException{
		String url= "jdbc:mysql://localhost:3306/javaexam?serverTimezone=UTC";
		String user = "root";
		String pass = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * DB接続を切断する
	 */
	public void close() {
		try {
			if(con != null) con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * ログイン時のデータ照合
	 * @param name 名前
	 * @param pass　パスワード
	 * @return ログイン成功時...1 <br>ログイン失敗時...0
	 * @throws SQLException
	 */
	public int getLoginInfo(String name, String pass) throws SQLException{

		int row = 0;
		sql = "SELECT * from user where name = ? and password = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, pass);

		try {
			rs = ps.executeQuery();

			if (rs.next()) {
				row = rs.getInt("id");
			}
		}finally {
			ps.close();
		}
		return row;
	}

	/**
	 * DBから全データを抽出する
	 * @return 全データを保持するリスト
	 * @throws SQLException
	 */
	public ArrayList<ItemDto> getItemsAll() throws SQLException{

		sql = "select * from item";
		ps = con.prepareStatement(sql);
		return search(ps);
	}

	/**
	 * DBから1レコードを抽出する(商品コードで検索)
	 * @param code 抽出したい商品コード
	 * @return 該当データ
	 * @throws SQLException
	 */
	public ItemDto getItem(int code) throws SQLException{

		sql = "select * from item where code = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, code);
		return search(ps).get(0);
	}

	public ArrayList<ItemDto> getItemsFromSales() throws SQLException{

		sql = "select id, code, name, category, sales from item order by sales desc limit 5";
		ps = con.prepareStatement(sql);
		return search2(ps);
	}

	public ArrayList<ItemDto> getItemsMulti(String name, String category, int p1, int p2, boolean flag) throws SQLException {

		StringBuilder sb = new StringBuilder("select * from item where 1 = 1");
		List<String> parameters = new ArrayList<>();

		if (!name.equals("")) {
			sb.append(" And name like ?");
			parameters.add("name");
		}
		if (!category.equals("default")) {
			sb.append(" And category = ?");
			parameters.add("category");
		}
		if (p1 != 404) {
			if (flag) {
				sb.append(" And price >= 10000");
			} else {
				sb.append(" And price between ? and ?");
				parameters.add("price");
			}
		}
		sql = sb.toString();
		ps = con.prepareStatement(sql);

		int columnIndex = 0;
		if (parameters.contains("name")) {
			ps.setString(++columnIndex, "%" + name + "%");
		}
		if (parameters.contains("category")) {
			ps.setString(++columnIndex, category);
		}
		if (parameters.contains("price")) {
			ps.setInt(++columnIndex, p1);
			ps.setInt(++columnIndex, p2);
		}
		System.out.println(sql);
		return search(ps);
	}

	/**
	 * select文を実行するメソッド
	 * @param ps パラメータがセットされたSQLを持つオブジェクト
	 * @return 検索にヒットしたデータを持つリスト
	 * @throws SQLException
	 */
	private ArrayList<ItemDto> search(PreparedStatement ps) throws SQLException {

		try {
			rs = ps.executeQuery();
			list = new ArrayList<>();
			ItemDto dto;
			while(rs.next()) {
				dto = new ItemDto();
				dto.setId(rs.getInt("id"));
				dto.setCode(rs.getInt("code"));
				dto.setName(rs.getString("name"));
				dto.setCategory(parseCategory(rs.getString("category")));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				list.add(dto);
			}
		}finally {
			ps.close();
		}
		return list;
	}

	private ArrayList<ItemDto> search2(PreparedStatement ps) throws SQLException {

		try {
			rs = ps.executeQuery();
			list = new ArrayList<>();
			ItemDto dto;
			while(rs.next()) {
				dto = new ItemDto();
				dto.setId(rs.getInt("id"));
				dto.setCode(rs.getInt("code"));
				dto.setName(rs.getString("name"));
				dto.setCategory(parseCategory(rs.getString("category")));
				dto.setSales(rs.getInt("sales"));
				list.add(dto);
			}
		}finally {
			ps.close();
		}
		return list;
	}

	/**
	 * 画面から受け取ったデータをDBに挿入するメソッド
	 * @param dto (パラメータをまとめてもつオブジェクト)
	 * @return 成功件数
	 * @throws SQLException
	 */
	public int insert(ItemDto dto) throws SQLException {
		sql = "INSERT INTO item (code, name, category, price, stock, sales) VALUES (?, ?, ?, ?, ?, 0)";
		int n = 0;

		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1, dto.hashCode());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getCategory());
			ps.setInt(4, dto.getPrice());
			ps.setInt(5, dto.getStock());

			n = ps.executeUpdate();
		}finally {
			ps.close();
		}
		return n;
	}

	/**
	 * DBへの更新処理
	 * @param dto 既存商品の更新情報を持つオブジェクト
	 * @return 成功件数
	 * @throws SQLException
	 */
	public int update(ItemDto dto) throws SQLException {

		sql = "UPDATE item set name = ?, category = ?, price = ?, stock = ? where code = ?";
		int n = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getCategory());
			ps.setInt(3, dto.getPrice());
			ps.setInt(4, dto.getStock());
			ps.setInt(5, dto.getCode());

			n = ps.executeUpdate();
		}finally {
			ps.close();
		}
		return n;
	}

	/**
	 * DBへの加算処理
	 * @param dto 既存商品の更新情報を持つオブジェクト
	 * @return 成功件数
	 * @throws SQLException
	 */
	public int addstock(ItemDto dto) throws SQLException {

		sql = "UPDATE item set stock = stock + ? where code = ?";
		int n = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getStock());
			ps.setInt(2, dto.getCode());

			n = ps.executeUpdate();
		}finally {
			ps.close();
		}
		return n;
	}

	/**
	 * DBへの加算処理
	 * @param dto 既存商品の更新情報を持つオブジェクト
	 * @return 成功件数
	 * @throws SQLException
	 */
	public int buyitem(ItemDto dto) throws SQLException {

		sql = "UPDATE item set stock = stock - 1, sales = sales + 1 where code = ?";
		int n = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getCode());

			n = ps.executeUpdate();
		}finally {
			ps.close();
		}
		return n;
	}

	/**
	 * DBへの削除処理
	 * @param code 商品コード
	 * @return 成功件数
	 * @throws SQLException
	 */
	public int delete(int code) throws SQLException {

		sql = "DELETE from item where code = ?";
		int n = 0;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, code);
			n = ps.executeUpdate();
		}finally {
			ps.close();
		}

		return n;
	}

	/**
	 * カテゴリを変換するメソッド
	 * @param category DB用カテゴリ名
	 * @return 画面用カテゴリ名
	 */
	private String parseCategory(String category) {

		String ctgr = null;

		switch(category) {
		case "general":
			ctgr = "雑貨";
			break;
		case "electric":
			ctgr = "家電";
			break;
		case "book":
			ctgr = "書籍";
			break;
		case "food":
			ctgr = "食品";
			break;
		case "fashion":
			ctgr = "ファッション";
			break;
		default:
			ctgr = "未分類";
		}

		return ctgr;
	}
}
