package com.slj.pg.service.impl;

import com.alibaba.fastjson.JSON;
import com.slj.pg.PgClientApplication;
import com.slj.pg.bean.Organization;
import com.slj.pg.controller.OrganizationController;
import com.slj.pg.service.interfaces.OrganizationService;
import com.slj.pg.util.DataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yaoyl on 2018/12/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PgClientApplication.class)
@Transactional
@Rollback
public class OrganizationServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImplTest.class);

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationController organizationController;

    @Autowired
    private DataUtils dataUtils;

    @Test
    public void tree() throws Exception {
        Organization top = organizationService.tree(1);
        LOGGER.info("tree:{}", JSON.toJSONString(top));
    }

    @Test
    public void recover() {
        dataUtils.recover("D:/backupDatabase/2018-12-31.sql");
    }

}