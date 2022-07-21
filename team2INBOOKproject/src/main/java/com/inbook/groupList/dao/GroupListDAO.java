package com.inbook.groupList.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inbook.board.vo.BoardVO;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.util.db.DB;
import com.webjjang.util.PageObject;

// 게시판 데이터베이스 처리를 하는 객체 - Controller - Service - [DAO]
public class GroupListDAO {

	// DB 연결 정보
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@DESKTOP-TEAM2:1521:xe";
	String uid = "team02";
	String upw = "team02";
	
	// DB 처리를 위해 필요한 객체
	// 연결 객체
	Connection con = null;
	// 실행 객체
	PreparedStatement pstmt = null;
	// DB에서 가져온 데이터를 저장하는 객체
	ResultSet rs = null;
	
	
	// 그룹 리스트 - 완료
	public List<GroupListVO> list(PageObject pageObject) throws Exception {
		
		// 리턴 파입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		List<GroupListVO> list = null;
		
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. 실행 쿼리 작성
			//  - list 데이터를 여러개의 데이터를 가져온다. - 최근 글이 맨 앞에 보이도록 가져온다.
			//    1) 원본 데이터 가져오기
			String sql = "select no, title, content, id, to_char(writeDate, 'yyyy-mm-dd') writeDate, hashtag, fileName, hit "
					+ " from groupList ";
			// 검색어가 있는 경우 검색을 붙인다.
			sql += getCondition(pageObject);
			sql += " order by no desc ";
			//    2) 순서 번호를 붙인다.
			sql = " select rownum rnum, no, title, content, id, writeDate, hashtag, fileName, hit from(" + sql +") ";
			//    3) 1페이지에 해당되는 데이터 가져오기
			sql = " select rnum, no, title, content, id, writeDate, hashtag, fileName, hit from (" + sql + ") "
					+ " where rnum between ? and ? ";
			System.out.println(sql);
			
			// 4. 실행객체 & 데이터 세팅 - 1페이지 데이터
			int idx = 0;
			pstmt = con.prepareStatement(sql);
			// 검색어가 있는 경우 검색 데이터를 붙인다.
			idx = setData(pageObject, pstmt, idx);
			
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
//			System.out.println("실행 객체 생성 완료");
			
			// 5. 실행
			rs = pstmt.executeQuery();
//			System.out.println("DB 쿼리 실행 완료");
			
			// 6. 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터를 있는(rs.next()) 만큼 반복처리(while()) 해서 list에 담는다.
			if(rs != null) {
				// 데이터가 있는 만큼 반복처리
				while(rs.next()) {
					// list가 null이면 클래스 생성해준다. List 인터페이스이므로 상속받은 클래스를 생성해서 넣어 주셔야 한다.
					if(list == null) list = new ArrayList<GroupListVO>();
					GroupListVO vo = new GroupListVO();
					// rs -> vo에 저장
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHashtag(rs.getString("hashtag"));
					vo.setFileName(rs.getString("FileName"));
					//vo.setState(rs.getString("State"));
//					vo.setHit(rs.getLong("hit"));
					
					// vo -> list에 저장
					list.add(vo);
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위한 코드
			e.printStackTrace();
			// 메시지를 담은 예외 생성해서 다시 던진다.
			throw new Exception("그룹 리스트 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
			// 만약에 발생된 예외를 그대로 넘기려고 한다면 throw new Exception(e);
		} finally {
			// 예외처리를 한다.
			try {
				// 7.사용한 객체를 닫는다.
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 코드
				e.printStackTrace();
				// 메시지를 담은 예외 생성해서 다시 던진다.
				throw new Exception("그룹 리스트 데이터를 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		// list에 담겨 있는 데이터 확인
//		System.out.println(list);
		
		return list;
		
	}
	
	
	// 검색 조건 문장을 만들어 내는 메서드 - PageObject.word에 따라
	public String getCondition(PageObject pageObject) {
		String str = "";
		
		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			str += " where 1=0 ";
			if(pageObject.getKey().indexOf("t") >= 0)
				str += " or title like ? ";
			if(pageObject.getKey().indexOf("c") >= 0)
				str += " or content like ? ";
			if(pageObject.getKey().indexOf("w") >= 0)
				str += " or writer like ? ";
		}
		
		return str;
	}
	
	// 검색 데이터를 세팅하는 메서드
	public int setData(PageObject pageObject, PreparedStatement pstmt, int idx) throws Exception {
		
		// 검색어가 있으면 데이터 세팅
		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			if(pageObject.getKey().indexOf("t") >= 0)
				pstmt.setString(++idx, "%" + pageObject.getWord() + "%");
			if(pageObject.getKey().indexOf("c") >= 0)
				pstmt.setString(++idx, "%" + pageObject.getWord() + "%");
			if(pageObject.getKey().indexOf("w") >= 0)
				pstmt.setString(++idx, "%" + pageObject.getWord() + "%");
		}
		return idx;
	}
	
	// 그룹 전체 데이터 개수 가져오기
	public Long getTotalRow(PageObject pageObject) throws Exception{

		// 리턴 타입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		Long totalRow = 0L;
		
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. sql 작성 - 변경되는 데이터는 ? (대체문자)로 작성
			String sql = "SELECT count(*) FROM board ";
			sql += getCondition(pageObject);
			// 4. 실행 객체 & 데이터세팅 - no
			pstmt = con.prepareStatement(sql);
			// 조건이 있으면 데이터를 세팅한다.
			int idx = 0;
			idx = setData(pageObject, pstmt, idx);
			//   데이터 타입에 따른 메서드를 선택해서 세팅해준다.
			//System.out.println("실행 객체 생성 완료");
			// 5. 실행 
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			rs = pstmt.executeQuery();
			// System.out.println("DB 쿼리 실행 완료");
			// 6. 데이터 표시나 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터가 있는 경우(if) 데이터 가져온다.
			if(rs != null && rs.next()) {
				// 위에서 선언한 vo 객체를 생성하고 데이터를 담는다.
				totalRow = rs.getLong(1);
			}
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			// Controller에서 예외처리를 시키기 위해서 예외를 생성하고 던진다.
			throw new Exception("그룹  개수 DB 처리 중 오류 발생 - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				// Controller에서 예외처리를 시키기 위해서 예외를 생성하고 던진다.
				throw new Exception("그룹 개수 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
			}
		}
		
		// 데이터 확인
//		System.out.println(totalRow);
		
		return totalRow;
	}

	// 그룹 게시글보기
	// DB 쿼리 : SELECT no, title, content, writer, writeDate, hit FROM board WHERE no = 2;
	// 글번호는 받아서 처리한다. - long no
	@SuppressWarnings("null")
	public GroupListVO view(long no, String id) throws Exception{
		// 리턴 타입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		GroupListVO vo = null;
		
		System.out.println("GroupListDAO.view().no : " + no + " --------------------");
		
		try {
			// 1. 드라이버 확인
			// 2. 연결
			con = DB.getConnection();
			// 3. sql 작성 - 변경되는 데이터는 ? (대체문자)로 작성
			String sql = " SELECT g.no, g.title, g.content, g.id, "
					+ " to_char(g.writeDate, 'yyyy-mm-dd') writeDate, g.hashtag, g.fileName, "
					+ " m.photo, g.hit, "
					+ " (select 'LIKED' from groupList_Like where no = ? and id = ? ) myLiked, "
					+ " (select count(*) from groupList_Like where no = ? ) likeCnt "
					+ " from groupList g, member m "
					+ " WHERE (no = ?) and (g.id = m.id) ";
			System.out.println("groupList.view sql = " + sql);
			// 4. 실행 객체 & 데이터세팅 - no
			pstmt = con.prepareStatement(sql);
			//   데이터 타입에 따른 메서드를 선택해서 세팅해준다.
			pstmt.setLong(1, no);
			pstmt.setString(2, id);
			pstmt.setLong(3, no);
			pstmt.setLong(4, no);
			System.out.println("실행 객체 생성 완료");
			// 5. 실행 
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			rs = pstmt.executeQuery();
			System.out.println("DB 쿼리 실행 완료");
			// 6. 데이터 표시나 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터가 있는 경우(if) 데이터 가져온다.
			if(rs != null && rs.next()) {
				// 위에서 선언한 vo 객체를 생성하고 데이터를 담는다.
				vo = new GroupListVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("Id"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHashtag(rs.getString("hashtag"));
				vo.setFileName(rs.getString("fileName"));
				vo.setPhoto(rs.getString("photo"));
				vo.setHit(rs.getLong("hit"));
				vo.setMyLiked(rs.getString("myLiked"));
				vo.setLikeCnt(rs.getLong("likeCnt"));
//				
			}
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			// Controller에서 예외처리를 시키기 위해서 예외를 생성하고 던진다.
			throw new Exception("그룹 게시글보기 DB 처리 중 오류 발생 - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				// Controller에서 예외처리를 시키기 위해서 예외를 생성하고 던진다.
				throw new Exception("그룹 게시글보기 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
			}
		}
		
		// 데이터 확인
		System.out.println(vo);
		
		return vo;
	}

	// 그룹 게시글보기 조회수 1증가
	public Integer increase(long no) throws Exception{
		// return 타입과 동일한변수 선언
		Integer result = 0;
		
		// 데이터처리
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = "UPDATE groupList SET hit = hit + 1 WHERE no = ?";
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5. 실행
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result >= 1)
				System.out.println("그룹게시글 조회수 1증가 DB 처리 완료");
			else
				System.out.println("그룹게시글 조회수 1증가 DB 처리가 되지 않았습니다. 글번호를 확인해 주세요.");
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("그룹게시글 조회수 1증가 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				//  - commit 까지 완료하고 나온다. - auto commit
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("그룹게시글 조회수 1증가 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}
	
	// 그룹 게시글수정
	public Integer update(GroupListVO vo) throws Exception{
		// return 타입과 동일한변수 선언
		Integer result = 0;
		
		// 데이터처리
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = "UPDATE groupList SET title = ?, content = ?, hashtag = ?, fileName = ? WHERE no = ?";
				
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getHashtag());
			pstmt.setString(4, vo.getFileName());
			pstmt.setLong(5, vo.getNo());
			// 5. 실행
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result >= 1)
				System.out.println("그룹게시글 수정 DB 처리 완료");
			else
				System.out.println("그룹게시긓 수정 DB 처리가 되지 않았습니다. 글번호를 확인해 주세요.");
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("그룹 게시글수정 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				//  - commit 까지 완료하고 나온다. - auto commit
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("그룹  게시글수정 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}


	// 그룹 등록
	// DB 쿼리 : INSERT INTO board(no, title, content, writer) VALUES (board_seq.NEXTVAL, ?, ?, ?);
	// 제목, 내용, 작성자 -> BoardVO 받아서 처리한다. - BoardVO vo
	public Integer write(GroupListVO vo) throws Exception{
		// return 타입과 동일한변수 선언
		Integer result = 0;
		
		// 데이터처리
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = " INSERT INTO groupList(no, title, content, id, hashtag, fileName) VALUES (board_seq.NEXTVAL, ?, ?, ?, ?, ?) ";
			System.out.println("GroupListDAO.write().sql : " + sql);
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent()); 
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getHashtag());
			pstmt.setString(5, vo.getFileName());
			// 5. 실행
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			System.out.println("그룹 등록 DB 처리 완료");
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("그룹 글등록 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				//  - commit 까지 완료하고 나온다. - auto commit
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("그룹 글등록 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}

	// 그룹 글삭제
	// DB 쿼리 : DELETE FROM board WHERE no = ?
	// 번호 -> no 받아서 처리한다. 
	// 데이터가 삭제가 됐으면 1이 안됐으면 0이 리턴된다.
	public Integer delete(long no) throws Exception{
		// return 타입과 동일한변수 선언
		Integer result = 0;
		
		
		// 데이터처리
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = "DELETE FROM groupList WHERE no = ?";
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5. 실행
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result >= 1)
				System.out.println("그룹 삭제 DB 처리 완료");
			else
				System.out.println("그룹 삭제 DB 처리가 되지 않았습니다. 글번호를 확인해 주세요.");
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("그룹 게시글삭제 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				//  - commit 까지 완료하고 나온다. - auto commit
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("그룹 게시글삭제 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}


	// 게시판 좋아요 처리 - insert
	public Integer like(GroupListVO vo) throws Exception{
		// return 타입과 동일한변수 선언
		System.out.println("GroupListDAO.like().vo - " + vo);
		Integer result = 0;
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = "insert into groupList_like(no, id) values(?, ?)";
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			// 5. 실행
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) throw new Exception("좋아요 처리가 된 데이터입니다.");
			else
				System.out.println("좋아요 처리가 되었습니다.");
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("그룹 좋아요 - 좋아요 처리 중 DB 오류 또는 좋아요 처리 데이터입니다.");
		} finally {
				// 7. 닫기
				DB.close(con, pstmt);
		}
		
		return result;
	}


	// 게시판 좋아요 취소 처리 - delete
	public Integer likeCancel(GroupListVO vo) throws Exception{
		// return 타입과 동일한변수 선언
		Integer result = 0;
		
		
		// JDBC 프로그램
		try {
			// 1. 드라이버 확인 + 2. 연결
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = "delete from groupList_like where no = ? and id = ?";
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			// 5. 실행
			// - select처리 : executeQuery() - rs가 나온다. insert,update,delete 처리 : executeUpdate() - int가 나온다.
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
			if(result == 0) throw new Exception("정보를 확인해주세요.");
			else
				System.out.println("좋아요 취소가 되었습니다.");
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("그룹 좋아요 취소 - 좋아요 취소 중 DB 오류 또는 정보 확인하셔야 합니다.");
		} finally {
				// 7. 닫기
				DB.close(con, pstmt);
			
		}
		
		return result;
	}


}

	
	