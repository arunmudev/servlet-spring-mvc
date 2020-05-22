package org.company.service;

import java.sql.SQLException;
import java.util.List;
import org.company.dao.ServletDbDao;
import org.company.model.ServletDbModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServletDbServiceImpl implements ServletDbService {

	@Autowired
	private ServletDbDao servletDbDao;

	@Override
	public List<ServletDbModel> getAllData() throws SQLException {
		return servletDbDao.listIssues();
	}

	@Override
	public List<ServletDbModel> insert(ServletDbModel model)throws SQLException {
		return servletDbDao.insert(model);
	}

	@Override
	public boolean update(ServletDbModel model) throws SQLException {
		return servletDbDao.update(model);
	}

	@Override
	public boolean delete(ServletDbModel model) throws SQLException {
		return servletDbDao.delete(model);
	}

	@Override
	public List<ServletDbModel> issueDetails(Integer issueIdOne) throws SQLException {
		return servletDbDao.issueDetails(issueIdOne);
	}
}
