package com.inbook.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
	
	// DB 연결 정보
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@192.168.0.111:1521:xe";
	private static String uid = "team02";
	private static String upw = "team02";

	private static boolean checkDriver = false;
	
	static {
		
		// Oracle Driver 클래스 확인
		try {
			Class.forName(driver);
			checkDriver = true;
			System.out.println("DB.static 블록 - 드라이버 확인 성공 완료 ------");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}
	
	public static Connection getConnection() throws Exception {
		if(checkDriver) return DriverManager.getConnection(url, uid, upw);
		throw new Exception("DB를 처리할 프로그램(드라이버)이 존재하지 않습니다.");
		
	}
	
	public static void close(Connection con, PreparedStatement pstmt) throws Exception {
		if(con != null) con.close();
		if(pstmt != null) pstmt.close();
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws Exception {
		close(con, pstmt);
		if(rs != null) rs.close();
	}

}
