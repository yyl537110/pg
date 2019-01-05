package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.HelpCadre;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.HelpCadreDao;
import com.slj.pg.service.interfaces.HelpCadreService;
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
public class HelpCadreServiceImpl implements HelpCadreService {

    @Autowired
    private HelpCadreDao helpCadreDao;

    @Override
    public boolean insert(HelpCadre helpCadre) {
        return helpCadreDao.insert(helpCadre);
    }

    @Override
    public boolean update(HelpCadre helpCadre) {
        return helpCadreDao.update(helpCadre);
    }

    @Override
    public boolean deleteById(Integer id) {
        return helpCadreDao.deleteById(id);
    }

    @Override
    public List<HelpCadre> findAll(Map<String, Object> map) {
        return helpCadreDao.findAll(map);
    }

    @Override
    public HelpCadre findById(Integer id) {
        return helpCadreDao.findById(id);
    }

    @Override
    public PageDataInfo<HelpCadre> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<HelpCadre> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<HelpCadre> data = helpCadreDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
