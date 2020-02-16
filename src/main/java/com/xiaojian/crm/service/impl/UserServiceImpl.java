package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Employee;
import com.xiaojian.crm.domain.Role;
import com.xiaojian.crm.mapper.EmployeeMapper;
import com.xiaojian.crm.service.IUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements IUserService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = null;
        try{
            employee = employeeMapper.findByName(username);
        } catch(Exception e){
            e.printStackTrace();
        }

        List<Role> roleList = employee.getRoleList();

        User user = new User(employee.getUsername(),employee.getPassword(),
                employee.getState() == 0 ? false:true,true,true,true,getAuthority(roleList));


        return null;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
        for(Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return authorityList;
    }

}
