package sec05.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext; // 이름을 찾기 위한 작업을 위해 사용하는 클래스
import javax.naming.NamingException;
import javax.sql.DataSource; // 실질적인 어떤 데이터인지를 연결하는 객체 

public class MemberDAO {
//	필
	private Connection conn;
	private DataSource dataFactory;
	private PreparedStatement pstmt;

//	생

	public MemberDAO() {
		// JNDI방식의 연결로 MemberDAO 객체를 초기화
		try {
			Context ctx = new InitialContext(); // 컨텍스트 작업을 위한 객체
			Context envContext = (Context) ctx.lookup("java:/comp/env"); // 오라클인지 mysql인지 환경을 찾기 위한 컨텍스트
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			System.out.println("톰캣의 context.xml에 정의 되어있는 이름부분에서 정확하지 않은 에러");
//			e.printStackTrace();
		}
	}

//	메
	public List listMembers() {
		List list = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM t_member";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String userId = rs.getString("userId");
				String userPass = rs.getString("userPass");
				String userName = rs.getString("userName");
				String userEmail = rs.getString("userEmail");
				Date joinDate = rs.getDate("joinDate");
//				System.out.println(userId +userPass+userName+userEmail+joinDate);	
				MemberVO vo = new MemberVO();
				vo.setUserId(userId);
				vo.setUserPass(userPass);
				vo.setUserName(userName);
				vo.setUserEmail(userEmail);
				vo.setJoinDate(joinDate);

				list.add(vo);
			}

			rs.close(); // 자원이므로 닫아줘야함
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	private void connDB() {} // connectionDB이므로 미리 연결 객체를 만들어 놨기 때문에 필요 없음

	public void addMember(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into t_member";
			query += "(userId,userPass,userName,userEmail)";
			query += " values(?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getUserId());
			pstmt.setString(2, memberVO.getUserPass());
			pstmt.setString(3, memberVO.getUserName());
			pstmt.setString(4, memberVO.getUserEmail());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String userId) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from t_member where userId = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String userId = memberVO.getUserId();
		String userPass = memberVO.getUserPass();
		try {
			conn = dataFactory.getConnection();
			String query = "select decode(count(*),1,'true','false') as result from t_member where userId=? and userPass=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPass);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}