package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.PayLog;
import com.slj.pg.bean.Study;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.StudyService;
import com.slj.pg.util.ParamConstants;
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
@RequestMapping("/studies")
@Api(value = "两学一做记录管理")
public class StudyController implements BaseController<Study> {

    @Autowired
    private StudyService studyService;

    @Override
    @PostMapping
    @ApiOperation(value = "两学一做记录新增", notes = "两学一做记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid Study study) {
        study.setStatus(ParamConstants.STATUS_NORMAL);
        if(studyService.insert(study)) {
            return new HttpMessage<>(study.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "两学一做记录编辑", notes = "两学一做记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid Study study) {
        Preconditions.checkState(study.getId() != null,CustomStatus.PARAM_MISS);
        if(studyService.update(study)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "两学一做记录删除", notes = "两学一做记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        Study updateObj = new Study();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(studyService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "两学一做记录查询-列表", notes = "两学一做记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<Study>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(studyService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询两学一做记录详情", notes = "根据Id查询两学一做记录详情", response = HttpMessage.class)
    public HttpMessage<Study> detail(Integer id) {
        return new HttpMessage<>(studyService.findById(id),CustomStatus.SUCCESS);
    }
}
