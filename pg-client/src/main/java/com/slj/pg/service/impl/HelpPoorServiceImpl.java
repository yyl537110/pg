package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.HelpPoor;
import com.slj.pg.bean.Volunteer;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.HelpPoorDao;
import com.slj.pg.dao.mapper.HelpPoorMapper;
import com.slj.pg.service.interfaces.HelpPoorService;
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
public class HelpPoorServiceImpl implements HelpPoorService {

    @Autowired
    private HelpPoorDao helpPoorDao;

    @Override
    public boolean insert(HelpPoor helpPoor) {
        return helpPoorDao.insert(helpPoor);
    }

    @Override
    public boolean update(HelpPoor helpPoor) {
        return helpPoorDao.update(helpPoor);
    }

    @Override
    public boolean deleteById(Integer id) {
        return helpPoorDao.deleteById(id);
    }

    @Override
    public List<HelpPoor> findAll(Map<String, Object> map) {
        return helpPoorDao.findAll(map);
    }

    @Override
    public HelpPoor findById(Integer id) {
        return helpPoorDao.findById(id);
    }

    @Override
    public PageDataInfo<HelpPoor> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<HelpPoor> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<HelpPoor> data = helpPoorDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
