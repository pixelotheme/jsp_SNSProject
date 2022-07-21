package com.inbook.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.inbook.friend.vo.FriendVO;
import com.inbook.util.db.DB;
import com.webjjang.util.PageObject;

// 게시판 데이터베이스 처리를 하는 객체 - Controller - Service - [DAO]
public class FriendDAO {


	
	// DB 처리를 위해 필요한 객체
	// 연결 객체
	Connection con = null;
	// 실행 객체
	PreparedStatement pstmt = null;
	// DB에서 가져온 데이터를 저장하는 객체
	ResultSet rs = null;
	
	// 친구 리스트
	public List<FriendVO> list(PageObject pageObject, String id) throws Exception {
		
		// 리턴 파입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		List<FriendVO> list = null;
		
		
		try {
			
			//1
			con = DB.getConnection();
					
			
			//1) 원본 데이터 가져오기
			String sql = " select f.no, f.friendId, m.name, m.email, m.tel, m.photo, f.relationship "
					+ " from friend f, member m "
					+ " where(f.hostId = ? and f.friendId = m.id and f.relationship = 1) order by no desc";
			
			sql = " select rownum rnum, no, friendId, name, email, tel, photo, relationship from( " + sql +" ) ";
			sql = " select rnum, no, friendId, name, email, tel, photo, relationship from (" + sql + " ) "
					+ " where rnum between ? and ? ";
			
			System.out.println("sql 문 : "+sql);
			// 4. 실행객체 & 데이터 세팅
			int idx = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setLong(2, pageObject.getStartRow());
			pstmt.setLong(3, pageObject.getEndRow());
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<FriendVO>();
					FriendVO vo = new FriendVO();
					vo.setNo(rs.getLong("no"));
					vo.setFriendId(rs.getString("friendId"));
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					vo.setTel(rs.getString("tel"));
					vo.setPhoto(rs.getString("photo"));
					vo.setRelationship(rs.getInt("relationship"));
					
					// vo -> list에 저장
					list.add(vo);
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("친구 리스트 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			// 예외처리를 한다.
			try {
				// 7.사용한 객체를 닫는다.
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("친구 리스트 데이터를 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		System.out.println(list);
		
		return list;
		
	}
	
	
	
	public Long getTotalRow(PageObject pageObject, String id) throws Exception{
		// 리턴 타입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		Long totalRow = 0L;
		
		
		try {
			// 1. 드라이버 확인
			con = DB.getConnection();
//			System.out.println("DB 연결 완료");
			String sql = " SELECT nvl(count(*),0) FROM friend where( " ;
			
			sql += " hostId = ? and relationship = 1 )";
			// 4. 실행 객체 & 데이터세팅 - no
			System.out.println("totalRow sql :" + sql);
			
			System.out.println("gettotalRow con : "+ con);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			// 6. 데이터 표시나 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터가 있는 경우(if) 데이터 가져온다.
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구 전체 개수 DB 처리 중 오류 발생 - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("친구 전체 개수 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
			}
		}
		
		// 데이터 확인
		System.out.println("totalRow"+totalRow);
		
		return totalRow;
	}
	
	//신청 받은 개수 구하기
	public Long getRequestTotalRow(PageObject pageObject, String id) throws Exception{
		// 리턴 타입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		Long totalRow = 0L;
		
		
		try {
			// 1. 드라이버 확인
			con = DB.getConnection();
			String sql = " SELECT nvl(count(*),0) FROM friend where( " ;
			
			sql += " friendId = ? and relationship = 0 )";
			// 4. 실행 객체 & 데이터세팅 - no
			System.out.println("totalRow sql :" + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			// 6. 데이터 표시나 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터가 있는 경우(if) 데이터 가져온다.
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구 전체 글 개수 DB 처리 중 오류 발생 - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("친구 전체 글 개수 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
			}
		}
		
		// 데이터 확인
		System.out.println("totalRow"+totalRow);
		
		return totalRow;
	}

	//검색 페이징 처리
	public Long getSearchTotalRow(PageObject pageObject) throws Exception{
		Long totalRow = 0L;
		
		
		try {
			// 1. 드라이버 확인   
			con = DB.getConnection();
			String sql = " SELECT nvl(count(*),0) FROM member where (1=0 " +getcondition(pageObject)+" )" ;
			   
			// 4. 실행 객체 & 데이터세팅 - no
			System.out.println("totalRow sql :" + sql);
			
			int idx = 0;
			pstmt = con.prepareStatement(sql);
			idx = setData(pageObject, pstmt, idx);
			
			
			rs = pstmt.executeQuery();
//			rs.next();
//			rs.next();
//			int current_row_number = rs.getRow();
//			System.out.println("FriendDAO.getTotalSearchRow().rs.getRow() : "+current_row_number);
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			// Controller에서 예외처리를 시키기 위해서 예외를 생성하고 던진다.
			throw new Exception("친구 전체 글 개수 DB 처리 중 오류 발생 - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				// Controller에서 예외처리를 시키기 위해서 예외를 생성하고 던진다.
				throw new Exception("친구 전체 글 개수 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
			}
		}
		
		// 데이터 확인
		System.out.println("totalRow"+totalRow);
		
		return totalRow;
	}
	
		
// 친구신청
	public Integer friendRequestSend(FriendVO vo) throws Exception{
		Integer result = 0;
		// 데이터처리
		try {
			// 1. 드라이버 확인
			con = DB.getConnection();
			// 3. SQL 작성
			String sql = "INSERT INTO friend(no, hostId, friendId) VALUES (FRIEND_SEQ.nextval, ?, ?)";
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getHostId());
			pstmt.setString(2, vo.getFriendId());

			
			System.out.println("FriendDAO.write().service" +sql);
			
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}
		catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구등록 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("친구 등록 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}


	//search와 비교하기위한 친구인사람 id 만 가져온다
	public List<String> friendIdlist(String id) throws Exception {
		
		List<String> friendIdList = null;
		
		try {
			
			//1
			con = DB.getConnection();
					
			String sql = " select f.no, f.friendId, m.name, m.email, m.tel, m.photo, f.relationship "
					+ " from friend f, member m "
					+ " where(f.hostId = ? and f.friendId = m.id and f.relationship = 1) order by no desc";
			
			
			System.out.println("sql 문 : "+sql);
			// 4. 실행객체 & 데이터 세팅
			int idx = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터를 있는(rs.next()) 만큼 반복처리(while()) 해서 list에 담는다.
			if(rs != null) {
				while(rs.next()) {
					if(friendIdList == null) friendIdList = new ArrayList<String>();
					FriendVO vo = new FriendVO();
					vo.setFriendId(rs.getString("friendId"));
					
					friendIdList.add(vo.getFriendId());
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			// TODO: handle exception
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구 아이디 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			// 예외처리를 한다.
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("친구 아이디 데이터를 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		
		return friendIdList;
		
	}
	
	//로그인한 아이디가 친구요청받은 상대방아이디 정보 리스트(다른 정보도 가져온다)
	//친구 아이디에 로그인 아이디가 있고 rel가 0인 사람
	public List<FriendVO> friendRequestIdList(PageObject pageObject,String id) throws Exception {
		
		List<FriendVO> friendRequestList = null;
		
		
		try {
			
			//1
			con = DB.getConnection();
					
			
			String sql = " select f.hostId, m.name, m.email, m.photo, f.relationship "
					+ " from friend f, member m "
					+ " where(f.hostId = m.id and f.friendId = ? and f.relationship = 0) order by no desc";
			
			sql = " select rownum rnum, hostId, name, email, photo, relationship from( " + sql +" ) ";
			//3) 1페이지에 해당되는 데이터 가져오기
			sql = " select rnum, hostId, name, email, photo, relationship from (" + sql + " ) "
					+ " where rnum between ? and ? ";
			System.out.println("sql 문 : "+sql);
			// 4. 실행객체 & 데이터 세팅
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setLong(2, pageObject.getStartRow());
			pstmt.setLong(3, pageObject.getEndRow());
			
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터를 있는(rs.next()) 만큼 반복처리(while()) 해서 list에 담는다.
			if(rs != null) {
				// 데이터가 있는 만큼 반복처리
				while(rs.next()) {
					if(friendRequestList == null) friendRequestList = new ArrayList<FriendVO>();
					FriendVO vo = new FriendVO();
					vo.setHostId(rs.getString("hostId"));
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					vo.setPhoto(rs.getString("photo"));
					vo.setRelationship(rs.getInt("relationship"));
					
					friendRequestList.add(vo);
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("친구요청 받은 리스트 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("친구요청 받은 리스트 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		// list에 담겨 있는 데이터 확인
		System.out.println(friendRequestList);
		
		return friendRequestList;
		
	}
	
	public List<FriendVO> search(PageObject pageObject) throws Exception {

		List<FriendVO> list = null; 
		
		try {
			
			//1
			con = DB.getConnection();
					
			
			String sql = "  select id, name, email, photo from member "
					+ " where (1=0 ";
			sql += getcondition(pageObject); 


			sql += " ) and (gradeno = 1) ";
			
			sql = " select rownum rnum, id, name, email, photo from( " + sql +" ) ";
			sql = " select rnum, id, name, email, photo from (" + sql + " ) "
					+ " where rnum between ? and ? ";
			
			System.out.println("sql 문 : "+sql);
			
			// 4. 실행객체 & 데이터 세팅
			int idx = 0;
			pstmt = con.prepareStatement(sql);
			idx = setData(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<FriendVO>();
					FriendVO vo = new FriendVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					vo.setPhoto(rs.getString("photo"));
					
					list.add(vo);
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("친구 검색 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("친구 검색 데이터를 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		return list;
	}


	public String getcondition(PageObject pageObject) {
		String str = "";
		
		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			if(pageObject.getKey().indexOf("i") >= 0)
				str += " or id like ? ";
			if(pageObject.getKey().indexOf("n") >= 0)
				str += " or name like ? ";
		}
		
		return str;
		
	}

	public int setData(PageObject pageObject, PreparedStatement pstmt, int idx) throws Exception {

		//검색어가 있으면 데이터 세팅
		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			if(pageObject.getKey().indexOf("i") >= 0)
				pstmt.setString(++idx, "%" + pageObject.getWord() + "%");
			if(pageObject.getKey().indexOf("n") >= 0)
				pstmt.setString(++idx, "%" + pageObject.getWord() + "%");
		}
		
		return idx;
	}


	//친구요청받은 개수
	public long requestCount(String id) throws Exception {
		
		Long cnt = 0L;
		
		try {
			
			//1
			con = DB.getConnection();
					
			String sql = "  select nvl(count(*),0) from friend "
					+ " where (friendId = ? and acceptDate is null and relationship = 0) ";

			
//			System.out.println("sql 문 : "+sql);
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 5. 실행
			rs = pstmt.executeQuery();
//			System.out.println(" 쿼리실행");
			if(rs != null && rs.next()) {
				// 데이터가 있는 만큼 반복처리
				cnt = rs.getLong(1);
//				System.out.println(" cnt 주입");
			} // end of if
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("친구요청 개수 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			// 예외처리를 한다.
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("친구요청 개수 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
//		System.out.println("신청받은 개수"+cnt);
		return cnt;
	}


	public Integer friendRequestAccept(FriendVO vo) throws Exception {
		Integer result = 0;
		
		try {
			con = DB.getConnection();
			String sql = " insert into friend (no, hostId, friendId, relationship, acceptdate)"
					+ " values(FRIEND_SEQ.nextval, ?, ?, 1, sysdate) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getHostId());
			pstmt.setString(2, vo.getFriendId());

			
			System.out.println("FriendDAO.write().service" +sql);
			
			result = pstmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}
		catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구 수락 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("친구 수락 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
		
	}


	//friendRequestAccept 와 같이 처리된다 
	public Integer friendRequestSenderUpdate(FriendVO vo) throws Exception {
		Integer result = 0;
		
		// 데이터처리
		try {
			con = DB.getConnection();
			String sql = " UPDATE friend set relationship = 1, acceptdate = sysdate where (hostId = ? and friendId = ?)"; 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getFriendId());
			pstmt.setString(2, vo.getHostId());

			
			System.out.println("FriendDAO.write().service" +sql);
			
			result = pstmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}
		catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구 요청한 사람 업데이트DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("친구 요청한 사람 업데이트 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
		
	}

	public Integer friendRequestRefuse(FriendVO vo) throws Exception{
		int result = 0;
		try {
			// 1. 드라이버 확인
			con = DB.getConnection();
			String sql = " DELETE from friend where (hostId = ? and friendId= ? and relationship = 0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getHostId());
			pstmt.setString(2, vo.getFriendId());

			
			System.out.println("FriendDAO.write().service" +sql);
			
			result = pstmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}
		catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("친구 수락 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				//  - commit 까지 완료하고 나온다. - auto commit
				DB.close(con, pstmt);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("친구 수락 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		return result;
		
	}


	//친구요청 보낸사람 id
	public List<String> friendRequestSendId(String id) throws Exception {
		// TODO Auto-generated method stub
		List<String> sendList = null;
		
		try {
			
			//1
			con = DB.getConnection();
					
			
			String sql = " SELECT friendId from friend where(hostId= ? and relationship = 0) ";
			System.out.println("sql 문 : "+sql);
			// 4. 실행객체 & 데이터 세팅
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터를 있는(rs.next()) 만큼 반복처리(while()) 해서 list에 담는다.
			if(rs != null) {
				while(rs.next()) {
					if(sendList == null) sendList = new ArrayList<String>();
					
					sendList.add(rs.getString("friendId"));
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("친구요청 한 아이디 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			// 예외처리를 한다.
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("친구요청 한 아이디 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		
		return sendList;
	}
	
	//친구 요청 받은 아이디
	public List<String> friendRequestId(String id) throws Exception {
		// TODO Auto-generated method stub
		List<String> requestList = null;
		
		try {
			
			//1
			con = DB.getConnection();
			
			
			String sql = " SELECT hostId from friend where(friendId= ? and relationship = 0) ";
			System.out.println("sql 문 : "+sql);
			// 4. 실행객체 & 데이터 세팅
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터를 있는(rs.next()) 만큼 반복처리(while()) 해서 list에 담는다.
			if(rs != null) {
				while(rs.next()) {
					if(requestList == null) requestList = new ArrayList<String>();
					
					requestList.add(rs.getString("hostId"));
				} // end of while
			} // end of if
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("친구요청 받은 리스트 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			try {
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("친구요청 받은 리스트 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		
		return requestList;
	}



	public Integer friendRequestCancel(FriendVO vo) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			con = DB.getConnection();
			String sql = " DELETE from friend where (hostId = ? and friendId= ? and relationship = 0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getHostId());
			pstmt.setString(2, vo.getFriendId());

			
			System.out.println("FriendDAO.write().service" +sql);
			
			// 5. 실행
			result = pstmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("친구요청 취소 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("친구 요청 취소 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		return result;
		
	}

	public Integer delete(FriendVO vo) throws Exception{
		Integer result = 0;
		
		try {
			con = DB.getConnection();
			String sql = " DELETE from friend where (hostId = ? and friendId = ? and relationship = 1) ";
			System.out.println("쿼리: " +sql);

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getHostId());
			pstmt.setString(2, vo.getFriendId());

			result = pstmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("친구 삭제 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("친구 삭제 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}

	//로그인한 아이디가 2번 물음표에 들어간다 상대방 삭제중
	public Integer deleteOtherPart(FriendVO vo) throws Exception{
		Integer result = 0;
		
		try {
			// 1. 드라이버 확인
			con = DB.getConnection();

			String sql = " DELETE from friend where (hostId = ? and friendId = ? and relationship = 1) ";

			System.out.println("쿼리: " +sql);
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFriendId());
			pstmt.setString(2, vo.getHostId());

			result = pstmt.executeUpdate();
			// 6. 표시 또는 담기
		}
		catch (SQLIntegrityConstraintViolationException e) {
			result = 3;
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("친구 삭제 DB 처리 중 오류. - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("친구 삭제 객체를 닫는 중 오류. - " + e.getMessage());
			}
		}
		
		return result;
	}
	

	// 친구 리스트
	public List<FriendVO> suggestions(PageObject pageObject, String id, String[] friendIdList) throws Exception {
		
		// 리턴 파입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		List<FriendVO> suggestionsList = null;
		List<FriendVO> resultList = null;
		
		
		try {
			
			//1
			con = DB.getConnection();
			
			
//			int friendIdx = 0;
			
//			if(friendIdList != null) {
				//내 친구 아이디 꺼내오기 
				for(String hostId : friendIdList) {
					
					String sql = " select f.no, f.friendId, m.name, m.photo, f.relationship "
							+ " from friend f, member m "
							+ " where(f.hostId = ? and f.friendId = m.id and f.relationship = 1) "
							+ " and(f.friendId != ? "+getSuggestionsCondition(friendIdList)+" ) ";
					
					//1. 친구 아이디 2.내아이디 3,4 친구 아이디
					System.out.println("sql : " + sql);
					int idx = 0;
					pstmt = con.prepareStatement(sql);
					//번호상승 따로 지정 크게 한바퀴 돌려야함
					pstmt.setString(++idx, hostId);
					pstmt.setString(++idx, id);
					idx = setSuggestionData(friendIdList, pstmt, idx);
					
					rs = pstmt.executeQuery();
					
					
					if(rs != null) {
						while(rs.next()) {
							if(suggestionsList == null) suggestionsList = new ArrayList<FriendVO>();
							FriendVO vo = new FriendVO();
							vo.setFriendId(rs.getString("friendId"));
							vo.setName(rs.getString("name"));
							vo.setPhoto(rs.getString("photo"));
							vo.setRelationship(rs.getInt("relationship"));
							
							// vo -> list에 저장
							suggestionsList.add(vo);
							
						} // end of while
					} // end of if
				}
//			}
			if(suggestionsList == null || suggestionsList.equals("")) {
				return resultList;
			}
			//중복아이디 제거
			resultList = new ArrayList<FriendVO>(new HashSet<FriendVO>(suggestionsList));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("친구 추천 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
		} finally {
			// 예외처리를 한다.
			try {
				// 7.사용한 객체를 닫는다.
			
					
					DB.close(con, pstmt, rs);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("친구 추천 데이터를 처리객체 닫는 중 오류 발생. - " + e.getMessage());
			}
		}
		
		System.out.println("resultList : "+resultList);
		
		return resultList;
		
	}
	
	public String getSuggestionsCondition (String[] friendIdList ) {
		String str = "";
		long friendIdCount = friendIdList.length;
		//3명 아이디 -> ㅇ
		int i;
		
		if(friendIdCount != 0) {
			str += " and ";
			for(i = 0; i < friendIdCount; i++) {
				str += " f.friendId != ? and";
			}
			str += " 1=1 ";
		}
		
		
		return str;
		
	}
	
	public int setSuggestionData(String[] friendIdList, PreparedStatement pstmt, int idx) throws Exception {
		int i;
		long friendIdCount = friendIdList.length;
		
		for(i =0; i < friendIdCount; i++) {
			pstmt.setString(++idx, friendIdList[i]);
		}
		
		return idx;
	}
	

	public List<FriendVO> allUser(String id, PageObject pageObject) throws Exception{
		List<FriendVO> allUser = null;
		try {
		con = DB.getConnection();
		
		String sql = "select rnum, id, name, photo "
				+ " from "
				+ "    (  select rownum rnum, id, name, photo "
				+ "    from"
				+ "        ( select id, name, photo "
				+ "        from member where gradeNo = 1 and id != ? order by id "
				+ "        ) "
				+ "    ) "
				+ " where rnum between ? and ? ";
		
		System.out.println("FriendDAO.allUser().sql : "+sql);
		System.out.println("FriendDAO.allUser().row : "+pageObject.getStartRow() +" + " + pageObject.getEndRow());
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setLong(2, pageObject.getStartRow());
		pstmt.setLong(3, pageObject.getEndRow());
		
		rs = pstmt.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				if(allUser == null) allUser = new ArrayList<FriendVO>();
				FriendVO vo = new FriendVO();
				vo.setFriendId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPhoto(rs.getString("photo"));
				
				// vo -> list에 저장
				allUser.add(vo);
			} // end of while
		
		
		} // end of if
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new Exception("모든 유저 데이터를 가져오는 중 오류 발생. - " + e.getMessage());
	} finally {
		// 예외처리를 한다.
		try {
			// 7.사용한 객체를 닫는다.
			DB.close(con, pstmt, rs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("모든 유저 데이터를 처리객체 닫는 중 오류 발생. - " + e.getMessage());
		}
	}
		
		
		return allUser;
	}
	
	
	public Long getAllUserTotalRow( String id) throws Exception{
		// 리턴 타입과 동일한 변수 - 데이터가 있다면 데이터를 채워서 리턴시킨다.
		Long totalRow = 0L;
		
		
		try {
			// 1. 드라이버 확인
			con = DB.getConnection();
//			System.out.println("DB 연결 완료");
			String sql = " SELECT nvl(count(*),0) FROM member where( " ;
			
			sql += " id != ? and gradeNo = 1 )";
			// 4. 실행 객체 & 데이터세팅 - no
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			// 6. 데이터 표시나 데이터 담기 - 만약(if)에 rs가 null이 아니고 데이터가 있는 경우(if) 데이터 가져온다.
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
		} catch (Exception e) {
			// 개발자를 위한 코드
			e.printStackTrace();
			throw new Exception("유저 전체 개수 DB 처리 중 오류 발생 - " + e.getMessage());
		} finally {
			try {
				// 7. 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// 개발자를 위한 코드
				e.printStackTrace();
				throw new Exception("유저 전체 개수 DB 자원 닫기 중 오류 발생 - " + e.getMessage());
			}
		}
		
		// 데이터 확인
		System.out.println("totalRow"+totalRow);
		
		return totalRow;
	}
	
	
}
