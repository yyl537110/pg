package com.slj.pg.dao.impl;

import com.slj.pg.bean.PartyMember;
import com.slj.pg.dao.interfaces.PartyMemberDao;
import com.slj.pg.dao.mapper.PartyMemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
@Repository
public class PartyMemberDaoImpl implements PartyMemberDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartyMemberDaoImpl.class);

    @Autowired
    private PartyMemberMapper partyMemberMapper;

    @Override
    public boolean insert(PartyMember partyMember) {
        try {
            return partyMemberMapper.insert(partyMember);
        }catch (Exception e) {
            LOGGER.error("sql execute error PartyMemberDaoImpl insert",e);
        }
        return false;
    }

    @Override
    public boolean update(PartyMember partyMember) {
        try {
            return partyMemberMapper.update(partyMember);
        }catch (Exception e) {
            LOGGER.error("sql execute error PartyMemberDaoImpl update",e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            return partyMemberMapper.deleteById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error PartyMemberDaoImpl deleteById",e);
        }
        return false;
    }

    @Override
    public List<PartyMember> findAll(Map<String, Object> map) {
        try {
            return partyMemberMapper.findAll(map);
        }catch (Exception e) {
            LOGGER.error("sql execute error PartyMemberDaoImpl findAll",e);
        }
        return Collections.emptyList();
    }

    @Override
    public PartyMember findById(Integer id) {
        try {
            return partyMemberMapper.findById(id);
        }catch (Exception e) {
            LOGGER.error("sql execute error PartyMemberDaoImpl findById",e);
        }
        return new PartyMember();
    }
}
