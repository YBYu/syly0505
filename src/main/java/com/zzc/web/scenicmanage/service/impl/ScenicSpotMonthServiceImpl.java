package com.zzc.web.scenicmanage.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.zzc.core.common.hibernate.qbc.CriteriaQuery;
import com.zzc.core.common.hibernate.qbc.HqlQuery;
import com.zzc.core.common.hibernate.qbc.PageList;
import com.zzc.core.common.model.common.DBTable;
import com.zzc.core.common.model.common.UploadFile;
import com.zzc.core.common.model.json.DataGridReturn;
import com.zzc.core.common.model.json.ImportFile;
import com.zzc.core.common.model.json.TreeGrid;
import com.zzc.core.common.service.CommonService;
import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.tag.vo.datatable.DataTableReturn;
import com.zzc.tag.vo.easyui.Autocomplete;
import com.zzc.tag.vo.easyui.ComboTreeModel;
import com.zzc.tag.vo.easyui.TreeGridModel;
import com.zzc.web.system.pojo.base.TSDepart;

public class ScenicSpotMonthServiceImpl extends CommonServiceImpl implements CommonService {

	@Override
	public List<DBTable> getAllDbTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllDbTableSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Serializable save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void batchSave(List<T> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T get(Class<T> class1, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getEntity(Class entityName, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void deleteAllEntitie(Collection<T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void updateEntitie(T pojo) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<T> findByQueryString(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateBySqlString(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> List<T> findListbySql(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getList(Class clas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T singleResult(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList getPageList(CriteriaQuery cq, boolean isOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTableReturn getDataTableReturn(CriteriaQuery cq, boolean isOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGridReturn getDataGridReturn(CriteriaQuery cq, boolean isOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList getPageList(HqlQuery hqlQuery, boolean needParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList getPageListBySql(HqlQuery hqlQuery, boolean isToEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByExample(String entityName, Object exampleEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getListByCriteriaQuery(CriteriaQuery cq, Boolean ispage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T uploadFile(UploadFile uploadFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse createXml(ImportFile importFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parserXml(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<com.zzc.core.common.model.json.ComboTree> comTree(
			List<TSDepart> all,
			com.zzc.core.common.model.json.ComboTree comboTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.zzc.core.common.model.json.ComboTree> ComboTree(List all,
			ComboTreeModel comboTreeModel, List in, boolean recursive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> getAutoList(Autocomplete autocomplete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeSql(String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeSql(String sql, Object... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeSql(String sql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCountForJdbc(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findHql(String hql, Object... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByDetached(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return null;
	}

}
