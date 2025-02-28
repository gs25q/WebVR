package model.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GuestBook;


public class GuestBookDAO {
	private JDBCUtil jdbcUtil = null;
	
	public GuestBookDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public int create(GuestBook gtb) throws SQLException {//새로운 방명록 작성
		String sql = "INSERT INTO guest_book VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {gtb.getGbID(), gtb.getContent(), 
						gtb.getDate(), gtb.getUserID(), gtb.getExhbId()
						//(user.getCommId()!=0) ? user.getCommId() : null 
						};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;				
	}
	
	
	public int update(GuestBook gtb) throws SQLException {//방명록 수정
		String sql = "UPDATE guest_book "
					+ "SET content=? "
					+ "WHERE user_id=?, id=? ";
		Object[] param = new Object[] {gtb.getContent(), gtb.getUserID(), 
					gtb.getGbID()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	public int remove(String gbID) throws SQLException {//방명록 삭제
		String sql = "DELETE FROM guest_book WHERE id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {gbID});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	
	public List<GuestBook> GBList() throws SQLException {//전시 별 방명록 리스트 반환
        String sql = "SELECT id, user_id, exhibition_id, content, date " 
        		   + "FROM guest_book"
        		   + "WHERE exhibition_id=?"
        		   + "ORDER BY date";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<GuestBook> GBList = new ArrayList<GuestBook>();	// User들의 리스트 생성
			while (rs.next()) {
				GuestBook GB = new GuestBook(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getInt("id"),
					rs.getInt("user_id"),
					rs.getInt("exhibition_id"),
					rs.getString("content"),
					rs.getString("date"));
				GBList.add(GB);				// List에 User 객체 저장
			}		
			return GBList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<GuestBook> myGBList() throws SQLException {//특정 사용자 방명록 리스트 반환
        String sql = "SELECT id, user_id, exhibition_id, content, date " 
        		   + "FROM guest_book"
        		   + "WHERE user_id=?"
        		   + "ORDER BY date";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<GuestBook> myGBList = new ArrayList<GuestBook>();	// User들의 리스트 생성
			while (rs.next()) {
				GuestBook GB = new GuestBook(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getInt("id"),
					rs.getInt("user_id"),
					rs.getInt("exhibition_id"),
					rs.getString("content"),
					rs.getString("date"));
				myGBList.add(GB);				// List에 User 객체 저장
			}		
			return myGBList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
}
