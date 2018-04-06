package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MemberVO;

public class MemberDAO {
	// Database Acception Object
	// �ű�ȸ�� ���
	public MemberVO getMemberJoin(MemberVO mmvo) throws Exception {
		// sql�̶� �����ϱ����� �ñ״��ĸ��߰�,��ɾ� ����
		StringBuffer sql = new StringBuffer();
		sql.append("insert into RAHELHAIRMEMBERINFO ");
		sql.append("(M_NO,M_HP,M_NAME,M_GENDER,M_VISITMOTIVE,M_BIRTHDAY,M_RECOMM)");
		sql.append(" values (RAHELHAIRMEMBERINFO_SEQ.nextval, ?, ?, ?, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO mmVo = mmvo;

		try { // DBUtilŬ������ getConnection( )�޼��忡�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �Է°��� get���� �����ͼ� set����?�� ������� �־���
			pstmt = con.prepareStatement(sql.toString());// Data Manipulation Language���������۾��
			pstmt.setString(1, mmvo.getM_hp());
			pstmt.setString(2, mmvo.getM_name());
			pstmt.setString(3, mmvo.getM_gender());
			pstmt.setString(4, mmvo.getM_visitMotive());
			pstmt.setDate(5, mmvo.getM_birthday());
			pstmt.setString(6, mmvo.getM_recomm());
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
		return mmVo;
	}

	// ȸ���� �̸��� �Է¹޾� ǥ���� ���� ��ȸ
	public ArrayList<MemberVO> getMemberCheck(String search, String nameOrHp) throws Exception {
		ArrayList<MemberVO> list = new ArrayList<>();
		// �޼ҵ� �ȿ��� ���� ������ Ÿ��String
		StringBuffer sql = new StringBuffer();
		switch (nameOrHp) {
		case "�̸�":
			sql.append("select * from RAHELHAIRMEMBERINFO where M_NAME like ");
			sql.append("? order by M_no");
			break;
		case "HP":
			sql.append("select * from RAHELHAIRMEMBERINFO where M_HP like ");
			sql.append("? order by M_no");
			break;
		}

		// String dml;// ���������۾��
		Connection con = null;// ����
		PreparedStatement pstmt = null;// DB�� ������ ���������� �ʿ��� ��ü
		ResultSet rs = null;// �����
		MemberVO mmVo = null;// ���Ϻ�����
		try {
			con = DBUtil.getConnection();// con=DB����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + search + "%");

			rs = pstmt.executeQuery();// select���� �����Ҷ� �����
			// VO�� �Է¹��� ���� �����´�
			// �˻� �ؾ���
			while (rs.next()) {
				mmVo = new MemberVO();
				mmVo.setM_no(rs.getString("M_no"));
				mmVo.setM_hp(rs.getString("M_hp"));
				mmVo.setM_name(rs.getString("M_name"));
				mmVo.setM_gender(rs.getString("M_gender"));
				mmVo.setM_visitMotive(rs.getString("M_visitMotive"));
				mmVo.setM_birthday(rs.getDate("M_birthday"));
				mmVo.setM_recomm(rs.getString("M_recomm"));

				list.add(mmVo);

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {

			}
		}
		return list;
	}

	// ������ ȸ���� ���� ����
	public void getMemberUpdate(MemberVO mmvo) throws Exception {
		// �� ������ ó���� ���� SQL ��
		StringBuffer sql = new StringBuffer();
		sql.append("update RAHELHAIRMEMBERINFO set ");
		sql.append(" m_hp=? ,m_name=? ,m_visitMotive=? ,m_birthday=? ,m_recomm=?  ");
		sql.append(" where m_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try { // �� DBUtil�̶�� Ŭ������ getConnection( )�޼���� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �� ������ ȸ�� ������ �����ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mmvo.getM_hp());
			pstmt.setString(2, mmvo.getM_name());
			pstmt.setString(3, mmvo.getM_visitMotive());
			pstmt.setDate(4, mmvo.getM_birthday());
			pstmt.setString(5, mmvo.getM_recomm());
			pstmt.setString(6, mmvo.getM_no());

			// �� SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();
			System.out.println(i);
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ȸ������ ����");
				alert.setHeaderText("ȸ������ ���� �Ϸ�.");
				alert.setContentText("ȸ������ ���� ����!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ȸ������ ����");
				alert.setHeaderText("ȸ������ ���� ����.");
				alert.setContentText("ȸ������ ���� ����!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

	// ȸ�� ��ü����Ʈ
	public ArrayList<MemberVO> getMemberTotal() {

		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRMEMBERINFO order by M_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO mmVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mmVo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getDate(6), rs.getString(7));
				list.add(mmVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {

		}
		return list;
	}
}