package org.company.dao;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.company.model.ServletDbModel;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

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

			String sql = "INSERT INTO issue_tracker(issue_id,issue_title,assignee,priority) VALUES(?,?,?,?);"; 		      
			statement = connection.prepareStatement(sql);
			statement.setInt(1, model.getIssueId());
			statement.setString(2, model.getIssueTitle());
			statement.setString(3, model.getAssignee());
			statement.setString(4, model.getPriority());
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
			String sql = "SELECT * FROM issue_tracker order by issue_id;";	
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				Integer issueId = resultSet.getInt("issue_id");
				String issueTitle = resultSet.getString("issue_title");
				String assignee = resultSet.getString("assignee");
				String priority = resultSet.getString("priority");
				ServletDbModel model = new ServletDbModel(issueId, issueTitle, assignee, priority);
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
	public List<ServletDbModel> issueDetails(Integer issueIdOne) throws SQLException {
		List<ServletDbModel> issueDetails = new ArrayList<>();
		connect();	 	
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM issue_tracker where issue_id=?;";	
			statement = connection.prepareStatement(sql);
			statement.setInt(1, issueIdOne);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Integer issueId = resultSet.getInt("issue_id");
				String issueTitle = resultSet.getString("issue_title");
				String assignee = resultSet.getString("assignee");
				String priority = resultSet.getString("priority");
				ServletDbModel model = new ServletDbModel(issueId, issueTitle, assignee, priority);
				issueDetails.add(model);
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
			String sql = "UPDATE issue_tracker SET issue_title=?,assignee=?,priority=? where issue_id=?;";	
			statement = connection.prepareStatement(sql);
			statement.setString(1, model.getIssueTitle());
			statement.setString(2, model.getAssignee());
			statement.setString(3, model.getPriority());
			statement.setInt(4, model.getIssueId());
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
}
