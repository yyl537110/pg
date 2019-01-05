package com.slj.pg.dao.impl;

import com.slj.pg.bean.News;
import com.slj.pg.dao.interfaces.NewsDao;
import com.slj.pg.dao.mapper.MeetingMapper;
import com.slj.pg.dao.mapper.NewsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Repository
public class NewsDaoImpl implements NewsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDaoImpl.class);

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public boolean insert(News news) {
        try {
            return newsMapper.insert(news);
        }catch (Exception e) {
            LOGGER.error("sql execute error NewsDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(News news) {
        try {
            return newsMapper.update(news);
        }catch (Exception e) {
            LOGGER.error("sql execute error NewsDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return newsMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error NewsDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<News> findAll(Map<String, Object> map) {
        try {
            return newsMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error NewsDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public News findById(Integer id) {
        try {
            return newsMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error NewsDaoImpl findById",e);
        }
        return new News();
    }
}
