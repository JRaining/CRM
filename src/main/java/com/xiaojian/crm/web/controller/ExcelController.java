package com.xiaojian.crm.web.controller;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.page.AjaxResult;
import com.xiaojian.crm.service.IEmployeeService;
import com.xiaojian.crm.util.UserConst;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {

    @Resource
    private IEmployeeService employeeService;

    @RequestMapping("/exportEmpExcel")
    @ResponseBody
    public AjaxResult outExcel() throws IOException, WriteException {
        HttpServletRequest request = UserConst.get();
        System.out.println("***************************");
        System.out.println(request.getContextPath());
        System.out.println("***************************");
        WritableWorkbook wb = Workbook.createWorkbook(new File(request.getContextPath() + "/excel/employee.xls"));
        WritableSheet sheet = wb.createSheet("员工表",0);
        // 时间格式
        DateFormat format = new DateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式
        WritableCellFormat wcf = new WritableCellFormat(format);
        wcf.setAlignment(Alignment.CENTRE);

        String[] titles = {"ID","用户名","真实姓名","联系方式","邮箱","所在部门","入职时间","状态","是否超级管理员"};
        for(int i = 0; i < titles.length; i++){
            Label cell = new Label(i,0,titles[i],wcf);
            sheet.addCell(cell);
        }
        // 获取所有员工
        List<Employee> employeeList = employeeService.findAll();
        AjaxResult result = null;

        Label cell ;
        for(int i = 0; i < employeeList.size(); i++){
            cell = new Label(0,i + 1,employeeList.get(i).getId() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(1,i + 1,employeeList.get(i).getUsername() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(2,i + 1,employeeList.get(i).getRealName() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(3,i + 1,employeeList.get(i).getTel() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(4,i + 1,employeeList.get(i).getEmail() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(5,i + 1,employeeList.get(i).getDept().getName() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(6,i + 1,employeeList.get(i).getInputTime() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(7,i + 1,employeeList.get(i).getStateStr() + "",wcf);
            sheet.addCell(cell);
            cell = new Label(8,i + 1,employeeList.get(i).getAdminStr() + "",wcf);
            sheet.addCell(cell);
        }
        wb.write();
        try {
            wb.close();
            result = new AjaxResult(true,"导出成功 !");
        } catch (WriteException e) {
            e.printStackTrace();
            result =new AjaxResult(false,"导出失败 !");
        }

        return result;
    }
}