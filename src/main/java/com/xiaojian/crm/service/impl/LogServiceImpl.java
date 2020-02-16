package com.xiaojian.crm.service.impl;

import com.xiaojian.crm.domain.Log;
import com.xiaojian.crm.mapper.LogMapper;
import com.xiaojian.crm.service.ILogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements ILogService {
    @Resource
    private LogMapper logMapper;

    @Override
    public List<Log> findAll() {
        return logMapper.findAll();
    }

    @Override
    public Log findById(Integer id) {
        return logMapper.findById(id);
    }

    @Override
    public int add(Log log) {
        return logMapper.add(log);
    }

    @Override
    public int delete(Integer id) {
        return logMapper.delete(id);
    }
}
