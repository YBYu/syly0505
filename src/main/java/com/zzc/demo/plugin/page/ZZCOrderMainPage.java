package com.zzc.demo.plugin.page;

import java.util.ArrayList;
import java.util.List;

import com.zzc.demo.plugin.entity.test.ZZCOrderCustomEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderProductEntity;

/**   
 * @Title: Entity
 * @Description: 订单信息 VO
 * @author 张代浩
 * @date 2013-03-19 22:01:34
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class ZZCOrderMainPage implements java.io.Serializable {
	/**订单客户明细*/
	private List<ZZCOrderCustomEntity> jeecgOrderCustomList = new ArrayList<ZZCOrderCustomEntity>();
	public List<ZZCOrderCustomEntity> getJeecgOrderCustomList() {
		return jeecgOrderCustomList;
	}
	public void setJeecgOrderCustomList(List<ZZCOrderCustomEntity> jeecgOrderCustomList) {
		this.jeecgOrderCustomList = jeecgOrderCustomList;
	}
	/**订单产品明细*/
	private List<ZZCOrderProductEntity> jeecgOrderProductList = new ArrayList<ZZCOrderProductEntity>();
	public List<ZZCOrderProductEntity> getJeecgOrderProductList() {
		return jeecgOrderProductList;
	}
	public void setJeecgOrderProductList(List<ZZCOrderProductEntity> jeecgOrderProductList) {
		this.jeecgOrderProductList = jeecgOrderProductList;
	}
}
