package com.slj.pg.dao.impl;

import com.slj.pg.bean.Volunteer;
import com.slj.pg.dao.interfaces.VolunteerDao;
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
public class VolunteerDaoImpl implements VolunteerDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(VolunteerDaoImpl.class);

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public boolean insert(Volunteer volunteer) {
        try {
            return volunteerMapper.insert(volunteer);
        }catch (Exception e) {
            LOGGER.error("sql execute error VolunteerDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(Volunteer volunteer) {
        try {
            return volunteerMapper.update(volunteer);
        }catch (Exception e) {
            LOGGER.error("sql execute error VolunteerDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return volunteerMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error VolunteerDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<Volunteer> findAll(Map<String, Object> map) {
        try {
            return volunteerMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error VolunteerDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public Volunteer findById(Integer id) {
        try {
            return volunteerMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error VolunteerDaoImpl findById",e);
        }
        return new Volunteer();
    }
}
