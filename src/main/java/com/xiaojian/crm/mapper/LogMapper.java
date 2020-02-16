package com.xiaojian.crm.mapper;

import com.xiaojian.crm.domain.Log;

import java.util.List;

public interface LogMapper {
//    查询所有日志
    List<Log> findAll();
//    id查询日志
    Log findById(Integer id);
//    新增日志
    int add(Log log);
//    删除日志
    int delete(Integer id);
}
