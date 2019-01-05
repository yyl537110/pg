package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.OrgActivity;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.OrgActivityDao;
import com.slj.pg.service.interfaces.OrgActivityService;
import com.slj.pg.util.ParamConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/28.
 */
@Service
public class OrgActivityServiceImpl implements OrgActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrgActivityServiceImpl.class);

    @Autowired
    private OrgActivityDao orgActivityDao;

    @Override
    public boolean insert(OrgActivity orgActivity) {
        return orgActivityDao.insert(orgActivity);
    }

    @Override
    public boolean update(OrgActivity orgActivity) {
        return orgActivityDao.update(orgActivity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return orgActivityDao.deleteById(id);
    }

    @Override
    public List<OrgActivity> findAll(Map<String, Object> map) {
        return orgActivityDao.findAll(map);
    }

    @Override
    public OrgActivity findById(Integer id) {
        return orgActivityDao.findById(id);
    }

    @Override
    public PageDataInfo<OrgActivity> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<PayLog> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<OrgActivity> data = orgActivityDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
