package org.company.service;

import java.sql.SQLException;
import java.util.List;

import org.company.dao.ServletDbDao;
import org.company.dao.ServletDbDaoImpl;
import org.company.model.ServletDbModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ServletDbServiceImpl implements ServletDbService {
   
	private ServletDbDao servletDbDao = new ServletDbDaoImpl();
	
	@Override
	public List<ServletDbModel> getAllData() throws SQLException {
      return servletDbDao.listIssues();
	}

	@Override
	public void insert(ServletDbModel model)throws SQLException {
		servletDbDao.insert(model);
	}

	@Override
	public void update(ServletDbModel model) throws SQLException {
		servletDbDao.update(model);
	}

	@Override
	public void delete(Integer issueId) throws SQLException {
		servletDbDao.delete(issueId);
		
	}
}
