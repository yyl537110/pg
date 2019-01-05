package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.Study;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.StudyDao;
import com.slj.pg.service.interfaces.StudyService;
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
public class StudyServiceImpl implements StudyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudyServiceImpl.class);

    @Autowired
    private StudyDao studyDao;

    @Override
    public boolean insert(Study study) {
        return studyDao.insert(study);
    }

    @Override
    public boolean update(Study study) {
        return studyDao.update(study);
    }

    @Override
    public boolean deleteById(Integer id) {
        return studyDao.deleteById(id);
    }

    @Override
    public List<Study> findAll(Map<String, Object> map) {
        return studyDao.findAll(map);
    }

    @Override
    public Study findById(Integer id) {
        return studyDao.findById(id);
    }

    @Override
    public PageDataInfo<Study> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<Study> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<Study> data = studyDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
