package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.Study;
import com.slj.pg.bean.Volunteer;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.VolunteerDao;
import com.slj.pg.service.interfaces.VolunteerService;
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
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerDao volunteerDao;

    @Override
    public boolean insert(Volunteer volunteer) {
        return volunteerDao.insert(volunteer);
    }

    @Override
    public boolean update(Volunteer volunteer) {
        return volunteerDao.update(volunteer);
    }

    @Override
    public boolean deleteById(Integer id) {
        return volunteerDao.deleteById(id);
    }

    @Override
    public List<Volunteer> findAll(Map<String, Object> map) {
        return volunteerDao.findAll(map);
    }

    @Override
    public Volunteer findById(Integer id) {
        return volunteerDao.findById(id);
    }

    @Override
    public PageDataInfo<Volunteer> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<Volunteer> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<Volunteer> data = volunteerDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
