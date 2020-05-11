package org.company.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.company.model.ServletDbModel;
import org.company.service.ServletDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;

/**
 * Servlet implementation class ServletDbController
 */
@Controller
public class ServletDbController{
	private ServletDbModel model;

	@Autowired
	private ServletDbService servletDbService;

	@Autowired
	private Gson gson;

	@RequestMapping("/")
	public String index(){
		return "index";	
	}
	/**
	 * Create new issue
	 */	
	@RequestMapping(value="/create")
	public String create(){
		System.out.println("Create New Issue1");
		return "create";	
	}

	/**
	 * update Existing issue
	 */	
	@RequestMapping(value="/update")
	public String update(){
		return "update";	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value="/ServletDbController",method=RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			response.setContentType("text/plain"); 	
			List<ServletDbModel> list = servletDbService.getAllData();
			String json = gson.toJson(list);			
			response.getWriter().write(json); 		     
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value="/fetchData",method=RequestMethod.GET)
	protected void fetchData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			response.setContentType("text/plain");
			System.out.println("fetch Data");
			String issueIn = request.getParameter("idInput");
			Integer issueIdOne =  Integer.parseInt(issueIn);
			List<ServletDbModel> list = servletDbService.issueDetails(issueIdOne);
			Gson gson = new Gson();
			String json = gson.toJson(list);			
			response.getWriter().write(json); 		     
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Insert new issue
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	private void insert(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String issueTitle = request.getParameter("issueInput");
		String assignee = request.getParameter("assigneeInput");
		String priority = request.getParameter("priorityInput");
		String issueIn = request.getParameter("idInput");
		Integer issueId =  Integer.parseInt(issueIn);
		model = new ServletDbModel(issueId, issueTitle,assignee,priority);
		try {
			List<ServletDbModel> list = servletDbService.insert(model);
			String json = gson.toJson(list);
			response.getWriter().write(json);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create new issue
	 */	
	@RequestMapping(value="/insertSuccess")
	public String insertSucess(){
		return "insertSuccess";	
	}

	/**
	 * Update Existing issue
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String issueTitle = request.getParameter("issueInput");
		String assignee = request.getParameter("assigneeInput");
		String issueIn = request.getParameter("idInput");
		String priority = request.getParameter("priorityInput");
		Integer issueId =  Integer.parseInt(issueIn);
		model = new ServletDbModel(issueId, issueTitle,assignee,priority);
		try{
			boolean status = servletDbService.update(model);
			response.getWriter().write(Boolean.toString(status));
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Update Existing issue
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String issueIn = request.getParameter("idInput");
		Integer issueId =  Integer.parseInt(issueIn);
		try{
			System.out.println("Delete controller call");
			boolean status = servletDbService.delete(issueId);
			response.getWriter().write(Boolean.toString(status));
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
