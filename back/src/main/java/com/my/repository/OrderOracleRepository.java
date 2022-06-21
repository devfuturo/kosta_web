package com.my.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	@Override
	public void insert(OrderInfo info) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			insertInfo(con, info); 
			insertLines(con, info.getLines()); // 같은 커넥션(세션-DB와의 커넥션. 세션트래킹의 세션X) 내에서
			//같은 DB연결 내에서 두 메서드(insertInfo, insertLines) 사용
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
	}
	private void insertInfo(Connection con, OrderInfo info) throws SQLException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO order_info(ORDER_NO,ORDER_ID,ORDER_DT) VALUES (order_seq.NEXTVAL, ?, SYSDATE)"; // 시퀀스 사용(일련번호)
		//같은 세션 내에서는 NEXTVAL을 CURRENT VAL 할 수 있음?!
		pstmt = con.prepareStatement(insertInfoSQL);
		pstmt.setString(1, info.getOrderId()); 
		// ▲ 재사용성을 높이기 위해서 (1, session.getAttribute("loginId")와 같이 웹과 연관된 내용 쓰지 않는 것이 좋음
		pstmt.executeUpdate();
	}
	private void insertLines(Connection con, List<OrderLine> lines) throws SQLException{
		PreparedStatement pstmt = null;
		String insertLineSQL = 
				"INSERT INTO order_line(ORDER_NO, ORDER_PROD_NO,ORDER_QUANTITY) VALUES (order_seq.CURRVAL, ?, ?)"; 
		pstmt = con.prepareStatement(insertLineSQL);
		for(OrderLine line: lines) {
			String prodNo = line.getOrderP().getProdNo();
			int orderQuantity = line.getOrderQuantity();
			pstmt.setString(1, prodNo);
			pstmt.setInt(2, orderQuantity);
			pstmt.addBatch(); //반복 돌 때마다 Batch에다가만 일괄 추가(저장)
		}
		pstmt.executeBatch(); // 반복이 끝나면 일괄처리 
	}
 	@Override
	public List<OrderInfo> selectById(String orderId) throws FindException {
		// repository에선 웹과 관련된 일 절대 하지X
 		// 로그인 된 orderId를 가지고 와서 orderInfo(주문자가 주문한 내용만 반환)를 List에 넣어서 
 		// 반환된 내용 서블릿이 받아와서 서블릿이 json문자열로 만들어서 응답
 		// 최근 주문번호부터 내림차순, 같은 주문번호라면 상품 번호로 오름차순하여 정렬
 		// select 구문 만들어져야하고 
// 		List<OrderInfo> orderinfo = new ArrayList<>();
// 		
// 		Connection con = null;
// 		PreparedStatement pstmt = null;
// 		ResultSet rs = null;
// 		String selectIdSQL = 
// 				"SELECT i.ORDER_NO, ORDER_DT, ORDER_PROD_NO, ORDER_QUANTITY "
// 				+ "FROM order_line l JOIN order_info i ON(l.order_no = i.order_no) "
// 				+ "ORDER BY 3 DESC , 4";
//		try {
//			con = MyConnection.getConnection();
//			pstmt = con.prepareStatement(selectIdSQL);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				int orderNo = rs.getInt("order_no");
//				String order_id = rs.getString("order_id"); 
//				Date order_date = rs.getDate(0);
//				String order_prod_no = rs.getString("order_prod_no");
//				int quantity = rs.getInt("quantity"); 
//				OrderInfo i = new OrderInfo (order_no, order_id, order_date);
//				OrderLine
//			}else {
//				
//			}
//			return orderInfo;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(rs, pstmt, con);
//		}
 	
//	}
 		return null;
 	} 
}
