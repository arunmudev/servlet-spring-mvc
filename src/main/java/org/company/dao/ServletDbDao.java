package org.company.dao;

import java.sql.SQLException;
import java.util.List;
import org.company.model.ServletDbModel;

public interface ServletDbDao {

	public List<ServletDbModel> insert(ServletDbModel model)throws SQLException;

	/**
	 * Fetch all data
	 */	
	public List<ServletDbModel> listIssues() throws SQLException;

	public boolean update(ServletDbModel model) throws SQLException;

	public boolean delete(ServletDbModel model) throws SQLException;

	/**
	 * Fetch particular data
	 */	
	public List<ServletDbModel> issueDetails(ServletDbModel model) throws SQLException;

}
