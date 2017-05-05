package com.zzc.demo.plugin.service.impl.test;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzc.core.common.service.impl.CommonServiceImpl;
import com.zzc.demo.plugin.entity.test.ZZCOrderCustomEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderMainEntity;
import com.zzc.demo.plugin.entity.test.ZZCOrderProductEntity;
import com.zzc.demo.plugin.service.test.JeecgOrderMainServiceI;

@Service("jeecgOrderMainService")
@Transactional
public class ZZCOrderMainServiceImpl extends CommonServiceImpl implements JeecgOrderMainServiceI {

	
	public void addMain(ZZCOrderMainEntity jeecgOrderMain,
			List<ZZCOrderProductEntity> jeecgOrderProducList,
			List<ZZCOrderCustomEntity> jeecgOrderCustomList){
		//保存订单主信息
		this.save(jeecgOrderMain);
		//保存订单产品明细
		for(ZZCOrderProductEntity product:jeecgOrderProducList){
			//外键设置
			product.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
			this.save(product);
		}
		//保存订单客户明细
		for(ZZCOrderCustomEntity custom:jeecgOrderCustomList){
			//外键设置
			custom.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
			this.save(custom);
		}
	}

	
	public void updateMain(ZZCOrderMainEntity jeecgOrderMain,
			List<ZZCOrderProductEntity> jeecgOrderProducList,
			List<ZZCOrderCustomEntity> jeecgOrderCustomList,
			boolean jeecgOrderCustomShow) {
		//保存订单主信息
		this.saveOrUpdate(jeecgOrderMain);
		//删除订单产品明细
		this.commonDao.deleteAllEntitie(this.findByProperty(ZZCOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
		//保存订单产品明细
		for(ZZCOrderProductEntity product:jeecgOrderProducList){
			//外键设置
			product.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
			this.save(product);
		}
		if(jeecgOrderCustomShow){
			//删除订单客户明细
			this.commonDao.deleteAllEntitie(this.findByProperty(ZZCOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
			//保存订单客户明细
			for(ZZCOrderCustomEntity custom:jeecgOrderCustomList){
				//外键设置
				custom.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
				this.save(custom);
			}
		}
	}

	
	public void delMain(ZZCOrderMainEntity jeecgOrderMain) {
		//删除主表信息
		this.delete(jeecgOrderMain);
		//删除订单产品明细
		this.deleteAllEntitie(this.findByProperty(ZZCOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
		//删除订单客户明细
		this.commonDao.deleteAllEntitie(this.findByProperty(ZZCOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
	}
}