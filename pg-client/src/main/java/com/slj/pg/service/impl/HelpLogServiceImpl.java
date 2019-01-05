package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.slj.pg.bean.HelpCadre;
import com.slj.pg.bean.HelpLog;
import com.slj.pg.bean.HelpPoor;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.HelpCadreDao;
import com.slj.pg.dao.interfaces.HelpLogDao;
import com.slj.pg.dao.interfaces.HelpPoorDao;
import com.slj.pg.service.interfaces.HelpLogService;
import com.slj.pg.util.ParamConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yaoyl
 * on 2018/12/30.
 */
@Service
public class HelpLogServiceImpl implements HelpLogService {

    @Autowired
    private HelpLogDao helpLogDao;

    @Autowired
    private HelpPoorDao helpPoorDao;

    @Autowired
    private HelpCadreDao helpCadreDao;

    @Override
    public boolean insert(HelpLog helpLog) {
        return helpLogDao.insert(helpLog);
    }

    @Override
    public boolean update(HelpLog helpLog) {
        return helpLogDao.update(helpLog);
    }

    @Override
    public boolean deleteById(Integer id) {
        return helpLogDao.deleteById(id);
    }

    @Override
    public List<HelpLog> findAll(Map<String, Object> map) {
        return helpLogDao.findAll(map);
    }

    @Override
    public HelpLog findById(Integer id) {
        HelpLog helpLog = helpLogDao.findById(id);
        if(helpLog.getId() != null) {
            helpLog.setHelpPoor(helpPoorDao.findById(helpLog.getPoorId()));
            helpLog.setHelpCadre(helpCadreDao.findById(helpLog.getCadreId()));
        }
        return helpLog;
    }

    @Override
    public PageDataInfo<HelpLog> page(SearchCondition searchCondition) {
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        Pagination pagination = searchCondition.getPagination();
        Page<HelpLog> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        List<HelpLog> data = helpLogDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
