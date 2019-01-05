package com.slj.pg.dao.impl;

import com.slj.pg.bean.OrgActivity;
import com.slj.pg.bean.Study;
import com.slj.pg.dao.interfaces.StudyDao;
import com.slj.pg.dao.mapper.OrgActivityMapper;
import com.slj.pg.dao.mapper.StudyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/28.
 */
@Repository
public class StudyDaoImpl implements StudyDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudyDaoImpl.class);

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public boolean insert(Study study) {
        try {
            return studyMapper.insert(study);
        }catch (Exception e) {
            LOGGER.error("sql execute error StudyDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(Study study) {
        try {
            return studyMapper.update(study);
        }catch (Exception e) {
            LOGGER.error("sql execute error StudyDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return studyMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error StudyDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<Study> findAll(Map<String, Object> map) {
        try {
            return studyMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error StudyDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public Study findById(Integer id) {
        try {
            return studyMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error StudyDaoImpl findById",e);
        }
        return new Study();
    }
}
