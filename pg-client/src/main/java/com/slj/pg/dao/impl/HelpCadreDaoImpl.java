package com.slj.pg.dao.impl;

import com.slj.pg.bean.HelpCadre;
import com.slj.pg.dao.interfaces.HelpCadreDao;
import com.slj.pg.dao.mapper.HelpCadreMapper;
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
public class HelpCadreDaoImpl implements HelpCadreDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCadreDaoImpl.class);

    @Autowired
    private HelpCadreMapper helpCadreMapper;

    @Override
    public boolean insert(HelpCadre helpCadre) {
        try {
            return helpCadreMapper.insert(helpCadre);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpCadreDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(HelpCadre helpCadre) {
        try {
            return helpCadreMapper.update(helpCadre);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpCadreDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return helpCadreMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpCadreDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<HelpCadre> findAll(Map<String, Object> map) {
        try {
            return helpCadreMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpCadreDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public HelpCadre findById(Integer id) {
        try {
            return helpCadreMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error HelpCadreDaoImpl findById",e);
        }
        return new HelpCadre();
    }
}
