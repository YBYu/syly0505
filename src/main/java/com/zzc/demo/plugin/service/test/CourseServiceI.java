package com.zzc.demo.plugin.service.test;


import com.zzc.core.common.service.CommonService;
import com.zzc.demo.plugin.entity.test.CourseEntity;

public interface CourseServiceI extends CommonService{

	/**
	 * 保存课程
	 *@Author JueYue
	 *@date   2013-11-10
	 *@param  course
	 */
	void saveCourse(CourseEntity course);
	/**
	 * 更新课程
	 *@Author JueYue
	 *@date   2013-11-10
	 *@param  course
	 */
	void updateCourse(CourseEntity course);

}
