package org.company.dao;

import java.sql.SQLException;
import java.util.List;

import org.company.model.ServletDbModel;

public interface ServletDbDao {

	public void insert(ServletDbModel model)throws SQLException;
	
	public List<ServletDbModel> listIssues() throws SQLException;
	
	public void update(ServletDbModel model) throws SQLException;
	
	public void delete(Integer issueId) throws SQLException;
}
