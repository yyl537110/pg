package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.Dict;
import com.slj.pg.bean.HelpCadre;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.DictDao;
import com.slj.pg.service.interfaces.DictService;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public boolean insert(Dict dict) {
        return dictDao.insert(dict);
    }

    @Override
    public boolean update(Dict dict) {
        return dictDao.update(dict);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dictDao.deleteById(id);
    }

    @Override
    public List<Dict> findAll(Map<String, Object> map) {
        return dictDao.findAll(map);
    }

    @Override
    public Dict findById(Integer id) {
        return dictDao.findById(id);
    }

    @Override
    public PageDataInfo<Dict> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<Dict> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        List<Dict> data = dictDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
