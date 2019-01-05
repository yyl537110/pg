package com.slj.pg.dao.impl;

import com.slj.pg.bean.VoluntaryActivity;
import com.slj.pg.dao.interfaces.VoluntaryActivityDao;
import com.slj.pg.dao.mapper.VoluntaryActivityMapper;
import com.slj.pg.dao.mapper.VolunteerMapper;
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
public class VoluntaryActivityDaoImpl implements VoluntaryActivityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoluntaryActivityDaoImpl.class);

    @Autowired
    private VoluntaryActivityMapper voluntaryActivityMapper;

    @Override
    public boolean insert(VoluntaryActivity voluntaryActivity) {
        try {
            return voluntaryActivityMapper.insert(voluntaryActivity);
        }catch (Exception e) {
            LOGGER.error("sql execute error VoluntaryActivityDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(VoluntaryActivity voluntaryActivity) {
        try {
            return voluntaryActivityMapper.update(voluntaryActivity);
        }catch (Exception e) {
            LOGGER.error("sql execute error VoluntaryActivityDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return voluntaryActivityMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error VoluntaryActivityDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<VoluntaryActivity> findAll(Map<String, Object> map) {
        try {
            return voluntaryActivityMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error VoluntaryActivityDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public VoluntaryActivity findById(Integer id) {
        try {
            return voluntaryActivityMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error VoluntaryActivityDaoImpl findById",e);
        }
        return new VoluntaryActivity();
    }
}
