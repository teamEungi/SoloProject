package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.MemberVO;
import model.ProductVO;

public class productDAO {
	public ProductVO getProduct(ProductVO pvo) throws Exception {
		// sql�̶� �����ϱ����� �ñ״��ĸ��߰�,��ɾ� ����
		StringBuffer sql = new StringBuffer();
		sql.append("insert into RAHELHAIRPRODUCTINFO ");
		sql.append("(P_NUM,P_NO,M_NAME,M_GENDER,I_NAME,P_DATE,I_PRICE)");
		sql.append(" values (RAHELHAIRPRODUCTINFO_SEQ.nextval,?,?,?,?,sysdate,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ProductVO pVo = pvo;

		try { // DBUtilŬ������ getConnection( )�޼��忡�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// �Է°��� get���� �����ͼ� set����?�� ������� �־���
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pvo.getP_no());
			pstmt.setString(2, pvo.getM_name());
			pstmt.setString(3, pvo.getM_gender());
			pstmt.setString(4, pvo.getI_name());
			pstmt.setString(5, pvo.getI_price());

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
		return pVo;
	}

	public ArrayList<ProductVO> getProductTotal() {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT P_NO,M_NAME,M_GENDER,SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', I_NAME) ORDER BY P_NO).EXTRACT('//text()').GETSTRINGVAL(), 2) I_NAME ,TO_CHAR(P_DATE,'YYYY-MM-DD') P_DATE, SUM(I_PRICE) I_PRICE");
		sql.append(" FROM RAHELHAIRPRODUCTINFO ");
		sql.append(" GROUP BY P_NO, TO_CHAR(P_DATE,'YYYY-MM-DD'),M_NAME,M_GENDER ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB����
			while (rs.next()) {
				pVo = new ProductVO();
				pVo.setP_no(rs.getInt("P_NO"));
				pVo.setM_name(rs.getString("M_NAME"));
				pVo.setM_gender(rs.getString("M_GENDER"));
				pVo.setI_name(rs.getString("I_NAME"));
				pVo.setI_price(rs.getString("I_PRICE"));
				pVo.setP_date(rs.getString("P_DATE"));
				
				list.add(pVo);// VO�� �ñ״��� ���� ������ �߰�
			}
		} catch (SQLException se) {
			System.out.println(se);
			se.printStackTrace();
		} catch (Exception e) {
		}
		return list;
	}

}
