package org.company.controller;

import java.io.BufferedReader;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ServletDbController
 */
@CrossOrigin(origins= {"http://localhost:3000"})
@Controller
public class ServletDbController{
	
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
	 * Insert success page 
	 */	
	@RequestMapping(value="/insertSuccess")
	public String insertSucess(){
		return "insertSuccess";	
	}
	
	/**
	 * Insert success page 
	 */	
	@RequestMapping(value="/googleClosure")
	public String googleClosure(){
		return "googleClosure";	
	}
	
	/**
	 * Insert success page 
	 */	
	@RequestMapping(value="/angular")
	public String angular(){
		return "angular";	
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
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine())!=null) {
			buffer.append(line);
		}
		String check = buffer.toString();
		Gson gsonn = new GsonBuilder().create();		
		ServletDbModel model = gsonn.fromJson(check, ServletDbModel.class);	
		try {
			List<ServletDbModel> list = servletDbService.insert(model);		
			String json = gson.toJson(list);
			response.getWriter().write(json);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update Existing issue
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader bufferReader = request.getReader();
		while((line=bufferReader.readLine())!=null) {
			buffer.append(line);
		}
		String json = buffer.toString();
		Gson ggson = new GsonBuilder().create();
		ServletDbModel model = ggson.fromJson(json, ServletDbModel.class);
		try{
			boolean status = servletDbService.update(model);
			response.getWriter().write(Boolean.toString(status));
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Delete Existing issue
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader bufferReader = request.getReader();
		while((line = bufferReader.readLine())!=null) {
			buffer.append(line);
		}
		String json = buffer.toString();
		Gson ggson = new GsonBuilder().create();
        ServletDbModel model = ggson.fromJson(json, ServletDbModel.class);		
		try{
			boolean status = servletDbService.delete(model);
			response.getWriter().write(Boolean.toString(status));
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
