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
	
	// 기타버튼
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
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// 드라이버튼
	public ArrayList<ItemVO> getDry() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='드라이' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// 클리닉버튼
	public ArrayList<ItemVO> getClinic() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='클리닉' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// Coler버튼
	public ArrayList<ItemVO> getColer() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='염색' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// Purm버튼
	public ArrayList<ItemVO> getPurm() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='펌' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// cut버튼
	public ArrayList<ItemVO> getCut() {
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from RAHELHAIRITEMEINFO ");
		sql.append("WHERE I_CATEGORY='커트' order by I_no");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO itVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	// 메뉴 전체 조회 리스트
	// 배열에 DB값을 넣는다
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
			rs = pstmt.executeQuery();// DB연결
			while (rs.next()) {
				itVo = new ItemVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(itVo);// VO에 시그니쳐 맞춘 정보를 추가
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		}
		return list;
	}

	public ItemVO getItemAdd(ItemVO itVo) throws Exception {
		// sql이랑 연결하기위해 시그니쳐맞추고,명령어 만듦
		StringBuffer sql = new StringBuffer();
		sql.append("insert into RAHELHAIRITEMEINFO ");
		sql.append("(I_NO,I_NAME,I_PRICE,I_CATEGORY)");
		sql.append(" values (RAHELHAIRITEMEINFO_SEQ.nextval, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		ItemVO itvo = itVo;

		try { // DBUtil클래스의 getConnection( )메서드에서 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 입력값을 get(VO이름)으로 가져와서 set으로?에 순서대로 넣어줌
			pstmt = con.prepareStatement(sql.toString());// Data Manipulation Language데이터조작언어
			pstmt.setString(1, itvo.getI_name());
			pstmt.setString(2, itvo.getI_price());
			pstmt.setString(3, itvo.getI_category());

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
		return itVo;
	}

	public void getItemDelete(String i_no) throws Exception {
		// sql이랑 연결하기위해 시그니쳐맞추고,명령어 만듦
		StringBuffer sql = new StringBuffer();
		sql.append("delete from RAHELHAIRITEMEINFO ");
		sql.append("where I_NO=?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			// sql에서 유일키인 no를 가져와서 검색
			pstmt.setString(1, i_no);
			// int i는 값이 올바르게 나오면1아니면0이 출력됨.없어도 catch가 잡아주니 상관없음
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
	}
}
