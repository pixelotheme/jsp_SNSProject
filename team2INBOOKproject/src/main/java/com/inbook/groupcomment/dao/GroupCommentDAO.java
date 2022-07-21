package com.inbook.groupcomment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inbook.board.vo.BoardVO;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.groupcomment.vo.GroupCommentVO;
import com.inbook.util.ReplyPageObject;
import com.inbook.util.db.DB;

public class GroupCommentDAO {
	
	// DB 처리를 위해 필요한 객체
	// 연결 객체
	Connection con = null;
	// 실행 객체
	PreparedStatement pstmt = null;
	// DB에서 가져온 데이터를 저장하는 객체
	ResultSet rs = null;
	
	
	// 댓글 목록
	public Long getTotalRow(ReplyPageObject replyPageObject) throws Exception {
		Long result = 0L;
		
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "select count(*) from groupList_reply where no = ?";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, replyPageObject.getNo());
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null && rs.next()) {
				result = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("댓글 개수 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("댓글 개수 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return result;
	}
	


	// 댓글 리스트
	public List<GroupCommentVO> list(ReplyPageObject replyPageObject) throws Exception {
		List<GroupCommentVO> list = null;
		
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "select gr.rno, gr.no, gr.content, m.photo, gr.id, "
					+ " to_char(gr.writeDate, 'yyyy-mm-dd') writeDate "
					+ " from groupList_reply gr, member m "
					+ " where (gr.no = ?) and (gr.id = m.id) ORDER BY rno DESC ";
			sql = " select rownum rnum, rno, no, content, photo, id, writeDate from (" + sql + ")";
			sql = " select rnum, rno, no, content, photo, id, writeDate from (" + sql + ") where rnum between ? and ? ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, replyPageObject.getNo());
			pstmt.setLong(2, replyPageObject.getStartRow());
			pstmt.setLong(3, replyPageObject.getEndRow());
			// 5.
			rs = pstmt.executeQuery();
			// 6.
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<GroupCommentVO>();
					GroupCommentVO vo = new GroupCommentVO();
					vo.setRno(rs.getLong("rno"));
					vo.setNo(rs.getLong("no"));
					vo.setContent(rs.getString("content"));
					vo.setPhoto(rs.getString("photo"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("댓글 리스트 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("댓글 리스트 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return list;
	}
	
	
	
	// 댓글 작성
	public Integer write(GroupCommentVO vo) throws Exception {
		Integer result = 0;
		
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "insert into groupList_reply(rno, no, content, id) "
					+ " values(groupList_reply_seq.nextval, ?, ?, ?) ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("ReplyDAO.write() - 댓글이 등록 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("댓글 등록 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("댓글 등록 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return result;
	}
	
	
	
	// 댓글 수정
	public Integer update(GroupCommentVO vo) throws Exception {
		Integer result = 0;
				
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "update groupList_reply set content = ? where rno = ? ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getRno());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("ReplyDAO.update() - 댓글이 수정 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("댓글 수정 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("댓글 수정 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return result;
	}
	
	
	
	// 댓글 삭제
	public Integer delete(GroupCommentVO vo) throws Exception {
		Integer result = 0;
				
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "delete from groupList_reply where rno = ? and id = ? ";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getRno());
			pstmt.setString(2, vo.getId());
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			System.out.println("ReplyDAO.delete() - 댓글이 삭제 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("댓글 삭제 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("댓글 삭제 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return result;
	}

//그룹 좋아요 처리 - insert
	// 메소드 이름은 like
	public Integer like(GroupListVO vo) throws Exception{
		// return 타입과 동일한변수 선언
		System.out.println("BoardDAO.like().vo - " + vo);
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


	// 그룹 좋아요 취소 처리 - delete
	public Integer likeCancel(BoardVO vo) throws Exception{
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
