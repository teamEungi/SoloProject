package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ItemVO;
import model.MemberVO;

public class ItemDAO {
	// Database Acception Object
	
	// ��Ÿ��ư
	public ArrayList<ItemVO> getEtc() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='etc.' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// ����̹�ư
	public ArrayList<ItemVO> getDry() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='�����' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// Ŭ���й�ư
	public ArrayList<ItemVO> getClinic() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='Ŭ����' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// Coler��ư
	public ArrayList<ItemVO> getColer() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='����' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// Purm��ư
	public ArrayList<ItemVO> getPurm() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='��' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// cut��ư
	public ArrayList<ItemVO> getCut() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='ĿƮ' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// �޴� ��ü ��ȸ ����Ʈ
	// �迭�� DB���� �ִ´�
	public ArrayList<ItemVO> getItemTotal() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	public ItemVO getItemAdd(ItemVO itVo) throws Exception {
		// sql�̶� �����ϱ����� �ñ״��ĸ��߰�,��ɾ� ����
		StringBuffer sql = new StringBuffer();
		sql.append("insert into RAHELHAIRITEMEINFO ");
		sql.append("(I_NO,I_NAME,I_PRICE,I_CATEGORY)");
		sql.append(" values (RAHELHAIRITEMEINFO_SEQ.nextval, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ItemVO itvo = itVo;

		try { // DBUtilŬ������ getConnection( )�޼��忡�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �Է°��� get(VO�̸�)���� �����ͼ� set����?�� ������� �־���
			pstmt = con.prepareStatement(sql.toString());// Data Manipulation Language���������۾��
			pstmt.setString(1, itvo.getI_name());
			pstmt.setString(2, itvo.getI_price());
			pstmt.setString(3, itvo.getI_category());

			// pstmt�� Update�� sql�� ������
			// int i�� ���� �ùٸ��� ������1�ƴϸ�0�� ��µ�. �־��� catch�� ����ִ� �������
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try { // �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					// Connection=con�� ���´�
					// PreparedStatement=pstmt�� ���´�
				if (pstmt != null)
					pstmt.close();// Statement������ ��ȯ�Ҷ� ����Ѵ�
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return itVo;
	}

	public void getItemDelete(String i_no) throws Exception {
		// sql�̶� �����ϱ����� �ñ״��ĸ��߰�,��ɾ� ����
		StringBuffer sql = new StringBuffer();
		sql.append("delete from RAHELHAIRITEMEINFO ");
		sql.append("where I_NO=?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// sql���� ����Ű�� no�� �����ͼ� �˻�
			pstmt.setString(1, i_no);
			// int i�� ���� �ùٸ��� ������1�ƴϸ�0�� ��µ�.��� catch�� ����ִ� �������
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try { // �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					// Connection=con�� ���´�
					// PreparedStatement=pstmt�� ���´�
				if (pstmt != null)
					pstmt.close();// Statement������ ��ȯ�Ҷ� ����Ѵ�
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}
}
