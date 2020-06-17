package org.company.dao;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.company.model.ServletDbModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.SystemPropertyUtils;

@Repository
public class ServletDbDaoImpl implements ServletDbDao {

	private String url;
	private String user;
	private String password;
	private Connection connection;

	protected void connect()throws SQLException {
		if(connection == null || connection.isClosed()) {
			//open connection
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			@SuppressWarnings("deprecation")
			XmlBeanFactory xml = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
			DriverManagerDataSource datasource = (DriverManagerDataSource) xml.getBean("dataSource");
			this.url = datasource.getUrl();
			this.user = datasource.getUsername();
			this.password = datasource.getPassword();
			connection = DriverManager.getConnection(url, user, password);
		}
	}

	/**
	 * Insert
	 */
	public List<ServletDbModel> insert(ServletDbModel model)throws SQLException {
		connect();	 	
		PreparedStatement statement = null;
		boolean status = false;
		List<ServletDbModel> list = new ArrayList<>();
		try {

			String sql = "INSERT INTO issue_tracker(issue_id,issue_title,assignee,priority,issue_status,Last_updated_time) VALUES(?,?,?,?,?,?);"; 		      
			statement = connection.prepareStatement(sql);
			statement.setInt(1, model.getIssueId());
			statement.setString(2, model.getIssueTitle());
			statement.setString(3, model.getAssignee());
			statement.setString(4, model.getPriority());
			statement.setString(5, model.getIssueStatus());
			statement.setString(6, LocalDateTime.now().toString());
			status = statement.executeUpdate() > 0;
			ServletDbModel model1  = new ServletDbModel(status);
			list.add(model1);
			System.out.println("insert success");
		}catch (SQLException e) {
			String errorMessage = e.getMessage().split("\n")[1];
			ServletDbModel model1  = new ServletDbModel(errorMessage);
			list.add(model1);
		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {

			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {

			}
		}

		return list;
	}

	/**
	 * Fetch all data
	 */	
	public List<ServletDbModel> listIssues() throws SQLException {
		List<ServletDbModel> listModel = new ArrayList<>();
		connect();	 	
		Statement statement = null;
		try {
			String sql = "SELECT * FROM issue_tracker order by last_updated_time desc;";	
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				Integer issueId = resultSet.getInt("issue_id");
				String issueTitle = resultSet.getString("issue_title");
				String assignee = resultSet.getString("assignee");
				String priority = resultSet.getString("priority");
				String issueStatus = resultSet.getString("issue_status");
				String lastUpdatedTime = resultSet.getString("last_updated_time");
				ServletDbModel model = new ServletDbModel(issueId, issueTitle, assignee, priority,issueStatus,lastUpdatedTime);
				listModel.add(model);                
			}
			System.out.println("fetch success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return listModel;
	}	

	/**
	 * Fetch particular data
	 */	
	public List<ServletDbModel> issueDetails(ServletDbModel model) throws SQLException {
		List<ServletDbModel> issueDetails = new ArrayList<>();
		connect();	 	
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM issue_tracker where issue_id=?;";	
			statement = connection.prepareStatement(sql);
			statement.setInt(1, model.getIssueId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Integer issueId = resultSet.getInt("issue_id");
				String issueTitle = resultSet.getString("issue_title");
				String assignee = resultSet.getString("assignee");
				String priority = resultSet.getString("priority");
				String issueStatus = resultSet.getString("issue_status");
				String lastUpdatedTime = resultSet.getString("last_updated_time");
				ServletDbModel model1 = new ServletDbModel(issueId, issueTitle, assignee, priority,issueStatus,lastUpdatedTime);
				issueDetails.add(model1);
			}
			System.out.println("fetch success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return issueDetails;
	}	

	/**
	 * update particular data
	 */	
	public boolean update(ServletDbModel model) throws SQLException {

		connect();	 	
		PreparedStatement statement = null;
		boolean status = false;
		try {
			String sql = "UPDATE issue_tracker SET issue_title=?,assignee=?,priority=?,Last_updated_time=? where issue_id=?;";	
			statement = connection.prepareStatement(sql);
			statement.setString(1, model.getIssueTitle());
			statement.setString(2, model.getAssignee());
			statement.setString(3, model.getPriority());
			statement.setString(4, LocalDateTime.now().toString());
			statement.setInt(5, model.getIssueId());
			status = statement.executeUpdate() > 0;
			System.out.println("update success");
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}

		return status;
	}

	/**
	 * Delete particular data from db
	 * @param issueId
	 * @return 
	 * @throws SQLException 
	 */
	public boolean delete(ServletDbModel model) throws SQLException {
		connect();	 	
		PreparedStatement statement = null;
		boolean status=false;
		try {
			String sql = "DELETE FROM issue_tracker where issue_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, model.getIssueId());
			status = statement.executeUpdate() > 0;
		}catch (SQLException e) {

		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return status;

	}
	
	@Test
	public void main() throws NumberFormatException, IOException {
	//	List<String> list = new ArrayList<>();
	//	ServletDbModel model = new ServletDbModel();
	//	list.add("one");
//		list.add("two");
//		list.add("three");
//		for (String string : list) {
//			System.out.println(string);	
//		}
		
//		String ar[] = {"one" ,"two"};
//		int i;
//		//System.out.println(ar.length);
//		//System.out.println(ar[0]);
//       for(i=1;i<10;i++) {
//    	   System.out.println(ar[i]);
//       }
		DataInputStream din = new DataInputStream(System.in);
		
		int number = Integer.parseInt(din.readLine());
		factorial(number);
		String string = "arun studied";
		stringReverse(string);
		
	}

	private void factorial(int number) {
		int fact = 1;
		for(int i=1;i<=number;i++) {
			 
		   fact	= i*fact;
		}
		System.out.print(fact);
		
	}
	
	private void stringReverse(String string) {
		char[] ar= string.toCharArray();
		int i;
		for(i=string.length()-1;i>=0;i--) {
			System.out.print(ar[i]);
		}
	}
}


