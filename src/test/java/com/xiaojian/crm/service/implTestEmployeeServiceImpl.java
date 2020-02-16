package com.xiaojian.crm.service;

import com.xiaojian.crm.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class implTestEmployeeServiceImpl {
    @Resource
    private IEmployeeService employeeService;

    @Test
    public void testDeleteById() {

    }

    @Test
    public void testAdd() {
        Employee employee = new Employee();
        employeeService.save(employee);
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testUpdateById() {
    }
}
