package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.PartyMember;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.PayLogService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yaoyl
 * on 2018/12/27.
 */
@RestController
@RequestMapping("/paylogs")
@Api(value = "党员缴费记录管理")
public class PayLogController implements BaseController<PayLog> {
    @Autowired
    private PayLogService payLogService;

    @Override
    @PostMapping
    @ApiOperation(value = "党员缴费记录新增", notes = "党员缴费记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid PayLog payLog) {
        payLog.setStatus(ParamConstants.STATUS_NORMAL);
        if(payLogService.insert(payLog)) {
            return new HttpMessage<>(payLog.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "党员缴费记录编辑", notes = "党员缴费记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid PayLog payLog) {
        Preconditions.checkState(payLog.getId() != null,CustomStatus.PARAM_MISS);
        if(payLogService.update(payLog)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "党员缴费记录删除", notes = "党员缴费记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable(value = "id") Integer id) {
        PayLog updateObj = new PayLog();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(payLogService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "党员缴费记录查询-列表", notes = "党员缴费记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<PayLog>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(payLogService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询缴费记录详情", notes = "根据Id查询缴费记录详情", response = HttpMessage.class)
    public HttpMessage<PayLog> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(payLogService.findById(id),CustomStatus.SUCCESS);
    }
}
