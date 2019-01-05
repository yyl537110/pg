package com.slj.pg.dao.impl;

import com.slj.pg.bean.PartyMember;
import com.slj.pg.bean.PayLog;
import com.slj.pg.dao.interfaces.PayLogDao;
import com.slj.pg.dao.mapper.PayLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/27.
 */
@Repository
public class PayLogDaoImpl implements PayLogDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayLogDaoImpl.class);

    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public boolean insert(PayLog payLog) {
        try {
            return payLogMapper.insert(payLog);
        }catch (Exception e) {
            LOGGER.error("sql execute error PayLogDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(PayLog payLog) {
        try {
            return payLogMapper.update(payLog);
        }catch (Exception e) {
            LOGGER.error("sql execute error PayLogDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return payLogMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error PayLogDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<PayLog> findAll(Map<String, Object> map) {
        try {
            return payLogMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error PayLogDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public PayLog findById(Integer id) {
        try {
            return payLogMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error PayLogDaoImpl findById",e);
        }
        return new PayLog();
    }
}
