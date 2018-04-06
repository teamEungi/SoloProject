package model;

public class ProductVO {
	private int p_num;// 회원번호
	private int p_no;// 회원번호
	private String m_name;// 회원번호
	private String m_gender;// 회원번호
	private String i_name;// 회원번호
	private String p_date;// 회원번호
	private String i_price;// 회원번호

	public ProductVO() {
		super();
	}

	public ProductVO(int p_num, int p_no, String m_name, String m_gender, String i_name, String p_date,
			String i_price) {
		super();
		this.p_num = p_num;
		this.p_no = p_no;
		this.m_name = m_name;
		this.m_gender = m_gender;
		this.i_name = i_name;
		this.p_date = p_date;
		this.i_price = i_price;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getI_price() {
		return i_price;
	}

	public void setI_price(String i_price) {
		this.i_price = i_price;
	}

}
