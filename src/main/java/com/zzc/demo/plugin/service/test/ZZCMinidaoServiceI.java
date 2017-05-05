package com.zzc.demo.plugin.service.test;

import java.util.List;

import com.zzc.demo.plugin.entity.test.ZZCMinidaoEntity;

/**
 * Minidao例子
 * @author fancq
 *
 */
public interface ZZCMinidaoServiceI {
	public List<ZZCMinidaoEntity> listAll(ZZCMinidaoEntity jeecgMinidao, int page, int rows);
	
	public ZZCMinidaoEntity getEntity(Class clazz, String id);
	
	public void insert(ZZCMinidaoEntity jeecgMinidao);
	
	public void update(ZZCMinidaoEntity jeecgMinidao);
	
	public void delete(ZZCMinidaoEntity jeecgMinidao);
	
	public void deleteAllEntitie(List<ZZCMinidaoEntity> entities);
	
	public Integer getCount();
	
	public Integer getSumSalary();
}
