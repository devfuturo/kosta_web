package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dto.Product;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		if("forward".equals(opt)) {
			// 요청 속성 ( 속성명 : 'test', 값: 상품객체) 추가
			Product sample = new Product("F0001","샌드위치",2000);
			request.setAttribute("test",sample);
			
			String path = "/iddupchk"; //iddupchk 과 연결 / 서버 차원에서의 연결(이동)
			RequestDispatcher rd = request.getRequestDispatcher(path); // 현재 사용중인 WebContextPath 내에서만 사용 가능(/back프로젝트 내에서만)
			rd.forward(request, response);
			
		} else if("redirect".equals(opt)){
			response.sendRedirect("http://www.google/com");
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<ul>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=forward\">FORWORD</a>");
			out.print("</li>");
			
			out.print("<li>");
			out.print("<a href=\"move?opt=redirect\">REDIRECT</a>");
			out.print("</li>");

			out.print("</ul>");
		}
		
	}

}
