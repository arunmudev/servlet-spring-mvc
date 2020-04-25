package org.company.service;

import java.sql.SQLException;
import java.util.List;

import org.company.model.ServletDbModel;

public interface ServletDbService {

	public List<ServletDbModel> getAllData() throws SQLException;

	public void insert(ServletDbModel model) throws SQLException;

	public void update(ServletDbModel model) throws SQLException;

	public void delete(Integer issueId) throws SQLException;
}
