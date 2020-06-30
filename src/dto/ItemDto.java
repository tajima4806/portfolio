package dto;

/**
 * 商品データを保持するクラス<br>
 * データ検索時のデータ管理やデータ登録時の際にも使用され、以下のフィールドを持つ<br>
 * @author user
 */

public class ItemDto {

	private int id;				//商品ID
	private int code;			//商品名
	private String name;		//商品コード
	private String category;	//カテゴリ
	private int price;			//値段
	private int stock;			//在庫
	private int sales;			//販売数


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
}
