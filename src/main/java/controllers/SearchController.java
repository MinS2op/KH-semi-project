package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.Common;
import dao.GymDAO;
import dto.GymDTO;

/**
 * developer : Minseop
 */
@WebServlet("*.search")
public class SearchController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=utf8");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		try {
			switch (uri) {
			// 헬스장 검색 전 초기 헬스장 리스트
			case "/main.search":
				this.getMainList(request, response);
				request.getRequestDispatcher("/gym/search.jsp").forward(request,response);
				break;
			// 헬스장 검색 리스트
			case "/gym.search":
				this.getSearchList(request, response);
				request.getRequestDispatcher("/gym/search.jsp").forward(request, response);
				break;
			//
			case "/c.search":
				
				break;
			//
			case "/d.search":
				
				break;
			//
			case "/e.search":
				
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	
	/**
	 * 헬스장 검색 전 초기 헬스장 리스트 메서드
	 */
	protected void getMainList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// gym data 불러오기
		List<GymDTO> gymList = GymDAO.getInstance().selectAll();
		
		// gym data 담기
		request.setAttribute("gymList", gymList);
		
	}

	/**
	 * 헬스장 리스트 검색 메서드
	 */
	protected void getSearchList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 검색어 parameter 가져오기
		String searchInput = request.getParameter("searchInput");

		// 검색 내용에 맞는 gym data 불러오기
		List<GymDTO> gymList = GymDAO.getInstance().selectBySearch(searchInput);

		// gym data 담기
		request.setAttribute("gymList", gymList);
	}






}
