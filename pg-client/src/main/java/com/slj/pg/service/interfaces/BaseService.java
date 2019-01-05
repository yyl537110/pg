package com.slj.pg.service.interfaces;

import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl on 2018/12/23.
 */
public interface BaseService<T> {
    /** 单个增加*/
    boolean insert(T t);

    /** 修改*/
    boolean update(T t);

    /** 删除*/
    boolean deleteById(Integer id);

    /** 查询所有*/
    List<T> findAll(Map<String, Object> map);

    /** 查询一条*/
    T findById(Integer id);

    /** 分页查询*/
    PageDataInfo<T> page(SearchCondition searchCondition);
}
