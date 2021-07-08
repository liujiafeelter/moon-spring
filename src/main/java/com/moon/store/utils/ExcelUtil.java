package com.moon.store.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;

/**
 * HSSFWorkbook时报错，改成XSSFWorkbook
 * Exception in thread "main" org.apache.poi.poifs.filesystem.OfficeXmlFileException:
 * The supplied data appears to be in the Office 2007+ XML. You are calling the part of POI that deals with OLE2 Office Documents.
 * You need to call a different part of POI to process this data (eg XSSF instead of HSSF)
 */
public class ExcelUtil {

    public static void main(String[] args) throws Exception{
        String filePath = "C:\\Users\\liujia54\\Desktop\\房源id--帖子id\\test.xlsx";
        readExcel(filePath, 0, 1);
    }

    /**
     * 读取Excel表格
     */
    public static void readExcel(String filePath, int sheetIndex, int cellIndex) throws Exception{
        FileInputStream fileInput = new FileInputStream(new File(filePath));//读取文件
        /******工作簿******************/
        XSSFWorkbook hb = new XSSFWorkbook(fileInput);//工作簿
        XSSFSheet sheet = hb.getSheetAt(sheetIndex);//获取第一个表单sheet

        /******行的处理******************/
        int firstrow = sheet.getFirstRowNum();//获取第一行
        int lastrow = sheet.getLastRowNum();  //获取最后一行
        for (int i=firstrow; i<=lastrow; i++) {
            Row row = sheet.getRow(i);
            if(row == null){
                //行为空的处理
            }
            /******列的处理******************/
            Cell cell = row.getCell(cellIndex);
            BigDecimal num = new BigDecimal(cell.toString());
            System.out.println(num.toPlainString());

            Cell cellWrite = row.getCell(cellIndex+2);
            cellWrite.setCellValue(num.toPlainString());

            /*int firstcell = row.getFirstCellNum();//获取这一行的第一列
            int lastcell = row.getLastCellNum();//获取这一行的最后一列
            List<String> list = new ArrayList<>();
            for (int j=firstcell; j<lastcell; j++) {
                if(j != cellIndex){

                }
                Cell cell = row.getCell(j);//获取第j列
                if(cell == null){
                    //列为空的处理
                }
                System.out.print(cell + "\t");
                list.add(cell.toString());
            }*/
        }

        fileInput.close();
        FileOutputStream out = new FileOutputStream(filePath);
        hb.write(out);
        hb.close();
        out.close();
    }


}
