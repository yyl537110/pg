package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.HelpPoor;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.HelpPoorService;
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
@RequestMapping("/helppoors")
@Api(value = "扶贫对象管理")
public class HelpPoorController implements BaseController<HelpPoor> {

    @Autowired
    private HelpPoorService helpPoorService;

    @Override
    @PostMapping
    @ApiOperation(value = "扶贫对象记录新增", notes = "扶贫对象记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid HelpPoor helpPoor) {
        helpPoor.setStatus(ParamConstants.STATUS_NORMAL);
        if(helpPoorService.insert(helpPoor)) {
            return new HttpMessage<>(helpPoor.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "扶贫对象记录编辑", notes = "扶贫对象记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid HelpPoor helpPoor) {
        Preconditions.checkState(helpPoor.getId() != null,CustomStatus.PARAM_MISS);
        if(helpPoorService.update(helpPoor)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "扶贫对象记录删除", notes = "扶贫对象记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable(value = "id") Integer id) {
        HelpPoor updateObj = new HelpPoor();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(helpPoorService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "扶贫对象记录查询-列表", notes = "扶贫对象记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<HelpPoor>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(helpPoorService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询扶贫对象记录详情", notes = "根据Id查询扶贫对象记录详情", response = HttpMessage.class)
    public HttpMessage<HelpPoor> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(helpPoorService.findById(id),CustomStatus.SUCCESS);
    }
}
