package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.PartyMember;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.PayLogDao;
import com.slj.pg.service.interfaces.PayLogService;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/27.
 */
@Service
public class PayLogServiceImpl implements PayLogService {
    @Autowired
    private PayLogDao payLogDao;

    @Override
    public boolean insert(PayLog payLog) {
        return payLogDao.insert(payLog);
    }

    @Override
    public boolean update(PayLog payLog) {
        return payLogDao.update(payLog);
    }

    @Override
    public boolean deleteById(Integer id) {
        return payLogDao.deleteById(id);
    }

    @Override
    public List<PayLog> findAll(Map<String, Object> map) {
        return payLogDao.findAll(map);
    }

    @Override
    public PayLog findById(Integer id) {
        return payLogDao.findById(id);
    }

    @Override
    public PageDataInfo<PayLog> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<PayLog> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<PayLog> data = payLogDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
