package com.inbook.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.inbook.comment.vo.CommentVO;
import com.inbook.util.ReplyPageObject;
import com.inbook.util.db.DB;

public class CommentDAO {
	
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
			String sql = "select count(*) from board_reply where no = ?";
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
	public List<CommentVO> list(ReplyPageObject replyPageObject) throws Exception {
		List<CommentVO> list = null;
		
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "select br.rno, br.no, br.content, m.photo, br.id, "
					+ " to_char(br.writeDate, 'yyyy-mm-dd') writeDate "
					+ " from board_reply br, member m "
					+ " where (br.no = ?) and (br.id = m.id) ORDER BY rno DESC ";
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
					if(list == null) list = new ArrayList<CommentVO>();
					CommentVO vo = new CommentVO();
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
	public Integer write(CommentVO vo) throws Exception {
		Integer result = 0;
		
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "insert into board_reply(rno, no, content, id) "
					+ " values(board_reply_seq.nextval, ?, ?, ?) ";
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
	public Integer update(CommentVO vo) throws Exception {
		Integer result = 0;
				
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "update board_reply set content = ? where rno = ? ";
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
	public Integer delete(CommentVO vo) throws Exception {
		Integer result = 0;
				
		try {
			
			con = DB.getConnection();
			// 3. sql 작성
			String sql = "delete from board_reply where rno = ? and id = ? ";
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
}
