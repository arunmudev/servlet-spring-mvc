package org.company.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.company.dao.ServletDbDao;
import org.company.dao.ServletDbDaoImpl;
import org.company.model.ServletDbModel;
import org.company.service.ServletDbService;
import org.company.service.ServletDbServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;

/**
 * Servlet implementation class ServletDbController
 */
@Controller
public class ServletDbController{
	private ServletDbModel model;
	private ServletDbService servletDbService = new ServletDbServiceImpl();

	@RequestMapping("/")
	public String index() throws SQLException {
		System.out.println("Servlet-Spring-MVC controller");
		return "index";	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value="/ServletDbController",method=RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			response.setContentType("text/plain"); 	
			System.out.println("load");
			List<ServletDbModel> list = servletDbService.getAllData();
			Gson gson = new Gson();
			String json = gson.toJson(list);			
			response.getWriter().write(json); 		     
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value="/ServletDbController",method=RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operationType");
		try{
			switch(operationType){
			case "insert" : 
				insert(request, response);
				break;
			case "update" : 
				update(request, response);
				break;
			case "delete" : 
				delete(request,response);
				break;
			}
		}catch(Exception e){
			throw new IOException(e);
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) {
		String issueTitle = request.getParameter("issueInput");
		String assignee = request.getParameter("assigneeInput");
		String priority = request.getParameter("priorityInput");
		String issueIn = request.getParameter("idInput");
		Integer issueId =  Integer.parseInt(issueIn);
		model = new ServletDbModel(issueId, issueTitle,assignee,priority);
		try {
			servletDbService.insert(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		String issueTitle = request.getParameter("issueInput");
		String assignee = request.getParameter("assigneeInput");
		String issueIn = request.getParameter("idInput");
		String priority = request.getParameter("priorityInput");
		Integer issueId =  Integer.parseInt(issueIn);
		model = new ServletDbModel(issueId, issueTitle,assignee,priority);
		try{
			servletDbService.update(model);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String issueIn = request.getParameter("idInput");
		Integer issueId =  Integer.parseInt(issueIn);
		try{
			servletDbService.delete(issueId);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
