package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.OrgActivity;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.OrgActivityService;
import com.slj.pg.util.ParamConstants;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yaoyl
 * on 2018/12/28.
 */
@RestController
@RequestMapping("/activities")
@Api(value = "组织活动管理")
public class OrgActivityController implements BaseController<OrgActivity> {

    @Autowired
    private OrgActivityService orgActivityService;

    @Override
    @PostMapping
    @ApiOperation(value = "组织活动记录新增", notes = "组织活动记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid OrgActivity orgActivity) {
        orgActivity.setStatus(ParamConstants.STATUS_NORMAL);
        if(orgActivityService.insert(orgActivity)) {
            return new HttpMessage<>(orgActivity.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "组织活动记录编辑", notes = "组织活动记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid OrgActivity orgActivity) {
        Preconditions.checkState(orgActivity.getId() != null,CustomStatus.PARAM_MISS);
        if(orgActivityService.update(orgActivity)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "组织活动记录删除", notes = "组织活动记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        OrgActivity updateObj = new OrgActivity();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(orgActivityService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "组织活动记录查询-列表", notes = "组织活动记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<OrgActivity>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(orgActivityService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询组织活动记录详情", notes = "根据Id查询组织活动记录详情", response = HttpMessage.class)
    public HttpMessage<OrgActivity> detail(Integer id) {
        return new HttpMessage<>(orgActivityService.findById(id),CustomStatus.SUCCESS);
    }
}
