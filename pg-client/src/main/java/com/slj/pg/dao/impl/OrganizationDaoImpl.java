package com.slj.pg.dao.impl;

import com.slj.pg.bean.Organization;
import com.slj.pg.dao.interfaces.OrganizationDao;
import com.slj.pg.dao.mapper.OrganizationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDaoImpl.class);

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public boolean insert(Organization organization) {
        try {
            return organizationMapper.insert(organization);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrganizationDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(Organization organization) {
        try {
            return organizationMapper.update(organization);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrganizationDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return organizationMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrganizationDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<Organization> findAll(Map<String, Object> map) {
        try {
            return organizationMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrganizationDaoImpl findAll",e);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Organization findById(Integer id) {
        try {
            return organizationMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error OrganizationDaoImpl findById",e);
        }
        return new Organization();
    }
}
