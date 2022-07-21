package com.inbook.chatRoom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.inbook.chatRoom.vo.ChatRoomVO;

import com.inbook.util.db.DB;
import com.webjjang.util.PageObject;

public class ChatRoomDAO {
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@DESKTOP-TEAM2:1521:xe";
//	String uid = "team02";
//	String upw = "team02";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public List<ChatRoomVO> list(PageObject pageObject) throws Exception{
		List<ChatRoomVO> list = null;
		

		try {
			//1,2
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, uid, upw);
			con = DB.getConnection();
			// 3.
			// 3-1. 전체 데이터 가져오기
			String sql = "select  cr.cno, m.id, cr.title from chatRoom cr, member m where ( m.id = cr.id) order by cno desc";
			// 3-2. 순서번호 붙이기
			sql = "select rownum rnum, cno, id, title"
					+ " from (" + sql + ")";
			// 3-3. 페이지에 맞는 데이터
			sql = "select rnum, cno, id, title from (" + sql + ") where rnum between ? and ?";
			// 4.		
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			// 5.
			rs = pstmt.executeQuery();
			// 6
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<ChatRoomVO>();
					
					ChatRoomVO vo = new ChatRoomVO();
					vo.setCno(rs.getLong("cno"));
					vo.setId(rs.getString("id"));
					vo.setTitle(rs.getString("title"));
					
	
		
					
					list.add(vo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅방 리스트 데이터 가져오는 DB 오류");
		}finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("채팅방 리스트 데이터 가져오는 DB 닫기 오류");
			}
		}
		
		return list;
	}
	
		
	public Long getTotalRow(PageObject pageObject) throws Exception {
		Long totalRow = 0L;
	
		
		try {
			// 1,2
		//	Class.forName(driver);	
			// 3.
			// con = DriverManager.getConnection(url, uid, upw);
			con = DB.getConnection();
			// 3-1. 전체 데이터 가져오기
			String sql = " select count(*) from chatRoom ";
			// 4
			pstmt = con.prepareStatement(sql);	
		//	pstmt.setLong(1, pageObject.getNo());
				// 5. 
				rs = pstmt.executeQuery();
				// 6. 
				if(rs != null && rs.next())
					totalRow = rs.getLong(1);	
			}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅방 전체 데이터 가져오기 DB 처리중 오류");
		} finally {
			try {
				// 7.
				DB.close(con, pstmt, rs); 
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("채팅방 전체 데이터 가져오기 DB 처리중 오류");

			}
		}
		System.out.println(totalRow);
		return totalRow;
	}

	public Integer write(ChatRoomVO vo) throws Exception {
		Integer result = 0;
		
		try {
			//1. 드라이버 확인-한번만
	//		Class.forName(driver);
			//2
	//		con = DriverManager.getConnection(url, uid, upw);
			con = DB.getConnection();
			//3 sql
			String sql = " INSERT INTO chatRoom(cno, id, title)VALUES(chatRoom_seq.NEXTVAL, ?, ?) ";
			//4
			pstmt = con.prepareStatement(sql);
	//		pstmt.setLong(1, vo.getCno());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getTitle());
			
			//5
			result = pstmt.executeUpdate();
			//6
			System.out.println("ChatRoomDAO.write() - 채팅방 등록이 되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅방 등록 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("채팅방 등록 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return result;
	}
	
	
	public Integer update(ChatRoomVO vo) throws Exception {
	Integer result = 0;
	
	try {
		//1. 드라이버 확인-한번만
	//	Class.forName(driver);
		//2
	//	con = DriverManager.getConnection(url, uid, upw);
		con = DB.getConnection();
		//3 sql
		String sql = "update chatRoom set title = ? where cno = ?";
		//4
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setLong(2, vo.getCno());
		//5
		result = pstmt.executeUpdate();
		//6
		System.out.println("ChatRoomDAO.update() - 채팅방 제목 수정이 되었습니다.");
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("채팅방 제목 수정 DB 처리 중 오류");
	} finally {
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅방 제목 수정 DB 객체 닫기 처리 중 오류");
		}
	}
	
	return result;
}

	public Integer delete(ChatRoomVO vo) throws Exception {
	Integer result = 0;
	
	try {
		//1. 드라이버 확인-한번만
	//	Class.forName(driver);
		//2
	//	con = DriverManager.getConnection(url, uid, upw);
		con = DB.getConnection();
		//3 sql
		String sql = "delete from chatRoom where cno =?";
		//4
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, vo.getCno());
//		pstmt.setString(2, vo.getId());
		//5
		result = pstmt.executeUpdate();
		//6
		System.out.println("ChatRoomDAO.delete() - 채팅방 삭제 되었습니다.");
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("채팅방 삭제 DB 처리 중 오류");
	} finally {
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅방 삭제 DB 객체 닫기 처리 중 오류");
		}
	}
	
	return result;
}

}
