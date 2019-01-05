package com.slj.pg.dao.impl;

import com.slj.pg.bean.OrgActivity;
import com.slj.pg.bean.PartyMember;
import com.slj.pg.dao.interfaces.OrgActivityDao;
import com.slj.pg.dao.mapper.OrgActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/28.
 */
@Repository
public class OrgActivityDaoImpl implements OrgActivityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrgActivityDaoImpl.class);

    @Autowired
    private OrgActivityMapper orgActivityMapper;

    @Override
    public boolean insert(OrgActivity orgActivity) {
        try {
            return orgActivityMapper.insert(orgActivity);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrgActivityDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(OrgActivity orgActivity) {
        try {
            return orgActivityMapper.update(orgActivity);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrgActivityDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return orgActivityMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrgActivityDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<OrgActivity> findAll(Map<String, Object> map) {
        try {
            return orgActivityMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrgActivityDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public OrgActivity findById(Integer id) {
        try {
            return orgActivityMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrgActivityDaoImpl findById",e);
        }
        return new OrgActivity();
    }
}
