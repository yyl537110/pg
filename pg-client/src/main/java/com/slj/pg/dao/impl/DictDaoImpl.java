package com.slj.pg.dao.impl;

import com.slj.pg.bean.Dict;
import com.slj.pg.dao.interfaces.DictDao;
import com.slj.pg.dao.mapper.DictMapper;
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
public class DictDaoImpl implements DictDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictDaoImpl.class);

    @Autowired
    private DictMapper dictMapper;

    @Override
    public boolean insert(Dict dict) {
        try {
            return dictMapper.insert(dict);
        }catch (Exception e) {
            LOGGER.error("sql execute error DictDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(Dict dict) {
        try {
            return dictMapper.update(dict);
        }catch (Exception e) {
            LOGGER.error("sql execute error DictDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return dictMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error DictDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<Dict> findAll(Map<String, Object> map) {
        try {
            return dictMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error DictDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public Dict findById(Integer id) {
        try {
            return dictMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error DictDaoImpl findById",e);
        }
        return new Dict();
    }
}
