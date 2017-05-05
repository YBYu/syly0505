package com.zzc.demo.plugin.dao.test;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zzc.dao.annotation.Arguments;
import com.zzc.dao.annotation.MiniDao;
import com.zzc.dao.annotation.ResultType;
import com.zzc.dao.annotation.Sql;
import com.zzc.dao.hibernate.MiniDaoSupportHiber;
import com.zzc.demo.plugin.entity.test.ZZCMinidaoEntity;

/**
 * Minidao例子
 * @author fancq
 * 
 */
//@Repository("jeecgMinidaoDao")
@MiniDao
public interface ZZCMinidaoDao extends MiniDaoSupportHiber<ZZCMinidaoEntity> {
	@Arguments({"jeecgMinidao", "page", "rows"})
	public List<Map> getAllEntities(ZZCMinidaoEntity jeecgMinidao, int page, int rows);

	@Arguments({"jeecgMinidao", "page", "rows"})
	@ResultType(ZZCMinidaoEntity.class)
	public List<ZZCMinidaoEntity> getAllEntities2(ZZCMinidaoEntity jeecgMinidao, int page, int rows);

	//@Arguments("id")
	//JeecgMinidaoEntity getJeecgMinidao(String id);

	@Sql("SELECT count(*) FROM jeecg_minidao")
	Integer getCount();

	@Sql("SELECT SUM(salary) FROM jeecg_minidao")
	Integer getSumSalary();

	/*@Arguments("jeecgMinidao")
	int update(JeecgMinidaoEntity jeecgMinidao);

	@Arguments("jeecgMinidao")
	void insert(JeecgMinidaoEntity jeecgMinidao);

	@Arguments("jeecgMinidao")
	void delete(JeecgMinidaoEntity jeecgMinidao);*/
}
