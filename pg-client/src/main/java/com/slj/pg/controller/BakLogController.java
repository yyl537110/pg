package com.slj.pg.controller;

import com.slj.pg.bean.BakLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.BakLogService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@RestController
@RequestMapping("/baks")
@Api(value = "数据备份管理")
public class BakLogController{

    @Autowired
    private BakLogService bakLogService;

    @PostMapping
    @ApiOperation(value = "一键备份数据", notes = "一键备份数据", response = HttpMessage.class)
    public HttpMessage<Integer> bakup() {
        BakLog bak = bakLogService.createBak();
        if(bak.getId() != null) {
            return new HttpMessage<>(bak.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @PostMapping("/recover/{id}")
    @ApiOperation(value = "一键备份数据", notes = "一键备份数据", response = HttpMessage.class)
    public HttpMessage<Boolean> recover(Integer id) {
        if(bakLogService.recover(id)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "数据备份记录删除", notes = "数据备份记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        BakLog updateObj = new BakLog();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(bakLogService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @PostMapping("/list")
    @ApiOperation(value = "数据备份记录查询-列表", notes = "数据备份记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<BakLog>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(bakLogService.page(searchCondition),CustomStatus.SUCCESS);
    }
}
