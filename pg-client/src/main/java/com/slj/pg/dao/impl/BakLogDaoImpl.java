package com.slj.pg.dao.impl;


import com.slj.pg.bean.BakLog;
import com.slj.pg.dao.interfaces.BakLogDao;
import com.slj.pg.dao.interfaces.BaseDao;
import com.slj.pg.dao.mapper.BakLogMapper;
import com.slj.pg.dao.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BakLogDaoImpl implements BakLogDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BakLogDaoImpl.class);
    @Autowired
    private BakLogMapper bakLogMapper;

    @Override
    public boolean insert(BakLog bakLog) {
        try {
            return bakLogMapper.insert(bakLog);
        }catch (Exception e) {
            LOGGER.error("sql execute error BakLogDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(BakLog bakLog) {
        try {
            return bakLogMapper.update(bakLog);
        }catch (Exception e) {
            LOGGER.error("sql execute error BakLogDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return bakLogMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error BakLogDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<BakLog> findAll(Map<String, Object> map) {
        try {
            return bakLogMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error BakLogDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public BakLog findById(Integer id) {
        try {
            return bakLogMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error BakLogDaoImpl findById",e);
        }
        return new BakLog();
    }
}