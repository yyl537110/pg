package com.slj.pg.dao.impl;

import com.slj.pg.PgClientApplication;
import com.slj.pg.bean.BakLog;
import com.slj.pg.dao.interfaces.BakLogDao;
import com.slj.pg.dao.mapper.BakLogMapper;
import com.slj.pg.util.ParamConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by yaoyl on 2018/12/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PgClientApplication.class)
@Transactional
@Rollback
public class BakLogDaoImplTest {

    @Autowired
    private BakLogDao bakLogDao;

    @Test
    public void insert() throws Exception {
        BakLog bakLog = new BakLog();
        bakLog.setBakFile("E:\\20191211bak.sql");
        bakLog.setStatus(ParamConstants.STATUS_NORMAL);
        bakLog.setGmtCreate(LocalDateTime.now());
        boolean insert = bakLogDao.insert(bakLog);
        Assert.assertTrue(insert);
        System.out.println("insert id:" + bakLog.getId());
    }

    private BakLog init() {
        BakLog bakLog = new BakLog();
        bakLog.setBakFile("E:\\20191211bak.sql");
        bakLog.setStatus(ParamConstants.STATUS_NORMAL);
        bakLog.setGmtCreate(LocalDateTime.now());
        bakLogDao.insert(bakLog);
        return bakLog;
    }


    @Test
    public void update() throws Exception {
        BakLog init = init();
        BakLog updateObj = new BakLog();
        updateObj.setId(init.getId());
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        boolean update = bakLogDao.update(updateObj);
        Assert.assertTrue(update);
    }

    @Test
    public void deleteById() throws Exception {
        BakLog init = init();
        boolean b = bakLogDao.deleteById(init.getId());
        Assert.assertTrue(b);
    }

    @Test
    public void findAll() throws Exception {
        Map<String,Object> params = new HashMap<>();
        List<BakLog> all = bakLogDao.findAll(params);
    }

    @Test
    public void findById() throws Exception {
        BakLog init = init();
        BakLog byId = bakLogDao.findById(init.getId());
        Assert.assertTrue(byId != null);
    }

}