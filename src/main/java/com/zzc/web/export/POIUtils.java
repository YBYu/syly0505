package com.zzc.web.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zzc.web.travel.controller.StatisticTravelAnnualController;

/**
 * POI工具類.
 * 
 * <pre>
 * 歷史紀錄：
 * 2012/1/31 LoriKing
 * 	新建檔案
 * </pre>
 * 
 * @author <pre>
 * SD
 * 	
 * PG
 * LoriKing
 * UT
 * 
 * MA
 * </pre>
 * @version $Rev$
 * 
 *          <p/>
 *          $Id$
 * 
 */
public class POIUtils extends XSSFHyperlink
{
    protected POIUtils(int type)
    {
        super(type);
    }

    // ~ Static Fields
    // ==================================================
    public static int STRING = 0;
    public static int LONG = 1;
    public static int DOUBLE = 2;
    public static int BIGDECIMAL = 3;
    public static int FORMULA = 4;

    public static String SQL_KEY = "sqlkey";
    public static String ERROR_KEY = "errorkey";

    // ~ Fields
    // ==================================================

    // ~ Constructors
    // ==================================================

    // ~ Methods
    // ==================================================

    /**
     * 
     * 功能：匯出,生成實體文件 将HSSFWorkbook寫入Excel文件
     * 
     * @param wb
     *            HSSFWorkbook
     * 
     * @param fileName
     *            寫入文件的相對路徑
     * 
     * @param wbName
     *            文件名
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void writeWorkbook(XSSFWorkbook wb, String fileName) throws FileNotFoundException, IOException
    {

        FileOutputStream fos = null;

        try
        {

            File file = new File(fileName);

            if (!file.exists())
            {
                file.getParentFile().mkdirs();
            }

            fos = new FileOutputStream(file);

            wb.write(fos);
        } catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
        } catch (IOException e)
        {
            throw new IOException();
        } finally
        {
            if (fos != null)
            {
                fos.flush();
                fos.close();
            }
        }
    }

    /**
     * 匯出,不生成實體文件
     * 
     * <pre>
     * 2012/1/31 LoriKing
     * </pre>
     * 
     * @param wb
     * @param response
     * @throws IOException
     */
    public static void writeWorkbook(HSSFWorkbook wb, OutputStream ops) throws IOException
    {

        OutputStream toClient = null;

        try
        {

            toClient = ops;
            wb.write(toClient);
        } catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
        } catch (IOException e)
        {
            throw new IOException();
        } finally
        {
            try
            {

                if (toClient != null)
                {
                    toClient.flush();
                    toClient.close();
                }
            } catch (IOException e)
            {
                throw new IOException();
            }
        }
    }

    /**
     * 功能 : 匯入 讀取excel,將資料寫入HSSFWorkbook工作薄
     * 
     * <pre>
     * 2011/8/18 LoriKing
     * </pre>
     * 
     * @param fileName
     *            -- 包含文件路徑的文件名
     * @return HSSFWorkbook
     * @throws Exception
     */
    public static HSSFWorkbook readWookbook(String fileName) throws Exception
    {

        // 輸入流
        FileInputStream fileIn = null;

        // 新的工作簿
        HSSFWorkbook workbook = null;

        try
        {
            workbook = new HSSFWorkbook(POIUtils.class.getResourceAsStream(fileName));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            if (fileIn != null)
            {
                fileIn.close();
            }
            System.out.println(fileName);
        }

        // 將帶有資料的工作簿返回
        return workbook;
    }

    public static HSSFWorkbook readWookbook(InputStream fileIn) throws Exception
    {

        // 新的工作簿
        HSSFWorkbook workbook = null;

        try
        {

            // 讀取文件
            // new FileInputStream(file);
            POIFSFileSystem poiFS = new POIFSFileSystem(fileIn);

            // 將資料寫到工作簿
            workbook = new HSSFWorkbook(poiFS);
        } catch (Exception e)
        {
            return null;
        } finally
        {

            try
            {
                fileIn.close();
            } catch (Exception e)
            {
                throw new Exception(e);
            }
        }
        // 將帶有資料的工作簿返回
        return workbook;
    }

    /**
     * 
     * 功能：HSSFSheet
     * 
     * @param wb
     *            HSSFWorkbook 工作薄
     * 
     * @param sheetName
     *            sheet名稱 String
     * 
     * @return HSSFSheet
     */
    public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName)
    {

        HSSFSheet sheet = wb.createSheet(sheetName);

        // 默認列寬
        // sheet.setDefaultColumnWidth((short)12); //3.0
        sheet.setDefaultColumnWidth(12); // 3.7

        // 無渲染
        sheet.setGridsPrinted(false);

        // 無格線
        sheet.setDisplayGridlines(false);

        return sheet;
    }

    /**
     * 
     * 功能：HSSFRow
     * 
     * @param sheet
     *            HSSFSheet HSSFWorkbook工作薄當前sheet
     * 
     * @param rowNum
     *            行號 int
     * 
     * @param height
     *            行高 int
     * 
     * @return HSSFRow
     */
    public static HSSFRow createRow(HSSFSheet sheet, int rowNum, int height)
    {

        HSSFRow row = sheet.createRow(rowNum);
        row.setHeight((short) height);

        return row;
    }

    /**
     * 
     * 功能：創建CellStyle樣式
     * 
     * @param wb
     *            HSSFWorkbook
     * 
     * @param backgroundColor
     *            背景色
     * 
     * @param foregroundColor
     *            前置色
     * 
     * @param halign
     *            水平方向文字排列(左,中,右)
     * 
     * @param font
     *            字體
     * 
     * @return CellStyle
     */
    public static HSSFCellStyle createCellStyle(HSSFWorkbook wb, short backgroundColor, short foregroundColor,
            short halign, HSSFFont font)
    {

        HSSFCellStyle cs = wb.createCellStyle();

        cs.setAlignment(halign);

        // 垂直方向居中
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cs.setFillBackgroundColor(backgroundColor);
        cs.setFillForegroundColor(foregroundColor);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs.setFont(font);

        return cs;
    }

    /**
     * 
     * 功能 : 創建帶邊框的CellStyle樣式,虛線邊框
     * 
     * @param wb
     *            HSSFWorkbook
     * 
     * @param backgroundColor
     *            背景色
     * 
     * @param foregroundColor
     *            前置色
     * 
     * @param font
     *            字體
     * 
     * @return CellStyle
     */
    public static HSSFCellStyle createBorderCellStyle(HSSFWorkbook wb, short backgroundColor, short foregroundColor,
            short halign, HSSFFont font)
    {

        HSSFCellStyle cs = wb.createCellStyle();

        cs.setAlignment(halign);
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cs.setFillBackgroundColor(backgroundColor);
        cs.setFillForegroundColor(foregroundColor);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs.setFont(font);
        cs.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
        cs.setBorderRight(HSSFCellStyle.BORDER_DASHED);
        cs.setBorderTop(HSSFCellStyle.BORDER_DASHED);
        cs.setBorderBottom(HSSFCellStyle.BORDER_DASHED);

        return cs;
    }

    /**
     * 
     * 功能 : 創建空白cell
     * 
     * @param row
     *            HSSFRow
     * 
     * @param cellNum
     *            int
     * 
     * @param style
     *            HSSFStyle
     * 
     * @return HSSFCell
     */
    public static HSSFCell createCell(HSSFRow row, int cellNum, HSSFCellStyle style)
    {

        // HSSFCell cell = row.createCell((short)cellNum); //3.0
        HSSFCell cell = row.createCell(cellNum); // 3.7
        cell.setCellStyle(style);

        return cell;
    }

    /**
     * 創建帶公式的單元格,並且單元格格式為0.00% .
     * 
     * <pre>
     * 2012/2/21 LoriKing
     * </pre>
     * 
     * @param row
     * @param cellNum
     * @param cellFormula
     * @param style
     * @param workBook
     * @return HSSFCell
     */
    public static HSSFCell createCell(HSSFRow row, int cellNum, String cellFormula, HSSFCellStyle style,
            HSSFWorkbook workBook)
    {

        HSSFCell cell = POIUtils.createCell(row, cellNum, style);
        cell.setCellFormula(cellFormula);

        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        cell.setCellStyle(cellStyle);

        return cell;
    }

    /**
     * 創建單元格
     * 
     * <pre>
     * 2011/8/18 LoriKing
     * </pre>
     * 
     * @param column
     * @param row
     * @param strValue
     * @param style
     */
    public static void createCell(int column, HSSFRow row, String strValue, HSSFCellStyle style)
    {

        // HSSFCell cell = row.createCell((short)column); //3.0
        HSSFCell cell = row.createCell(column); // 3.7
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16); //3.0
        cell.setCellValue(strValue);
        cell.setCellStyle(style);
    }

    /**
     * 創建單元格
     * 
     * <pre>
     * 2012/3/26 LoriKing
     * 
     * </pre>
     * 
     * @param column
     * @param row
     * @param strValue
     * @param style
     * @param sheet
     */
    public static HSSFCell createCell(int column, HSSFRow row, String strValue, HSSFCellStyle style, HSSFSheet sheet)
    {

        sheet.autoSizeColumn(column, true);
        HSSFCell cell = row.createCell(column); // 3.7
        cell.setCellValue(strValue);
        cell.setCellStyle(style);

        return cell;
    }

    /**
     * 創建單元格
     * 
     * <pre>
     * 2012/2/21 LoriKing
     * </pre>
     * 
     * @param column
     * @param row
     * @param intValue
     * @param style
     */
    public static void createCell(int column, HSSFRow row, int intValue, HSSFCellStyle style)
    {
        // HSSFCell cell = row.createCell((short)column); //3.0
        HSSFCell cell = row.createCell(column); // 3.7
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16); //3.0
        cell.setCellValue(intValue);
        cell.setCellStyle(style);
    }

    /**
     * 創建單元格
     * 
     * <pre>
     * 2011/8/18 LoriKing
     * </pre>
     * 
     * @param column
     * @param row
     * @param doubleValue
     * @param style
     */
    public static void createCell(int column, HSSFRow row, Double doubleValue, HSSFCellStyle style)
    {

        // HSSFCell cell = row.createCell((short)column); //3.0
        HSSFCell cell = row.createCell(column); // 3.7
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16); //3.0
        cell.setCellValue(doubleValue);
        cell.setCellStyle(style);
    }

    /**
     * 創建單元格
     * 
     * <pre>
     * 2012/1/31 LoriKing
     * </pre>
     * 
     * @param column
     * @param row
     * @param decValue
     * @param formula
     *            (公式信息)
     * @param style
     */
    public static void createCell(int column, HSSFRow row, BigDecimal decValue, String formula, HSSFCellStyle style)
    {

        // HSSFCell cell = row.createCell((short)column); //3.0
        HSSFCell cell = row.createCell(column); // 3.7
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16); //3.0
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
        cell.setCellStyle(style);

        if (StringUtils.isNotEmpty(formula))
        {
            cell.setCellFormula(formula);
        } else
        {
            cell.setCellValue(decValue == null ? Double.parseDouble("0") : Double.parseDouble(String.valueOf(decValue)));
        }
    }

    /**
     * 將單元格內容轉換成0.00%格式.
     * 
     * <pre>
     * 2012/2/22 LoriKing
     * </pre>
     * 
     * @param cellNum
     * @param row
     * @param strValue
     * @param style
     * @param workBook
     * @return HSSFCell
     */
    public static HSSFCell createCell(int cellNum, HSSFRow row, String strValue, HSSFCellStyle style,
            HSSFWorkbook workBook)
    {

        HSSFCell cell = createCell(row, cellNum, style);
        cell.setCellValue(new Double(StringUtils.isEmpty(strValue) ? "0.0" : strValue));
        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        cell.setCellStyle(cellStyle);

        return cell;
    }

    /**
     * 將單元格內容轉換成0.00%格式.
     * 
     * <pre>
     * 2012/2/22 LoriKing
     * </pre>
     * 
     * @param cellNum
     * @param row
     * @param doubleValue
     * @param style
     * @param workBook
     * @return HSSFCell
     */
    public static HSSFCell createCell(int cellNum, HSSFRow row, Double doubleValue, HSSFCellStyle style,
            HSSFWorkbook workBook)
    {

        HSSFCell cell = createCell(row, cellNum, style);
        cell.setCellValue(doubleValue);
        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        cell.setCellStyle(cellStyle);

        return cell;
    }

    /**
     * 
     * 功能：合并单元格
     * 
     * @param sheet
     *            HSSFSheet
     * 
     * @param firstRow
     *            int
     * 
     * @param lastRow
     *            int
     * 
     * @param firstColumn
     *            int
     * 
     * @param lastColumn
     *            int
     * 
     * @return int 合并区域号码
     */
    public static int mergeCell(HSSFSheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn)
    {
        // return sheet.addMergedRegion(new Region(firstRow, (short)firstColumn,
        // lastRow, (short)lastColumn)); //3.0
        return sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn)); // 3.7
    }

    /**
     * 合併單元格,並給合併後的單元格設置樣式 3.0
     * 
     * <pre>
     * 2012/3/4 LoriKing
     * </pre>
     * 
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstColumn
     * @param lastColumn
     * @param style
     */
    /*
     * public static void setMergeCellStyle(HSSFSheet sheet, int firstRow, int
     * lastRow, int firstColumn, int lastColumn,HSSFCellStyle style) {
     * 
     * sheet.addMergedRegion(new Region(firstRow, (short)firstColumn, lastRow,
     * (short)lastColumn));
     * 
     * for (int i = firstRow; i <= lastRow; i++) {
     * 
     * HSSFRow row = sheet.getRow(i); if (row == null) { row =
     * sheet.createRow(i); }
     * 
     * for (int j = firstColumn; j <= lastColumn; j++) {
     * 
     * HSSFCell cell = row.getCell((short)(j));
     * 
     * if (cell == null) { cell = row.createCell((short)(j)); }
     * cell.setCellStyle(style); } } }
     */

    /**
     * 合併單元格,並給合併後的單元格設置樣式 3.7
     * 
     * <pre>
     * 2012/3/4 LoriKing
     * </pre>
     * 
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstColumn
     * @param lastColumn
     * @param style
     */
    public static void setMergeCellStyle(HSSFSheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn,
            HSSFCellStyle style)
    {

        CellRangeAddress ca = new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn);
        sheet.addMergedRegion(ca);
        setRegionStyle(sheet, ca, style);
    }

    /**
     * 
     * 设置合并单元格的边框样式 3.7
     * 
     * @param sheet
     *            HSSFSheet
     * 
     * @param ca
     *            CellRangAddress
     * 
     * @param style
     *            CellStyle
     */
    public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca, CellStyle style)
    {

        for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++)
        {

            HSSFRow row = HSSFCellUtil.getRow(i, sheet);

            for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++)
            {

                HSSFCell cell = HSSFCellUtil.getCell(row, j);

                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 
     * 功能：创建字体
     * 
     * @param wb
     *            HSSFWorkbook
     * 
     * @param boldweight
     *            short
     * 
     * @param color
     *            short
     * 
     * @return Font
     */
    public static HSSFFont createFont(HSSFWorkbook wb, short boldweight, short color, short size)
    {

        HSSFFont font = wb.createFont();

        font.setBoldweight(boldweight);

        font.setColor(color);

        font.setFontHeightInPoints(size);

        return font;
    }

    // /**
    // *
    // * 设置合并单元格的边框样式
    // *
    // * @param sheet
    // * HSSFSheet
    // *
    // * @param ca
    // * CellRangAddress
    // *
    // * @param style
    // * CellStyle
    // */
    // public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca,
    // CellStyle style) {
    // for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {
    // HSSFRow row = HSSFCellUtil.getRow(i, sheet);
    // for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {
    // HSSFCell cell = HSSFCellUtil.getCell(row, j);
    // cell.setCellStyle(style);
    // }
    // }
    // }

    /**
     * 創建表頭樣式
     * 
     * <pre>
     * 2011/8/18 LoriKing
     * </pre>
     * 
     * @param workbook
     * @param align
     * @param strBorder
     * @param fontSize
     * @param bolder
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook, short align, String strBorder, short fontSize,
            short bolder, String fontType)
    {

        // 設定報表體樣式(中文字體)
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 居中顯示
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(align);

        // 設置邊框
        if ("Y".equals(strBorder))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("left".equals(strBorder))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        } else if ("right".equals(strBorder))
        {

            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        }

        // 設定字體的大小
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setBoldweight(bolder);
        font.setFontName(fontType);
        style.setFont(font);

        return style;
    }

    /**
     * 創建主體樣式
     * 
     * <pre>
     * 2012/1/31 LoriKing
     * </pre>
     * 
     * @param workbook
     * @param align
     * @param strBorder
     * @param fontSize
     * @param bolder
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getBodyCellStyle(HSSFWorkbook workbook, short align, String strBorder, short fontSize,
            short bolder)
    {

        // 設定報表體樣式(中文字體)
        HSSFCellStyle style = workbook.createCellStyle();

        // 居中顯示
        style.setAlignment(align);

        // 設置邊框
        if ("Y".equals(strBorder))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("left".equals(strBorder))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        } else if ("right".equals(strBorder))
        {

            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        }

        // 設定字體的大小
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setBoldweight(bolder);
        style.setFont(font);

        return style;
    }

    /**
     * <pre>
     * Method Name : getTableStyle
     * Description : 取得Cell樣式
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyle(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableTitleStyle
     * Description : 取得Cell樣式
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableTitleStyle(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "", strIsCenter, (short) 18, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableTitleBoldStyle
     * Description : 取得Cell樣式(加粗)
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableTitleBoldStyle(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "", strIsCenter, (short) 12, fontType, "", "Y", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableTitleBoldBorderStyle
     * Description : 取得Cell樣式(加粗)帶框線
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableTitleBoldBorderStyle(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THIN:DETAIL", strIsCenter, (short) 12, fontType, "", "Y", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleBorder
     * Description : 取得Cell樣式-Border
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleBorder(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THIN:DETAIL", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleBorderB
     * Description : 取得Cell樣式-BorderB
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleBorderB(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THIN:DETAIL", strIsCenter, (short) 12, fontType, "", "Y", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleBack
     * Description : 取得Cell樣式-Back
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleBack(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THIN:DETAIL", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "22");
    }

    /**
     * <pre>
     * Method Name : getTableStyleTop
     * Description : 取得Cell樣式-Top
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleTop(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THICK:TOP", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleRight
     * Description : 取得Cell樣式-Right
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleRight(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THICK:RIGHT", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleBOTTOM
     * Description : 取得Cell樣式-BOTTOM
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleBOTTOM(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THICK:BOTTOM", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleRightTop
     * Description : 取得Cell樣式-RightTop
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleRightTop(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THICK:RIGHTANDTOP", strIsCenter, (short) 12, fontType, "", "N", (short) 0, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyleRightBOTTOM
     * Description : 取得Cell樣式-RightBOTTOM
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyleRightBOTTOM(HSSFWorkbook workBook, String strIsCenter, String fontType)
    {

        return getTableStyle(workBook, "THICK:RIGHTANDBOTTOM", strIsCenter, (short) 12, fontType, "", "N", (short) 0,
                "");
    }

    /**
     * <pre>
     * Method Name : getTableStyle
     * Description : 取得Cell樣式
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strIsCenter
     *            對齊方式
     * @param fontType
     *            字體類型
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyle(HSSFWorkbook workBook, String strIsCenter, String fontType,
            short frontColor)
    {

        return getTableStyle(workBook, "", strIsCenter, (short) 12, fontType, "", "N", frontColor, "");
    }

    /**
     * <pre>
     * Method Name : getTableStyle
     * Description : 取得報表頭顯示樣式
     * </pre>
     * 
     * @param workBook
     *            HSSFWorkbook workBook
     * @param strType
     *            邊框樣式
     * @param strIsCenter
     *            對齊方式
     * @param fontSize
     *            字體大小
     * @param fontType
     *            字體類型
     * @param fontColor
     *            字體顏色
     * @param isBOLD
     *            是否加粗顯示
     * @param frontColor
     *            前景色
     * @param backColor
     *            背景色
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle getTableStyle(HSSFWorkbook workBook, String strType, String strIsCenter,
            short fontSize, String fontType, String fontColor, String isBOLD, short frontColor, String backColor)
    {

        // 設定報表體樣式(中文字體)
        HSSFCellStyle style = workBook.createCellStyle();

        // 居中顯示
        if ("Y".equals(strIsCenter))
        {

            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        } else if ("R".equals(strIsCenter))
        {

            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        } else if ("L".equals(strIsCenter))
        {

            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }

        // 邊框加粗
        if ("THICK:LEFT".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("THICK:RIGHT".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("THICK:TOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("THICK:BOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
        } else if ("THICK:LEFTANDTOP".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("THICK:LEFTANDBOTTOM".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
        } else if ("THICK:RIGHTANDTOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("THICK:RIGHTANDBOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
        }

        // 細邊框
        else if ("THIN:DETAIL".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        }

        // 粗邊框
        else if ("THICK:DETAIL".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
        }

        // 虛線邊框
        else if ("DASHED:LEFT".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DASHED:RIGHT".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderRight(HSSFCellStyle.BORDER_DASHED);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DASHED:TOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DASHED:BOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_DASHED);
        } else if ("DASHED:LEFTANDTOP".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
            style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DASHED:LEFTANDBOTTOM".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_DASHED);
        } else if ("DASHED:RIGHTANDTOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_DASHED);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DASHED:RIGHTANDBOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_DASHED);
            style.setBorderBottom(HSSFCellStyle.BORDER_DASHED);
        } else if ("DASHED:DETAIL".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
            style.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
            style.setBorderRight(HSSFCellStyle.BORDER_DASHED);
            style.setBorderBottom(HSSFCellStyle.BORDER_DASHED);
        }

        // 無邊框
        else if ("NONE:DETAIL".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_NONE);
            style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
            style.setBorderRight(HSSFCellStyle.BORDER_NONE);
            style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        } else if ("NONE:LEFTANDRTOPANDRIGHT".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_NONE);
            style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
            style.setBorderRight(HSSFCellStyle.BORDER_NONE);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("NONE:TOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_NONE);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("NONE:BOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
        } else if ("NONE:TOPBOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
            style.setBorderRight(HSSFCellStyle.BORDER_NONE);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("NONE:LEFTRIGINT".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
            style.setBorderRight(HSSFCellStyle.BORDER_NONE);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        }

        // 雙線邊框
        else if ("DOUBLE:LEFT".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DOUBLE:RIGHT".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DOUBLE:TOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DOUBLE:BOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        } else if ("DOUBLE:LEFTANDTOP".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DOUBLE:LEFTANDBOTTOM".equals(strType))
        {

            style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        } else if ("DOUBLE:RIGHTANDTOP".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        } else if ("DOUBLE:RIGHTANDBOTTOM".equals(strType))
        {

            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        } else if ("DOUBLE:TOPANDRIGHTANDBOTTOM".equals(strType))
        {
            style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        } else if ("DOUBLE:TOPANDLEFTANDBOTTOM".equals(strType))
        {
            style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
        }

        if (frontColor != 0)
        {

            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(frontColor);
        } else
        {
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        }

        if (StringUtils.isNotEmpty(backColor))
        {

            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFPalette palette = workBook.getCustomPalette();

            if ("Yellow".equals(backColor))
            {

                palette.setColorAtIndex(HSSFColor.LIGHT_YELLOW.index, (byte) 255, (byte) 255, (byte) 153);
                style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            } else if ("Green".equals(backColor))
            {
                palette.setColorAtIndex(HSSFColor.LIGHT_GREEN.index, (byte) 204, (byte) 255, (byte) 255);
                style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            } else
            {
                palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index, (byte) 192, (byte) 192, (byte) 192);
                style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            }
        }

        // 設定字體的大小
        HSSFFont font = workBook.createFont();

        if ("EN".equals(fontType))
        {

            font.setFontName("Times New Roman");
        } else if ("ZH".equals(fontType))
        {

            font.setFontName("標楷體");
        } else
        {
            font.setFontName(fontType);
        }

        font.setFontHeightInPoints((short) fontSize);

        if (null != fontColor && fontColor.length() > 0)
        {

            font.setColor(Short.parseShort(fontColor));
        }

        if ("Y".equals(isBOLD))
        {

            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }

        style.setFont(font);

        return style;
    }

    /**
     * 設置超鏈接的樣式
     * 
     * <pre>
     * 2012/3/22 LoriKing
     * 
     * </pre>
     * 
     * @param workBook
     * @return HSSFCellStyle
     */
    public static final HSSFCellStyle getLinkStyle(HSSFWorkbook workBook)
    {

        // 設置超連接樣式
        HSSFCellStyle hlink_style = workBook.createCellStyle();
        HSSFFont hlink_font = workBook.createFont();
        hlink_font.setUnderline(HSSFFont.U_SINGLE);
        hlink_font.setColor(HSSFColor.BLUE.index);
        hlink_style.setFont(hlink_font);

        return hlink_style;
    }

    /**
     * 鏈接web
     * 
     * <pre>
     * 2012/3/22 LoriKing
     * 
     * </pre>
     * 
     * @param strUrl
     * @param cell
     */
    public static final void setAddressUrl(String strUrl, HSSFCell cell)
    {
        HSSFHyperlink hyperlink = new HSSFHyperlink(HSSFHyperlink.LINK_URL);
        hyperlink.setAddress(strUrl);
        cell.setHyperlink(hyperlink);
    }

    /**
     * 鏈接文件
     * 
     * <pre>
     * 2012/3/22 LoriKing
     * 
     * </pre>
     * 
     * @param fileName
     * @param cell
     */
    public static final void setAddressFile(String fileName, HSSFCell cell)
    {
        HSSFHyperlink hyperlink = new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
        hyperlink.setAddress(fileName);
        cell.setHyperlink(hyperlink);
    }

    /**
     * 鏈接email
     * 
     * <pre>
     * 2012/3/22 LoriKing
     * 
     * </pre>
     * 
     * @param emailAddr
     * @param cell
     */
    public static final void setAddressEmail(String emailAddr, HSSFCell cell)
    {
        HSSFHyperlink hyperlink = new HSSFHyperlink(HSSFHyperlink.LINK_EMAIL);
        hyperlink.setAddress(emailAddr);
        cell.setHyperlink(hyperlink);
    }

    /**
     * 鏈接sheet
     * 
     * <pre>
     * 2012/3/22 LoriKing
     * 
     * </pre>
     * 
     * @param sheetName
     * @param cell
     */
    public static final void setAddressSheet(String sheetName, HSSFCell cell)
    {
        HSSFHyperlink hyperlink = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
        hyperlink.setAddress("'" + sheetName + "'!A1");
        cell.setHyperlink(hyperlink);
    }

    /**
     * 鏈接sheet
     * 
     * <pre>
     * 2012/3/22 LoriKing
     * 
     * </pre>
     * 
     * @param sheet
     * @param cell
     */
    public static final void setAddressSheet(HSSFSheet sheet, HSSFCell cell)
    {
        HSSFHyperlink hyperlink = new HSSFHyperlink(HSSFHyperlink.LINK_DOCUMENT);
        hyperlink.setAddress("'" + sheet.getSheetName() + "'!A1");
        cell.setHyperlink(hyperlink);
    }

    /**
     * 创建名称
     * 
     * @param wb
     * @param name
     * @param expression
     * @return
     */
    public static HSSFName createName(HSSFWorkbook wb, String name, String expression)
    {
        HSSFName refer = wb.createName();
        refer.setRefersToFormula(expression);
        refer.setNameName(name);
        return refer;
    }

    /**
     * 设置数据有效性（通过名称管理器级联相关）
     * 
     * @param name
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     */
    public static HSSFDataValidation setDataValidation(String name, int firstRow, int endRow, int firstCol, int endCol)
    {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(name);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol,
                (short) endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);
        return data_validation;
    }

    public static final String getColumnLetter(int columnNum)
    {

        return (columnNum / 26) > 0 ? getLetter(columnNum / 26 - 1) + getLetter(columnNum % 26) : getLetter(columnNum);
    }

    public static final String getLetter(int columnNum)
    {

        switch (columnNum)
        {
        case 0:
            return "A";
        case 1:
            return "B";
        case 2:
            return "C";
        case 3:
            return "D";
        case 4:
            return "E";
        case 5:
            return "F";
        case 6:
            return "G";
        case 7:
            return "H";
        case 8:
            return "I";
        case 9:
            return "J";
        case 10:
            return "K";
        case 11:
            return "L";
        case 12:
            return "M";
        case 13:
            return "N";
        case 14:
            return "O";
        case 15:
            return "P";
        case 16:
            return "Q";
        case 17:
            return "R";
        case 18:
            return "S";
        case 19:
            return "T";
        case 20:
            return "U";
        case 21:
            return "V";
        case 22:
            return "W";
        case 23:
            return "X";
        case 24:
            return "Y";
        case 25:
            return "Z";
        }

        return "";
    }
  //*********************************模板下载***********************************8  
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
    public static HSSFWorkbook replaceExcel(String sheetName,String modelPath,Map<String, Object> param) throws java.io.IOException{
		   POIFSFileSystem fs = new POIFSFileSystem(StatisticTravelAnnualController.class.getResourceAsStream(modelPath));  
     HSSFWorkbook wb = new HSSFWorkbook(fs);  
     HSSFSheet sheet = wb.getSheetAt(0);  
    replaceExcelDate(sheet, param);
return wb;
}
	
	
   
    public static void replaceExcelDate(HSSFSheet sheet,Map<String, Object> param){
		// 获取行数
	HSSFRow row = null;
		int rowNum = sheet.getLastRowNum() + 1;
		for (int i = 0; i < rowNum; i++) {
		 row = sheet.getRow(i);
		
		// 获取行里面的总列数
		int columnNum = 0;
		if(row!=null){
			
		columnNum = row.getPhysicalNumberOfCells();
			
		}
		for (int j = 0; j < columnNum; j++) {
		HSSFCell cell = sheet.getRow(i).getCell(j);
		System.out.println("第"+i+"行"+"第:"+j+"列");
		String cellValue = getCellStringValue(cell);
		for (Entry<String, Object> entry : param.entrySet()) {
		String key = entry.getKey();
		if(key.equals(cellValue)){
		String value = entry.getValue() == null ? "" : entry.getValue().toString();
		setCellStrValue(sheet, i, j, value);
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
    public static void setCellStrValue(HSSFSheet sheet,int rowIndex, int cellnum, String value) {  
        HSSFCell cell = sheet.getRow(rowIndex).getCell(cellnum);  
        cell.setCellValue(value);  
    }
    
    /**
     * 根据单元格不同属性返回字符串数值 
     * @param cell
     * @return
     */
    public static String getCellStringValue(HSSFCell cell) {   
      String cellValue = "";   
     try{
    	 switch (cell.getCellType()) {   
         case HSSFCell.CELL_TYPE_STRING:   
           cellValue = cell.getStringCellValue();   
           if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)   
             cellValue = " ";   
           break;   
         case HSSFCell.CELL_TYPE_NUMERIC:   
           cellValue = String.valueOf(cell.getNumericCellValue());   
           break;   
         case HSSFCell.CELL_TYPE_FORMULA:   
           cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);   
           cellValue = String.valueOf(cell.getNumericCellValue());   
           break;   
         case HSSFCell.CELL_TYPE_BLANK:   
           cellValue = " ";   
           break;   
         case HSSFCell.CELL_TYPE_BOOLEAN:   
           break;   
         case HSSFCell.CELL_TYPE_ERROR:   
           break;   
         default:   
           break;   
         }   
         return cellValue;   
     }catch(Exception e){
    	 return null;
     }
    }  
}
