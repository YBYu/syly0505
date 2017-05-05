package com.zzc.demo.plugin.service.test;

import net.sf.json.JSONObject;

import com.zzc.core.common.model.json.DataGrid;
import com.zzc.core.common.service.CommonService;
import com.zzc.demo.plugin.entity.test.ZZCJdbcEntity;

public interface ZZCJdbcServiceI extends CommonService{
	public void getDatagrid1(ZZCJdbcEntity pageObj, DataGrid dataGrid);
	public void getDatagrid2(ZZCJdbcEntity pageObj, DataGrid dataGrid);
	public JSONObject getDatagrid3(ZZCJdbcEntity pageObj, DataGrid dataGrid);
	public void listAllByJdbc(DataGrid dataGrid);
}
