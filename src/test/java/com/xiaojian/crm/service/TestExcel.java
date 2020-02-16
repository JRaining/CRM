package com.xiaojian.crm.service;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestExcel {
    @Test
    public void outExcel() throws IOException, WriteException {
        // 创建Excel 对象
        WritableWorkbook wb = Workbook.createWorkbook(new File("E:\\OutExcel\\out.xls"));
        // 创建工作本
        WritableSheet sheet = wb.createSheet("我的第一个工作本",0);
        // 修改列的宽度
        sheet.setColumnView(0,40);
        // 修改行的高度
        sheet.setRowView(0,400);

        // 合并单元格(列，行，列，行)
        // 合并后的单元格内容由左上角单元格文本控制: 1,10
        sheet.mergeCells(0,0,5,5);

        // 创建单元格
        Label cell = new Label(10,0,"你的名字");
        sheet.addCell(cell);

        DateFormat format = new DateFormat("yyyy-MM-dd HH:mm:ss");
        WritableCellFormat wcf = new WritableCellFormat(format);
        // 水平居中
        wcf.setAlignment(Alignment.CENTRE);
        // 垂直居中
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);

        DateTime time = new DateTime(4,0,new Date(),wcf);
        Label cell2 = new Label(0,0,"你的名字",wcf);
        sheet.addCell(cell2);
        sheet.addCell(time);

        // 将写入的内容写到 Excel 中
        wb.write();
        // 关闭资源
        wb.close();
    }
    @Test
    public void readExcel() throws IOException, BiffException {
        // 找到要读取的文件
        Workbook workbook = Workbook.getWorkbook(new File("E:\\OutExcel\\in.xls"));
        // 拿到工作本
        Sheet sheet = workbook.getSheet(0);
        // 知道 Excel 有多少行多少列
        int rows = sheet.getRows();
        int cols = sheet.getColumns();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Cell cell = sheet.getCell(j,i);
                System.out.print(cell.getContents() + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void outTest2() throws IOException, BiffException, WriteException {
        // 查询是否有该文件
            WritableWorkbook wb = Workbook.createWorkbook(new File("E:\\OutExcel\\out.xls"));
            WritableSheet sheet = wb.getSheet(0);

            Label cell = new Label(20,0,"你的名字");
            sheet.addCell(cell);

            wb.write();

            wb.close();

    }
}
