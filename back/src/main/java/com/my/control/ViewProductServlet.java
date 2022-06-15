package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductOracleRepository;
import com.my.repository.ProductRepository;

public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청 전달데이터 얻기
		String prod_no = request.getParameter("prod_no");
		
		//2. DB에서 상품 검색
		ProductRepository respository = new ProductOracleRepository();
		try {
			Product p = respository.selectByProdNo(prod_no);
			//3. 응답 형식 지정
			//Json 형태 -> 기존 페이지에 추가로 끼워넣기 / 주소 url변경 -> clear해서 새로 처리(채워짐)
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("상품번호:"+prod_no);
			out.print("<h1>");
			out.print("상품명:"+p.getProdName());
			out.print("</h1>");
			out.print("가격:"+p.getProdPrice());
			out.print("</html>");
			
			
		} catch (FindException e) { 
			e.printStackTrace();
		}
		
	}

}
