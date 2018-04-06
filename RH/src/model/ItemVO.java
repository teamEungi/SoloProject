package model;

public class ItemVO {
	private String i_no;// 메뉴번호
	private String i_name;// 메뉴명
	private String i_price;// 메뉴가격
	private String i_category;// 카테고리....안씀 왜있는겨

	public ItemVO() {
	}

	public ItemVO(String i_no, String i_name, String i_price, String i_category) {
		super();
		this.i_no = i_no;
		this.i_name = i_name;
		this.i_price = i_price;
		this.i_category = i_category;
	}

	public ItemVO(String i_name, String i_price, String i_category) {
		super();
		this.i_name = i_name;
		this.i_price = i_price;
		this.i_category = i_category;
	}

	public String getI_no() {
		return i_no;
	}

	public void setI_no(String i_no) {
		this.i_no = i_no;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public String getI_price() {
		return i_price;
	}

	public void setI_price(String i_price) {
		this.i_price = i_price;
	}

	public String getI_category() {
		return i_category;
	}

	public void setI_category(String i_category) {
		this.i_category = i_category;
	}

}
