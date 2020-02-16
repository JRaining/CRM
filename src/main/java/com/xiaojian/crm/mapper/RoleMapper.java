package com.xiaojian.crm.mapper;

import com.xiaojian.crm.domain.Role;
import com.xiaojian.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
// 查询所有角色
    List<Role> findAll();
//    id查询角色
    Role findById(Integer id);
//    增加新角色
    int add(Role role);
//    添加角色-权限关系
    int roleIncreasesPer(@Param("rId")Integer rId,@Param("pId")Integer pId);
//    删除角色-权限关系
    int delPerOfRole(Integer rId);
//    删除角色
    int delete(Integer id);
//    修改角色信息
    int update(Role role);
//    查询角色总数
    Long queryCount(QueryObject qo);
//    分页查询角色信息
    List<Role> queryForPage(QueryObject qo);

}
