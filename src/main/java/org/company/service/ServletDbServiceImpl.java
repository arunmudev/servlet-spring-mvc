package org.company.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		 
		List<ServletDbModel> listModel = servletDbDao.listIssues();
	//	listModel.listIterator(index);
		Integer issueId;
		//model.setIssueId(issueId);
		List<ServletDbModel> dbmodel = servletDbDao.insert(model);
		//return servletDbDao.insert(model);
		Map<String ,  List<ServletDbModel>> filterMap = new HashMap<String,  List<ServletDbModel>>();
//		Map<String,String> map = dbmodel.stream().collect(Collectors.toMap(
//				ServletDbModel::is))
		//System.out.println(dbmodel);
		//dbmodel.stream().filter(e -> e.contains(true));
		return dbmodel;
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
	public List<ServletDbModel> issueDetails(ServletDbModel model) throws SQLException {
		return servletDbDao.issueDetails(model);
	}
}
