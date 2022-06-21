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
import com.my.dto.Product;

public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 전달 데이터 X 로그인된 (주문자) id값은 세션 객체의 어트리뷰트로 있음
		// 주문 정보는 DB에 있음
		// 응답결
		// session의 주문 내용 확인하고 주문 내용을 json형태로 응답한다
		HttpSession session = request.getSession();
		Map<OrderInfo, Integer> orderInfo = (Map)session.getAttribute("orderInfo");
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		ObjectMapper mapper = new ObjectMapper();
		
		List list = new ArrayList<>();
		for(OrderInfo i : orderInfo.keySet()) {
			Integer orderNo = orderInfo.get(i);
			Integer orderId = orderInfo.get(i);
			Integer orderDt = orderInfo.get(i);
			
			Map map = new HashMap<>();
			map.put("orderNo",orderNo);
//			map.put("orderId",orderId);
			map.put("orderDt",orderDt);
			list.add(map);
		}
		
		for(Product p : cart.keySet()) {
			Integer quantity = cart.get(p);
			Map map = new HashMap<>();
			map.put("p", p);
			map.put("quantity", quantity);
			list.add(map);
		}
		
		String result = mapper.writeValueAsString(list);
		System.out.println(list);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}