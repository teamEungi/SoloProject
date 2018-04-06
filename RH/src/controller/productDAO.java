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
		// sql이랑 연결하기위해 시그니쳐맞추고,명령어 만듦
		StringBuffer sql = new StringBuffer();
		sql.append("insert into RAHELHAIRPRODUCTINFO ");
		sql.append("(P_NUM,P_NO,M_NAME,M_GENDER,I_NAME,P_DATE,I_PRICE)");
		sql.append(" values (RAHELHAIRPRODUCTINFO_SEQ.nextval,?,?,?,?,sysdate,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ProductVO pVo = pvo;

		try { // DBUtil클래스의 getConnection( )메서드에서 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 입력값을 get으로 가져와서 set으로?에 순서대로 넣어줌
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pvo.getP_no());
			pstmt.setString(2, pvo.getM_name());
			pstmt.setString(3, pvo.getM_gender());
			pstmt.setString(4, pvo.getI_name());
			pstmt.setString(5, pvo.getI_price());

			// pstmt를 Update로 sql에 날려줌
			// int i는 값이 올바르게 나오면1아니면0이 출력됨. 있어도없어도 catch가 잡아주니 상관없음
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try { // 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
					// Connection=con을 끊는다
					// PreparedStatement=pstmt를 끊는다
				if (pstmt != null)
					pstmt.close();// Statement객제를 반환할때 사용한다
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
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				pVo = new ProductVO();
				pVo.setP_no(rs.getInt("P_NO"));
				pVo.setM_name(rs.getString("M_NAME"));
				pVo.setM_gender(rs.getString("M_GENDER"));
				pVo.setI_name(rs.getString("I_NAME"));
				pVo.setI_price(rs.getString("I_PRICE"));
				pVo.setP_date(rs.getString("P_DATE"));
				
				list.add(pVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
			se.printStackTrace();
		} catch (Exception e) {
		}
		return list;
	}

}
