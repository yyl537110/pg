package com.slj.pg.dao.impl;

import com.slj.pg.bean.HelpLog;
import com.slj.pg.dao.interfaces.HelpLogDao;
import com.slj.pg.dao.mapper.HelpLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/30.
 */
@Repository
public class HelpLogDaoImpl implements HelpLogDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpLogDaoImpl.class);

    @Autowired
    private HelpLogMapper helpLogMapper;

    @Override
    public boolean insert(HelpLog helpLog) {
        try {
            return helpLogMapper.insert(helpLog);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpLogDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(HelpLog helpLog) {
        try {
            return helpLogMapper.update(helpLog);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpLogDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return helpLogMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpLogDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<HelpLog> findAll(Map<String, Object> map) {
        try {
            return helpLogMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpLogDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public HelpLog findById(Integer id) {
        try {
            return helpLogMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpLogDaoImpl findById",e);
        }
        return new HelpLog();
    }
}
