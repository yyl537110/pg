package com.slj.pg.dao.impl;

import com.slj.pg.bean.Meeting;
import com.slj.pg.bean.OrgActivity;
import com.slj.pg.dao.interfaces.MeetingDao;
import com.slj.pg.dao.mapper.MeetingMapper;
import com.slj.pg.dao.mapper.OrgActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/30.
 */
@Repository
public class MeetingDaoImpl implements MeetingDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingDaoImpl.class);

    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public boolean insert(Meeting meeting) {
        try {
            return meetingMapper.insert(meeting);
        }catch (Exception e) {
            LOGGER.error("sql execute error MeetingDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(Meeting meeting) {
        try {
            return meetingMapper.update(meeting);
        }catch (Exception e) {
            LOGGER.error("sql execute error MeetingDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return meetingMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error MeetingDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<Meeting> findAll(Map<String, Object> map) {
        try {
            return meetingMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error MeetingDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public Meeting findById(Integer id) {
        try {
            return meetingMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error MeetingDaoImpl findById",e);
        }
        return new Meeting();
    }
}
