package com.zzc.demo.plugin.service.impl.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzc.demo.plugin.dao.test.ZZCMinidaoDao;
import com.zzc.demo.plugin.entity.test.ZZCMinidaoEntity;
import com.zzc.demo.plugin.service.test.ZZCMinidaoServiceI;

/**
 * Minidao例子
 * @author fancq
 *
 */
@Service("jeecgMinidaoService")
@Transactional
public class ZZCMinidaoServiceImpl implements ZZCMinidaoServiceI {
	@Autowired
	private ZZCMinidaoDao jeecgMinidaoDao;
	
	public List<ZZCMinidaoEntity> listAll(ZZCMinidaoEntity jeecgMinidao, int page, int rows) {
		List<ZZCMinidaoEntity> entities = jeecgMinidaoDao.getAllEntities2(jeecgMinidao, page, rows);
		return entities;
	}
	
	public ZZCMinidaoEntity getEntity(Class clazz, String id) {
		ZZCMinidaoEntity jeecgMinidao = (ZZCMinidaoEntity)jeecgMinidaoDao.getByIdHiber(clazz, id);
		return jeecgMinidao;
	}
	
	public void insert(ZZCMinidaoEntity jeecgMinidao) {
		jeecgMinidaoDao.saveByHiber(jeecgMinidao);
	}
	
	public void update(ZZCMinidaoEntity jeecgMinidao) {
		jeecgMinidaoDao.updateByHiber(jeecgMinidao);
	}
	
	public void delete(ZZCMinidaoEntity jeecgMinidao) {
		jeecgMinidaoDao.deleteByHiber(jeecgMinidao);
	}
	
	public void deleteAllEntitie(List<ZZCMinidaoEntity> entities) {
		for (ZZCMinidaoEntity entity : entities) {
			jeecgMinidaoDao.deleteByHiber(entity);
		}
	}
	
	public Integer getCount() {
		return jeecgMinidaoDao.getCount();
	}
	
	public Integer getSumSalary() {
		return jeecgMinidaoDao.getSumSalary();
	}
}