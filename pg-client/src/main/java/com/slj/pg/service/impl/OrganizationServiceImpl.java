package com.slj.pg.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.slj.pg.bean.Organization;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.BaseDao;
import com.slj.pg.dao.interfaces.OrganizationDao;
import com.slj.pg.service.interfaces.BaseService;
import com.slj.pg.service.interfaces.OrganizationService;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 组织机构管理Service
 * Created by yaoyl
 * on 2018/12/24.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public boolean insert(Organization organization) {
        return organizationDao.insert(organization);
    }

    @Override
    public boolean update(Organization organization) {
        return organizationDao.update(organization);
    }

    @Override
    public boolean deleteById(Integer id) {
        return organizationDao.deleteById(id);
    }

    @Override
    public List<Organization> findAll(Map<String, Object> map) {
        return organizationDao.findAll(map);
    }

    @Override
    public Organization findById(Integer id) {
        return organizationDao.findById(id);
    }

    @Override
    public PageDataInfo<Organization> page(SearchCondition searchCondition) {
        return null;
    }

    @Override
    public Organization tree(Integer id) {
        if(id == null) {
            id = 0;
        }
        Organization top = new Organization();
        top.setId(id);
        Map<String,Object> params = Maps.newHashMap();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<Organization> all = organizationDao.findAll(params);
        for (Organization organization : all) {
            if(id.equals(organization.getId())) {
                top = organization;
            }
        }

        getSubs(top,all);
        return top;
    }

    private void getSubs(Organization organization, List<Organization> all) {
        if(organization == null) {
            return;
        }
        for (Organization sub : all) {
            if(organization.getId().equals(sub.getParentId())) {
                if(organization.getSubs() == null) {
                    organization.setSubs(Lists.newArrayList(sub));
                }else {
                    organization.getSubs().add(sub);
                }
                getSubs(sub,all);
            }
        }
    }
}
