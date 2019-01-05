package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.HelpLog;
import com.slj.pg.bean.Link;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.LinkDao;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.LinkService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    @PostMapping
    @ApiOperation(value = "扶贫对象记录新增", notes = "扶贫对象记录新增", response = HttpMessage.class)
    public boolean insert(Link link) {
        return linkDao.insert(link);
    }

    @Override
    public boolean update(Link link) {
        return linkDao.update(link);
    }

    @Override
    public boolean deleteById(Integer id) {
        return linkDao.deleteById(id);
    }

    @Override
    public List<Link> findAll(Map<String, Object> map) {
        return linkDao.findAll(map);
    }

    @Override
    public Link findById(Integer id) {
        return linkDao.findById(id);
    }

    @Override
    public PageDataInfo<Link> page(SearchCondition searchCondition) {
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        Pagination pagination = searchCondition.getPagination();
        Page<Link> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        List<Link> data = linkDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
