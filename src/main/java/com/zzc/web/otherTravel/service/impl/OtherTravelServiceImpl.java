package com.zzc.web.otherTravel.service.impl;

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
import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.tag.vo.datatable.DataTableReturn;
import com.zzc.tag.vo.easyui.Autocomplete;
import com.zzc.tag.vo.easyui.ComboTreeModel;
import com.zzc.tag.vo.easyui.TreeGridModel;
import com.zzc.web.otherTravel.service.OtherTravelService;
import com.zzc.web.system.pojo.base.TSDepart;
import com.zzc.web.system.pojo.base.TSUser;

public class OtherTravelServiceImpl extends CommonServiceImpl implements OtherTravelService {

	@Override
	public TSUser checkUserExits(TSUser user) {
		// TODO Auto-generated method stub
		return this.commonDao.getUserByUserIdAndUserNameExits(user);
	}

	@Override
	public String getUserRole(TSUser user) {
		// TODO Auto-generated method stub
		return this.commonDao.getUserRole(user);
	}

	@Override
	public void pwdInit(TSUser user, String newPwd) {
		this.commonDao.pwdInit(user,newPwd);
		
	}


}
