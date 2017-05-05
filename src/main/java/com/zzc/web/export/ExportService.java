package com.zzc.web.export;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzc.web.system.service.SystemService;

@Component
public class ExportService
{
    private int columnNum;
    
    private int startRow = 1;
    
    private int startColumn = 0;

    public ExportService(int columnNum)
    {
        this.columnNum = columnNum;
    }
    
    public ExportService(int startRow, int columnNum)
    {
    	this.startRow = startRow;
        this.columnNum = columnNum;
    }
    
    public ExportService(int startRow,int startColumn, int columnNum)
    {
    	this.startRow = startRow;
    	this.startColumn = startColumn;
        this.columnNum = columnNum;
    }
    
    private static SystemService systemService;
	@Autowired
	public ExportService(SystemService systemService){
		ExportService.systemService = systemService;
	}

    public HSSFWorkbook getNewModelInfos(String modelPath, String sql) throws Exception
    {

        // 根据路径,得到空模版信息
    	HSSFWorkbook workbook = POIUtils.readWookbook(modelPath);

        // 根据条件,查询模版内容
        // List<Map<String,String>> dt1 = hibernateDao.queryForListWithSql(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> dt1 = systemService.findListbySql(sql);
        if (workbook != null)
        {
            disposeDataTable1(dt1, workbook);
        }
        return workbook;
    }
    
    public HSSFWorkbook getNewModelInfos2(String modelPath, String sql) throws Exception
    {

        // 根据路径,得到空模版信息
    	HSSFWorkbook workbook = POIUtils.readWookbook(modelPath);

        // 根据条件,查询模版内容
        // List<Map<String,String>> dt1 = hibernateDao.queryForListWithSql(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> dt1 = systemService.findListbySql(sql);
        if (workbook != null)
        {
            disposeDataTable2(dt1, workbook);
        }
        return workbook;
    }
    
    public HSSFWorkbook getNewModelInfos2(String modelPath, String sql,HSSFWorkbook workbook) throws Exception
    {

        // 根据条件,查询模版内容
        // List<Map<String,String>> dt1 = hibernateDao.queryForListWithSql(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> dt1 = systemService.findListbySql(sql);
        if (workbook != null)
        {
            disposeDataTable2(dt1, workbook);
        }
        return workbook;
    }
    
    
    public HSSFWorkbook getNewModelInfos(String modelPath, String sql, HSSFWorkbook workbook) throws Exception
    {

        // 根据条件,查询模版内容
        // List<Map<String,String>> dt1 = hibernateDao.queryForListWithSql(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> dt1 = systemService.findListbySql(sql);
        if (workbook != null)
        {
            disposeDataTable1(dt1, workbook);
        }
        return workbook;
    }
    
    
    // 添加第一个sheet页内容
    private void disposeDataTable1(List<Object[]> dt, HSSFWorkbook workbook)
    {
        int rowNum = dt.size();
        int startRow = this.startRow;
        int startColumn = 0;

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell cell = null;
        String cellValue = null;

        int k = 0;

        /*
         * 第一層循環:循環所有行,每循環一次,產生一行 第二層循環:循環每一行的所有列,每循環一次,產生一個單元個
         */
        for (int i = startRow; i < rowNum + startRow; i++)
        {

            // Creating Row
            HSSFRow row = sheet.createRow(i);

            for (int j = startColumn; j < this.columnNum + startColumn; j++)
            {
                cellValue = disposeStr(String.valueOf(dt.get(k)[j]));
                cell = row.createCell(j);
                cell.setCellValue(cellValue);
            }
            k++;
        }
    }

    // 添加第一个sheet页内容
    private void disposeDataTable2(List<Object[]> dt, HSSFWorkbook workbook)
    {
        int rowNum = dt.size();
        int startRow = this.startRow;
        int startColumn = this.startColumn;

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell cell = null;
        String cellValue = null;

        int k = 0;

        /*
         * 第一層循環:循環所有行,每循環一次,產生一行 第二層循環:循環每一行的所有列,每循環一次,產生一個單元個
         */
        for (int i = startRow; i < rowNum + startRow; i++)
        {

            // Creating Row
            HSSFRow row = sheet.getRow(i);

            for (int j = startColumn; j < this.columnNum + startColumn; j++)
            {
            	if(this.columnNum == 1) {
            		 cellValue = disposeStr(String.valueOf(dt.get(k)));
            	}else{
            		 cellValue = disposeStr(String.valueOf(dt.get(k)[j]));
            	}
               
            	cell = row.createCell(j);
                cell.setCellValue(cellValue);
            }
            k++;
        }
    }
    
    private String disposeStr(String str)
    {
        if (StringUtils.isEmpty(str))
        {
            return "";
        } else if (StringUtils.lowerCase(str).equals("null"))
        {
            return "";
        }
        return str;
    }
}
