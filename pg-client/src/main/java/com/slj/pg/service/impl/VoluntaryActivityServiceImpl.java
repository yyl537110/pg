package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.VoluntaryActivity;
import com.slj.pg.bean.Volunteer;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.VoluntaryActivityDao;
import com.slj.pg.service.interfaces.VoluntaryActivityService;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/30.
 */
@Service
public class VoluntaryActivityServiceImpl implements VoluntaryActivityService {

    @Autowired
    private VoluntaryActivityDao voluntaryActivityDao;

    @Override
    public boolean insert(VoluntaryActivity voluntaryActivity) {
        return voluntaryActivityDao.insert(voluntaryActivity);
    }

    @Override
    public boolean update(VoluntaryActivity voluntaryActivity) {
        return voluntaryActivityDao.update(voluntaryActivity);
    }

    @Override
    public boolean deleteById(Integer id) {
        return voluntaryActivityDao.deleteById(id);
    }

    @Override
    public List<VoluntaryActivity> findAll(Map<String, Object> map) {
        return voluntaryActivityDao.findAll(map);
    }

    @Override
    public VoluntaryActivity findById(Integer id) {
        return voluntaryActivityDao.findById(id);
    }

    @Override
    public PageDataInfo<VoluntaryActivity> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<VoluntaryActivity> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<VoluntaryActivity> data = voluntaryActivityDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
