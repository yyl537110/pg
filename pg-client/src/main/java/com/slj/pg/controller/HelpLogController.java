package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.HelpLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.HelpLogService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yaoyl
 * on 2018/12/30.
 */
@RestController
@RequestMapping("/helplogs")
@Api(value = "帮扶管理")
public class HelpLogController implements BaseController<HelpLog> {

    @Autowired
    private HelpLogService helpLogService;

    @Override
    @PostMapping
    @ApiOperation(value = "帮扶记录新增", notes = "帮扶记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid HelpLog helpLog) {
        helpLog.setStatus(ParamConstants.STATUS_NORMAL);
        if(helpLogService.insert(helpLog)) {
            return new HttpMessage<>(helpLog.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "帮扶记录编辑", notes = "帮扶记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid HelpLog helpLog) {
        Preconditions.checkState(helpLog.getId() != null,CustomStatus.PARAM_MISS);
        if(helpLogService.update(helpLog)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "帮扶记录删除", notes = "帮扶记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        HelpLog updateObj = new HelpLog();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(helpLogService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "帮扶记录查询-列表", notes = "帮扶记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<HelpLog>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(helpLogService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询帮扶记录详情", notes = "根据Id查询帮扶记录详情", response = HttpMessage.class)
    public HttpMessage<HelpLog> detail(Integer id) {
        return new HttpMessage<>(helpLogService.findById(id),CustomStatus.SUCCESS);
    }
}
