package com.slj.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.slj.pg.bean.BakLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.Pagination;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.dao.interfaces.BakLogDao;
import com.slj.pg.service.interfaces.BakLogService;
import com.slj.pg.util.DataUtils;
import com.slj.pg.util.DateUtils;
import com.slj.pg.util.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@Service
public class BakLogServiceImpl implements BakLogService {
    @Autowired
    private BakLogDao bakLogDao;

    @Autowired
    private DataUtils dataUtils;

    @Value("${mysql.bakHome}")
    private String bakHome;

    @Override
    public boolean insert(BakLog bakLog) {
        return bakLogDao.insert(bakLog);
    }

    @Override
    public boolean update(BakLog bakLog) {
        return bakLogDao.update(bakLog);
    }

    @Override
    public boolean deleteById(Integer id) {
        return bakLogDao.deleteById(id);
    }

    @Override
    public List<BakLog> findAll(Map<String, Object> map) {
        return bakLogDao.findAll(map);
    }

    @Override
    public BakLog findById(Integer id) {
        return bakLogDao.findById(id);
    }

    @Override
    public PageDataInfo<BakLog> page(SearchCondition searchCondition) {
        Pagination pagination = searchCondition.getPagination();
        Page<BakLog> pageInfo;
        if(pagination.getCurrentPage() != 0) {
            pageInfo = PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        }else {
            pageInfo = new Page<>(0,0);
        }
        Map<String,Object> params = searchCondition.getSearchContent();
        List<BakLog> data = bakLogDao.findAll(params);
        return new PageDataInfo<>(data,pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    @Override
    public BakLog createBak() {
        String bakFilePath = getBakFilePath();
        boolean bakupResult = dataUtils.bakup(bakFilePath);
        if(!bakupResult) {
            return new BakLog();
        }
        BakLog bakLog = new BakLog();
        bakLog.setBakFile(bakFilePath);
        bakLog.setStatus(ParamConstants.STATUS_NORMAL);
        boolean insertResult = bakLogDao.insert(bakLog);
        if(!insertResult) {
            return new BakLog();
        }
        return bakLog;
    }

    private String getBakFilePath() {
        File bakDir = new File(bakHome);
        // 如果目录不存在
        if (!bakDir.exists()) {
            bakDir.mkdirs();
        }
        if(!bakHome.endsWith(File.separator)){
            bakHome = bakHome + File.separator;
        }
        return bakHome + DateUtils.formatLocalDate(LocalDate.now())+"-"+UUID.randomUUID().toString();
    }

    @Override
    public boolean recover(Integer id) {
        BakLog bakLog = bakLogDao.findById(id);
        if(bakLog.getId() == null) {
            return false;
        }
        boolean recover = dataUtils.recover(bakLog.getBakFile());
        if(!recover) {
            return false;
        }
        BakLog update = new BakLog();
        update.setId(id);
        return bakLogDao.update(update);
    }
}
