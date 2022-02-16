package ofile;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Readexcel {

    public static String getexcel(String path)throws Exception{
        String result="";


            FileInputStream fileInputStream=new FileInputStream(path);
            Workbook workbook;
            if(path.endsWith(".xlsx")) {
                workbook=new XSSFWorkbook(fileInputStream);
            }
            else if(path.endsWith(".xls")) {
                workbook=new HSSFWorkbook(fileInputStream);
            }
            else {
                return "";
            }
            for(Sheet each:workbook){
                for(Row row:each){
                    for(Cell cell:row){
                        if(cell!=null){
                            DataFormatter formatter=new DataFormatter();
                            result=result+formatter.formatCellValue(cell)+"\t";
                        }
                        else{
                            result=result+"\t\t";
                        }
                    }
                    result=result+"\n";
                }
            }
            fileInputStream.close();
            return result;



    }
    public static void main(String[] args) throws Exception{
        String path="D:\\project\\doc\\3212.xlsx";
        String text=getexcel(path);
        System.out.println(text);
    }
}

