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
	// 신규회원 등록
	public MemberVO getMemberJoin(MemberVO mmvo) throws Exception {
		// sql이랑 연결하기위해 시그니쳐맞추고,명령어 만듦
		StringBuffer sql = new StringBuffer();
		sql.append("insert into RAHELHAIRMEMBERINFO ");
		sql.append("(M_NO,M_HP,M_NAME,M_GENDER,M_VISITMOTIVE,M_BIRTHDAY,M_RECOMM)");
		sql.append(" values (RAHELHAIRMEMBERINFO_SEQ.nextval, ?, ?, ?, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MemberVO mmVo = mmvo;

		try { // DBUtil클래스의 getConnection( )메서드에서 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 입력값을 get으로 가져와서 set으로?에 순서대로 넣어줌
			pstmt = con.prepareStatement(sql.toString());// Data Manipulation Language데이터조작언어
			pstmt.setString(1, mmvo.getM_hp());
			pstmt.setString(2, mmvo.getM_name());
			pstmt.setString(3, mmvo.getM_gender());
			pstmt.setString(4, mmvo.getM_visitMotive());
			pstmt.setDate(5, mmvo.getM_birthday());
			pstmt.setString(6, mmvo.getM_recomm());
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
		return mmVo;
	}

	// 회원의 이름을 입력받아 표에서 정보 조회
	public ArrayList<MemberVO> getMemberCheck(String search, String nameOrHp) throws Exception {
		ArrayList<MemberVO> list = new ArrayList<>();
		// 메소드 안에서 사용될 데이터 타입String
		StringBuffer sql = new StringBuffer();
		switch (nameOrHp) {
		case "이름":
			sql.append("select * from RAHELHAIRMEMBERINFO where M_NAME like ");
			sql.append("? order by M_no");
			break;
		case "HP":
			sql.append("select * from RAHELHAIRMEMBERINFO where M_HP like ");
			sql.append("? order by M_no");
			break;
		}

		// String dml;// 데이터조작언어
		Connection con = null;// 연결
		PreparedStatement pstmt = null;// DB에 쿼리를 보내기위해 필요한 객체
		ResultSet rs = null;// 결과값
		MemberVO mmVo = null;// 리턴변수값
		try {
			con = DBUtil.getConnection();// con=DB연결
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + search + "%");

			rs = pstmt.executeQuery();// select문을 실행할때 사용함
			// VO에 입력받은 값을 가져온다
			// 검색 해야함
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

	// 선택한 회원의 정보 수정
	public void getMemberUpdate(MemberVO mmvo) throws Exception {
		// ② 데이터 처리를 위한 SQL 문
		StringBuffer sql = new StringBuffer();
		sql.append("update RAHELHAIRMEMBERINFO set ");
		sql.append(" m_hp=? ,m_name=? ,m_visitMotive=? ,m_birthday=? ,m_recomm=?  ");
		sql.append(" where m_no = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try { // ③ DBUtil이라는 클래스의 getConnection( )메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// ④ 수정한 회원 정보를 수정하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mmvo.getM_hp());
			pstmt.setString(2, mmvo.getM_name());
			pstmt.setString(3, mmvo.getM_visitMotive());
			pstmt.setDate(4, mmvo.getM_birthday());
			pstmt.setString(5, mmvo.getM_recomm());
			pstmt.setString(6, mmvo.getM_no());

			// ⑤ SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();
			System.out.println(i);
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("회원정보 수정");
				alert.setHeaderText("회원정보 수정 완료.");
				alert.setContentText("회원정보 수정 성공!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("회원정보 수정");
				alert.setHeaderText("회원정보 수정 실패.");
				alert.setContentText("회원정보 수정 실패!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// ⑥ 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

	// 회원 전체리스트
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