package model;

import java.sql.Date;

public class MemberVO {
	private String m_no;// ȸ����ȣ
	private String m_hp;// ��ȭ��ȣ
	private String m_name;// �̸�
	private String m_gender;// ����
	private String m_visitMotive;// ���Ե���
	private Date m_birthday;// �������
	private String m_recomm;// ��õ��

	public MemberVO() {

	}

	public MemberVO(String m_no, String m_hp, String m_name, String m_gender, String m_visitMotive, Date m_birthday,
			String m_recomm) {
		super();
		this.m_no = m_no;
		this.m_hp = m_hp;
		this.m_name = m_name;
		this.m_gender = m_gender;
		this.m_visitMotive = m_visitMotive;
		this.m_birthday = m_birthday;
		this.m_recomm = m_recomm;
	}

	public MemberVO(String m_hp, String m_name, String m_gender, String m_visitMotive, Date m_birthday,
			String m_recomm) {
		super();
		this.m_hp = m_hp;
		this.m_name = m_name;
		this.m_gender = m_gender;
		this.m_visitMotive = m_visitMotive;
		this.m_birthday = m_birthday;
		this.m_recomm = m_recomm;
	}

	public String getM_no() {
		return m_no;
	}

	public void setM_no(String m_no) {
		this.m_no = m_no;
	}

	public String getM_hp() {
		return m_hp;
	}

	public void setM_hp(String m_hp) {
		this.m_hp = m_hp;
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

	public String getM_visitMotive() {
		return m_visitMotive;
	}

	public void setM_visitMotive(String m_visitMotive) {
		this.m_visitMotive = m_visitMotive;
	}

	public Date getM_birthday() {
		return m_birthday;
	}

	public void setM_birthday(Date m_birthday) {
		this.m_birthday = m_birthday;
	}

	public String getM_recomm() {
		return m_recomm;
	}

	public void setM_recomm(String m_recomm) {
		this.m_recomm = m_recomm;
	}

}
