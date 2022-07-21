package com.inbook.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inbook.member.vo.MemberVO;
import com.inbook.util.db.DB;
import com.inbook.friend.vo.FriendVO;
import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;

public class MemberDAO {


	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<MemberVO> list(PageObject pageObject) throws Exception {
		
		List<MemberVO> list = null;
		
		try {
			
			con = DB.getConnection();
			
			String sql = " select m.id, m.name, m.gender, to_char(m.birth, 'yyyy-mm-dd') birth, "
					+ " m.tel, m.status, m.photo, m.gradeNo, g.gradeName "
					+ " from member m, grade g where m.gradeNo = g.gradeNo order by id ";
			
			sql = " select rownum rnum, id, name, gender, birth, tel, status, photo, gradeNo, gradeName from(" + sql + " ) ";
			
			sql = " select rnum, id, name, gender, birth, tel, status, photo, gradeNo, gradeName from ( "
					+ sql + " ) where rnum between ? and ? ";
			
			System.out.println("MemberDAO.list().sql : " + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				
				while (rs.next()) {
					if(list == null) list = new ArrayList<MemberVO>();
					MemberVO vo = new MemberVO();
					
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setStatus(rs.getString("status"));
					vo.setPhoto(rs.getString("photo"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
				
					list.add(vo);
					
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception("회원 리스트 데이터처리중 오류 발생" + e.getMessage());
		}	finally {
			
			try {
				
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
				throw new Exception ("회원 리스트 객체 닫는중 오류 발생 " +e.getMessage());
				
				
			}
			
			
			
		}
		return list;
	}
		
		
		
		
		public Long getTotalRow(PageObject pageObject) throws Exception{
			Long totalRow = 0L;
			
			try {
				con = DB.getConnection();
				
				String sql = "SELECT count(*) FROM member ";
				
				System.out.println("MemberDAO.getTotalRow().sql : " + sql);
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs != null && rs.next()) {
				
					totalRow = rs.getLong(1);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
				throw new Exception("회원 전체 데이터 개수 DB 처리 중 오류 발생 - " + e.getMessage());
			} finally {
				try {
				
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
					if(rs != null) rs.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				
					throw new Exception("회원 전체 데이터 개수 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
				}
			}
			
			return totalRow;
		}

		
		public MemberVO view(String id) throws Exception{
			
			MemberVO vo = null;
			
			try {
				
				con = DB.getConnection();
		
				String sql = " select m.id, m.name, m.gender, to_char(m.birth, 'yyyy-mm-dd') birth, "
						+ " m.tel, m.email, m.regDate, m.conDate, m.status, m.photo, m.gradeNo, g.gradeName "
						+ " from member m, grade g "
						+ " where (id = ?) and (m.gradeNo = g.gradeNo) order by id ";
				System.out.println("MemberDAO.view().sql : " + sql);
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);

				
				rs = pstmt.executeQuery();

				if(rs != null && rs.next()) {
					
					vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setEmail(rs.getString("email"));
					vo.setRegDate(rs.getString("regDate"));
					vo.setConDate(rs.getString("conDate"));
					vo.setStatus(rs.getString("status"));
					vo.setPhoto(rs.getString("photo"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
				throw new Exception("회원 정보 보기 DB 처리 중 오류 발생 - " + e.getMessage());
			} finally {
				try {
					
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
					if(rs != null) rs.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					
					throw new Exception("회원 정보 보기 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
				}
			}
			
			return vo;
		}


		public Integer update(MemberVO vo) throws Exception{
		
			Integer result = 0;
			
			
			try {
				
				con = DB.getConnection();
				
				String sql = "UPDATE member SET pw = ?, name = ?, gender = ?, birth = ?, tel = ?, email = ?, photo = ? WHERE id = ?";
				
				pstmt = con.prepareStatement(sql);	
				pstmt.setString(1, vo.getPw());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getGender());
				pstmt.setString(4, vo.getBirth());
				pstmt.setString(5, vo.getTel());
				pstmt.setString(6, vo.getEmail());
				pstmt.setString(7, vo.getPhoto());
				pstmt.setString(8, vo.getId());
				
				result = pstmt.executeUpdate();
				
				if(result >= 1)
					System.out.println("회원 수정 완료");
				else
					System.out.println("회원 수정 DB 처리가 되지 않았습니다. 글번호를 확인해 주세요.");
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new Exception("회원 수정 DB 처리 중 오류. - " + e.getMessage());
			} finally {
				try {
				
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new Exception("회원 수정 객체를 닫는 중 오류. - " + e.getMessage());
				}
			}
			
			return result;
		}


		
		public Integer write(MemberVO vo) throws Exception{
			
			Integer result = 0;
			
			
			try {
				
				con = DB.getConnection();
				
				// ??? 값은 모르는값으로 직접 입력해야할 때
				String sql = "INSERT INTO member(id, pw, name, gender, birth, tel, email, photo) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				System.out.println("MemberDAO.update().sql - " + sql);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPw());
				pstmt.setString(3, vo.getName());
				pstmt.setString(4, vo.getGender());
				pstmt.setString(5, vo.getBirth());
				pstmt.setString(6, vo.getTel());
				pstmt.setString(7, vo.getEmail());
				pstmt.setString(8, vo.getPhoto());
			
				result = pstmt.executeUpdate();
				
				System.out.println("회원가입 DB 처리 완료");
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new Exception("회원가입 DB 처리 중 오류. - " + e.getMessage());
			} finally {
				try {
					
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new Exception("회원가입 객체를 닫는 중 오류. - " + e.getMessage());
				}
			}
			
			return result;
		}

		
		public Integer delete(MemberVO vo) throws Exception{
			// return 타입과 동일한변수 선언
			Integer result = 0;
			
			// 데이터처리
			try {
				// 1. 드라이버 확인
				con = DB.getConnection();
//				System.out.println("연결 완료");
				// 3. SQL 작성
				String sql = " DELETE from member where id = ?";
				// 4. 실행객체 & 데이터 세팅
				System.out.println("쿼리: " +sql);
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, vo.getId());
				
				// 5. 실행
				// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
				result = pstmt.executeUpdate();
				// 6. 표시 또는 담기
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("친구 삭제 DB 처리 중 오류. - " + e.getMessage());
			} finally {
				try {
					// 7. 닫기
					//  - commit 까지 완료하고 나온다. - auto commit
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
				} catch (Exception e) {
					// 개발자를 위한 코드
					e.printStackTrace();
					throw new Exception("친구 삭제 객체를 닫는 중 오류. - " + e.getMessage());
				}
			}
			
			return result;
		}

		
		
		public LoginVO login(LoginVO loginVO) throws Exception{
			
			LoginVO vo = null;
			
			System.out.println("MemberDAO.login().loginVO : " + loginVO + " --------------------");
			
			try {
				
				con = DB.getConnection();
			
				String sql = " select m.id, m.name, m.gradeNo, g.gradeName, m.photo from member m, grade g ";
				sql += " where (id = ? and pw = ? and status = '정상') and (m.gradeNo = g.gradeNo) ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, loginVO.getId());
				pstmt.setString(2, loginVO.getPw());
				System.out.println("실행 객체 생성 완료");
			
				rs = pstmt.executeQuery();
				System.out.println("DB 쿼리 실행 완료");
				
				if(rs != null && rs.next()) {
					
					vo = new LoginVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setPhoto(rs.getString("photo"));
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
				throw new Exception("로그인 DB 처리 중 오류 발생 - " + e.getMessage());
			} finally {
				try {
					
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
					if(rs != null) rs.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					
					throw new Exception("로그인 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
				}
			}
			
			
			System.out.println(vo);
			
			return vo;
		}

		
		public Integer gradeUpdate(MemberVO vo) throws Exception{
			
			Integer result = 0;
			
			
			try {
				
				con = DB.getConnection();
				
				String sql = "UPDATE member SET gradeNo = ? WHERE id = ?";
				System.out.println("MemberDAO.gradeUpdate().sql : " + sql);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getGradeNo());
				pstmt.setString(2, vo.getId());
				
				result = pstmt.executeUpdate();
				
				if(result >= 1)
					System.out.println("회원등급 수정 DB 처리 완료");
				else
					System.out.println("회원등급 수정 DB 처리가 되지 않았습니다. 아이디를 확인해 주세요.");
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new Exception("회원등급 수정 DB 처리 중 오류. - " + e.getMessage());
			} finally {
				try {
					
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new Exception("회원등급 수정 객체를 닫는 중 오류. - " + e.getMessage());
				}
			}
			
			return result;
		}

		
		public Integer statusUpdate(MemberVO vo) throws Exception{
			
			Integer result = 0;
			
			
			try {
				
				con = DB.getConnection();
				
				String sql = "UPDATE member SET status = ? WHERE id = ?";
				System.out.println("MemberDAO.gradeUpdate().sql : " + sql);
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getStatus());
				pstmt.setString(2, vo.getId());
				
				result = pstmt.executeUpdate();
				
				if(result >= 1)
					System.out.println("회원상태 수정 DB 처리 완료");
				else
					System.out.println("회원상태 수정 DB 처리가 되지 않았습니다. 아이디를 확인해 주세요.");
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new Exception("회원상태 수정 DB 처리 중 오류. - " + e.getMessage());
			} finally {
				try {
				
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new Exception("회원상태 수정 객체를 닫는 중 오류. - " + e.getMessage());
				}
			}
			
			return result;
		}

		
		
		public String idCheck(String id) throws Exception{
		
			String resultId = null;
			
			try {
		
				con = DB.getConnection();

				String sql = " select id from member where id = ?";
				System.out.println("MemberDAO.idCheck().sql : " + sql);
				
				pstmt = con.prepareStatement(sql);
		
				pstmt.setString(1, id);

		
				rs = pstmt.executeQuery();

				if(rs != null && rs.next()) {
					resultId = rs.getString("id");
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
				throw new Exception("아이디 중복 체크 DB 처리 중 오류 발생 - " + e.getMessage());
			} finally {
				try {
					
					if(con != null) con.close();
					if(pstmt != null) pstmt.close();
					if(rs != null) rs.close();
				} catch (Exception e) {
					
					e.printStackTrace();
					
					throw new Exception("아이디 중복 체크 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
				}
			}
			
			return resultId;
		}
}

