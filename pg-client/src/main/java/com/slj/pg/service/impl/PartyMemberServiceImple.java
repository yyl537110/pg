package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slj.pg.bean.PartyMember;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.PartyMemberDao;
import com.slj.pg.service.interfaces.PartyMemberService;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 党员管理Service
 * Created by yaoyl
 * on 2018/12/24.
 */
@Service
public class PartyMemberServiceImple implements PartyMemberService{
    @Autowired
    private PartyMemberDao partyMemberDao;

    @Override
    public boolean insert(PartyMember partyMember) {
        return partyMemberDao.insert(partyMember);
    }

    @Override
    public boolean update(PartyMember partyMember) {
        return partyMemberDao.update(partyMember);
    }

    @Override
    public boolean deleteById(Integer id) {
        return partyMemberDao.deleteById(id);
    }

    @Override
    public List<PartyMember> findAll(Map<String, Object> map) {
        return partyMemberDao.findAll(map);
    }

    @Override
    public PartyMember findById(Integer id) {
        return partyMemberDao.findById(id);
    }

    @Override
    public PageDataInfo<PartyMember> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<PartyMember> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        params.put("status", ParamConstants.STATUS_NORMAL);
        List<PartyMember> data = partyMemberDao.findAll(params);
        pageInfo.getPageSize();
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(),pageInfo.getPageSize());
    }
}
