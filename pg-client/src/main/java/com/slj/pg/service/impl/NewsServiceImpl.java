package com.slj.pg.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.slj.pg.bean.Meeting;
import com.slj.pg.bean.News;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.NewsDao;
import com.slj.pg.service.interfaces.NewsService;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public boolean insert(News news) {
        return newsDao.insert(news);
    }

    @Override
    public boolean update(News news) {
        return newsDao.update(news);
    }

    @Override
    public boolean deleteById(Integer id) {
        return newsDao.deleteById(id);
    }

    @Override
    public List<News> findAll(Map<String, Object> map) {
        return newsDao.findAll(map);
    }

    @Override
    public News findById(Integer id) {
        return newsDao.findById(id);
    }

    @Override
    public PageDataInfo<News> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<News> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<News> data = newsDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    private void test() {
        List<News> data = newsDao.findAll(null);
        Map<Integer,Map<Integer,List<News>>> result = Maps.newHashMap();
        Map<Integer,List<News>> inner;
        for (News news : data) {
            LocalDateTime gmtCreate = news.getGmtCreate();
            int year = gmtCreate.getYear();
            int month = gmtCreate.getMonthValue();
            if(result.containsKey(year)) {
                inner = result.get(year);
                if(inner.containsKey(month)) {
                    inner.get(month).add(news);
                }else {
                    inner.put(month, Lists.newArrayList(news));
                }
            }else {
                inner = Maps.newHashMap();
                inner.put(month,Lists.newArrayList(news));
                result.put(year,inner);
            }
        }
        System.out.println("result:" + JSON.toJSONString(result));
    }
}
