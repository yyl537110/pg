package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.VoluntaryActivity;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.VoluntaryActivityService;
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
@RequestMapping("/volunteers/activities")
@Api(value = "志愿活动管理")
public class VoluntaryActivityController implements BaseController<VoluntaryActivity> {
    @Autowired
    private VoluntaryActivityService voluntaryActivityService;

    @Override
    @PostMapping
    @ApiOperation(value = "志愿活动记录新增", notes = "志愿活动记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid VoluntaryActivity voluntaryActivity) {
        voluntaryActivity.setStatus(ParamConstants.STATUS_NORMAL);
        if(voluntaryActivityService.insert(voluntaryActivity)) {
            return new HttpMessage<>(voluntaryActivity.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "志愿活动记录编辑", notes = "志愿活动记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid VoluntaryActivity voluntaryActivity) {
        Preconditions.checkState(voluntaryActivity.getId() != null,CustomStatus.PARAM_MISS);
        if(voluntaryActivityService.update(voluntaryActivity)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "志愿活动记录删除", notes = "志愿活动记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable(value = "id") Integer id) {
        VoluntaryActivity updateObj = new VoluntaryActivity();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(voluntaryActivityService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "志愿活动记录查询-列表", notes = "志愿活动记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<VoluntaryActivity>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(voluntaryActivityService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询志愿活动记录详情", notes = "根据Id查询志愿活动记录详情", response = HttpMessage.class)
    public HttpMessage<VoluntaryActivity> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(voluntaryActivityService.findById(id),CustomStatus.SUCCESS);
    }
}
