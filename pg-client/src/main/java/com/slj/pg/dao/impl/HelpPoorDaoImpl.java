package com.slj.pg.dao.impl;

import com.slj.pg.bean.HelpPoor;
import com.slj.pg.dao.interfaces.HelpPoorDao;
import com.slj.pg.dao.mapper.HelpPoorMapper;
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
public class HelpPoorDaoImpl implements HelpPoorDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpPoorDaoImpl.class);

    @Autowired
    private HelpPoorMapper helpPoorMapper;

    @Override
    public boolean insert(HelpPoor helpPoor) {
        try {
            return helpPoorMapper.insert(helpPoor);
        }catch (Exception e) {
            LOGGER.error("sql execute error helpPoorMapper insert",e);
        }
        return false;
    }

    @Override
    public boolean update(HelpPoor helpPoor) {
        try {
            return helpPoorMapper.update(helpPoor);
        }catch (Exception e) {
            LOGGER.error("sql execute error helpPoorMapper update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return helpPoorMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error helpPoorMapper deleteById",e);
        }
        return false;
    }

    @Override
    public List<HelpPoor> findAll(Map<String, Object> map) {
        try {
            return helpPoorMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error helpPoorMapper findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public HelpPoor findById(Integer id) {
        try {
            return helpPoorMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error helpPoorMapper findById",e);
        }
        return new HelpPoor();
    }
}
