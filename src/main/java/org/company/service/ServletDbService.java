package org.company.service;

import java.sql.SQLException;
import java.util.List;
import org.company.model.ServletDbModel;

public interface ServletDbService {

	public List<ServletDbModel> getAllData() throws SQLException;

	public List<ServletDbModel> insert(ServletDbModel model) throws SQLException;

	public boolean update(ServletDbModel model) throws SQLException;

	public boolean delete(ServletDbModel model) throws SQLException;

	public List<ServletDbModel> issueDetails(ServletDbModel model) throws SQLException;
}
