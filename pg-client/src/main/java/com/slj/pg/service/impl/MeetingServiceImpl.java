package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.Meeting;
import com.slj.pg.bean.OrgActivity;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.MeetingDao;
import com.slj.pg.service.interfaces.MeetingService;
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
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingDao meetingDao;

    @Override
    public boolean insert(Meeting meeting) {
        return meetingDao.insert(meeting);
    }

    @Override
    public boolean update(Meeting meeting) {
        return meetingDao.update(meeting);
    }

    @Override
    public boolean deleteById(Integer id) {
        return meetingDao.deleteById(id);
    }

    @Override
    public List<Meeting> findAll(Map<String, Object> map) {
        return meetingDao.findAll(map);
    }

    @Override
    public Meeting findById(Integer id) {
        return meetingDao.findById(id);
    }

    @Override
    public PageDataInfo<Meeting> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<Meeting> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<Meeting> data = meetingDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
