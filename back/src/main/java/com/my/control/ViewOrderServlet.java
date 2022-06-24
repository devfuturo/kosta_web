package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.OrderInfo;
import com.my.repository.OrderOracleRepository;
import com.my.repository.OrderRepository;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response, String order_id) throws ServletException, IOException {
		// 요청 전달 데이터 X 로그인된 (주문자) id값은 세션 객체의 어트리뷰트로 있음
		// 주문 정보는 DB에 있음
		// 응답결
		// session의 주문 내용 확인하고 주문 내용을 json형태로 응답한다
		
		
		HttpSession session = request.getSession();

		// DB에서 주문 정보 검색
		OrderRepository repository = new OrderOracleRepository();
		Map<OrderInfo, Integer> info = (Map)session.getAttribute("loginInfo");
		Map<String, Object> map = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		List list = new ArrayList<>();
		for(OrderInfo i : info.keySet()) {
			
			map.put(order_id, list);
			map.put()
			Map map = new HashMap<>();
		}
	
		// 서블릿이 결과 응답
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
	}
	
}
