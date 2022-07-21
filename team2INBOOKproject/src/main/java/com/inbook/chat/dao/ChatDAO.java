package com.inbook.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.inbook.chat.vo.ChatVO;

import com.inbook.util.db.DB;


public class ChatDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
		// 채팅 번호( 가장 큰거)
	public Long getMaxNo(long cno) throws Exception {
		Long MaxNo = 0L;

		try {
			// 1,2
			con = DB.getConnection();	
			// 3.
			// 3-1. 전체 데이터 가져오기
			String sql = " select max(no) from chat where (cno = ?)";
			// 4
			pstmt = con.prepareStatement(sql);	
			pstmt.setLong(1, cno);

				// 5. 
				rs = pstmt.executeQuery();
				// 6. 
				if(rs != null && rs.next())
					MaxNo = rs.getLong(1);	
			}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅 전체 데이터 가져오기 DB 처리중 오류");
		} finally {
			try {
				// 7.
				DB.close(con, pstmt, rs); 
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("채팅 전체 데이터 가져오기 DB 처리중 오류");

			}
		}
		
		return MaxNo;
	}


	public Integer chatSendData(ChatVO vo) throws Exception {
		Integer result = 0;
		
		try {
			//1. 드라이버 확인-한번만
	//		Class.forName(driver);
			//2
	//		con = DriverManager.getConnection(url, uid, upw);
			con = DB.getConnection();
			//3 sql
			String sql = " INSERT INTO chat(no, id, content, cno)VALUES(chat_seq.NEXTVAL, ?, ?, ?) ";
			//4
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getCno());
			
			//5
			result = pstmt.executeUpdate();
			//6
			System.out.println("ChatDAO.write() - 채팅 등록이 되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅 등록 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("채팅 등록 DB 객체 닫기 처리 중 오류");
			}
		}
		
		return result;
	}



	public ChatVO chatGetData(ChatVO vo) throws Exception {
		ChatVO dataVo = null;
		
		
		try {
			//1. 드라이버 확인-한번만
	//		Class.forName(driver);
			//2
	//		con = DriverManager.getConnection(url, uid, upw);
			con = DB.getConnection();
			//3 sql
			String sql = "select c.no, c.id, c.content, c.writeDate, cr.cno from chat c, chatRoom cr"
					+ " where ( c.no > ? and cr.cno = ?) and (cr.cno = c.cno)";
			//4
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(2, vo.getCno());
//			pstmt.setString(3, vo.getId());
			
			//5
//			result = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			// 6
			if(rs != null) {
				while(rs.next()) {
//					if(list == null) list = new ArrayList<ChatVO>();
					
					dataVo = new ChatVO();
					dataVo.setNo(rs.getLong("no"));
					dataVo.setId(rs.getString("id"));
					dataVo.setContent(rs.getString("content"));
					dataVo.setWriteDate(rs.getString("writeDate"));
					dataVo.setCno(rs.getLong("cno"));

					
				}
			}
			
			
			//6
			System.out.println("ChatDAO.write() - 채팅 maxNo 불러오기 처리가 되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("채팅 maxNo 불러오기 DB 처리 중 오류");
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("채팅 maxNo 불러오기 DB 객체 닫기 처리 중 오류");
			}
		}
		System.out.println("dataVo " + dataVo );
		return dataVo;
	}

	
	

	
}
