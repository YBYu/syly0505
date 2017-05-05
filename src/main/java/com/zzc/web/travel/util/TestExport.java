package com.zzc.web.travel.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * 
 *                  
 * @date: 2017年1月19日
 * @Author: 龙亚辉
 * @Email: 502230926@qq.com
 * @FileName: TestExport.java
 * @Version: 1.0
 * @About: 导出练习 
 *
 */
public class TestExport {
	POIFSFileSystem fs;
	HSSFWorkbook wb;
	HSSFSheet sheet;
	HSSFRow row;
	public static void main(String[] args) throws Exception {
		TestExport tt = new TestExport();
		String name ="sheet1";
		String modelpath="E:\\b\\测试1.xls";
		Map<String, Object> params = new HashMap<>();
		params.put("a", "aa");
		params.put("b", "gq");
		params.put("c", "你好");
		params.put("d", "多少");
		params.put("e", "11");
		try {
			
			HSSFWorkbook replaceExcel = tt.replaceExcel(name,modelpath,params);
			 FileOutputStream out = new FileOutputStream("E:\\b\\0111.xls");
			 replaceExcel.write(out);
		      out.close(); 
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	/**
	 * 
	 * @date: 2017年1月19日
	 * @Author: 龙亚辉
 *   @Email: 502230926@qq.com
	 * @param: modelPath 模板路径
	 * sheetName  表名
	 * param 要替换的名称
	 * @return: 替换模板中的字符串
	 * @throws java.io.IOException 
	 */
	public HSSFWorkbook replaceExcel(String sheetName,String modelPath,Map<String, Object> param) throws java.io.IOException{
		 try {  
	           fs = new POIFSFileSystem(new FileInputStream(modelPath));  
	           wb = new HSSFWorkbook(fs);  
	           sheet = wb.getSheet(sheetName);  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } 
	    replaceExcelDate(param);
	    return wb;
	}
	
	
   
    public void replaceExcelDate(Map<String, Object> param){
			// 获取行数
			int rowNum = sheet.getLastRowNum();
			for (int i = 0; i < rowNum; i++) {
			 row = sheet.getRow(i);
			
			// 获取行里面的总列数
			int columnNum = 0;
			if(row!=null){
				
			columnNum = row.getPhysicalNumberOfCells();
				
			}
			for (int j = 0; j < columnNum; j++) {
			HSSFCell cell = sheet.getRow(i).getCell(j);
			String cellValue = cell.getStringCellValue();
			for (Entry<String, Object> entry : param.entrySet()) {
			String key = entry.getKey();
			if(key.equals(cellValue)){
			String value = entry.getValue().toString();
			setCellStrValue(i, j, value);
			}
			}
			}
			}
    }
    /** 
     * 设置字符串类型的数据 
     * @param rowIndex--行值 从0开始
     * @param cellnum--列值  从0开始
     * @param value--字符串类型的数据 
     * 
     * @author 龙亚辉
     * @Date: 
     */  
    public void setCellStrValue(int rowIndex, int cellnum, String value) {  
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);  
        cell.setCellValue(value);  
    }
    
    


  

   	
  
}
