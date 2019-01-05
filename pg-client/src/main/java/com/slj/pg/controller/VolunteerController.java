package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.Meeting;
import com.slj.pg.bean.Volunteer;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.VolunteerService;
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
@RequestMapping("/volunteers")
@Api(value = "志愿者管理")
public class VolunteerController implements BaseController<Volunteer> {

    @Autowired
    private VolunteerService volunteerService;

    @Override
    @PostMapping
    @ApiOperation(value = "志愿者记录新增", notes = "志愿者记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid Volunteer volunteer) {
        volunteer.setStatus(ParamConstants.STATUS_NORMAL);
        if(volunteerService.insert(volunteer)) {
            return new HttpMessage<>(volunteer.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "志愿者记录编辑", notes = "志愿者记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid Volunteer volunteer) {
        Preconditions.checkState(volunteer.getId() != null,CustomStatus.PARAM_MISS);
        if(volunteerService.update(volunteer)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "志愿者记录删除", notes = "志愿者记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        Volunteer updateObj = new Volunteer();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(volunteerService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "志愿者记录查询-列表", notes = "志愿者记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<Volunteer>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(volunteerService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询志愿者记录详情", notes = "根据Id查询志愿者记录详情", response = HttpMessage.class)
    public HttpMessage<Volunteer> detail(Integer id) {
        return new HttpMessage<>(volunteerService.findById(id),CustomStatus.SUCCESS);
    }
}
