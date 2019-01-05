package com.slj.pg.dao.impl;

import com.slj.pg.bean.Link;
import com.slj.pg.dao.interfaces.LinkDao;
import com.slj.pg.dao.mapper.LinkMapper;
import com.slj.pg.dao.mapper.MeetingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Repository
public class LinkDaoImpl implements LinkDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkDaoImpl.class);

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public boolean insert(Link link) {
        try {
            return linkMapper.insert(link);
        }catch (Exception e) {
            LOGGER.error("sql execute error LinkDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(Link link) {
        try {
            return linkMapper.update(link);
        }catch (Exception e) {
            LOGGER.error("sql execute error LinkDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return linkMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error LinkDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<Link> findAll(Map<String, Object> map) {
        try {
            return linkMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error LinkDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public Link findById(Integer id) {
        try {
            return linkMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error LinkDaoImpl findById",e);
        }
        return new Link();
    }
}
