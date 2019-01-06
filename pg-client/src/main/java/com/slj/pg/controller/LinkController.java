package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.Link;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.LinkService;
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
@RequestMapping("/links")
@Api(value = "工作链接管理")
public class LinkController implements BaseController<Link> {

    @Autowired
    private LinkService linkService;

    @Override
    @PostMapping
    @ApiOperation(value = "工作链接记录新增", notes = "工作链接记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid Link link) {
        link.setStatus(ParamConstants.STATUS_NORMAL);
        if(linkService.insert(link)) {
            return new HttpMessage<>(link.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "工作链接记录编辑", notes = "工作链接记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid Link link) {
        Preconditions.checkState(link.getId() != null,CustomStatus.PARAM_MISS);
        if(linkService.update(link)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "工作链接记录删除", notes = "工作链接记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable("id") Integer id) {
        Link updateObj = new Link();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(linkService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "工作链接记录查询-列表", notes = "工作链接记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<Link>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(linkService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询工作链接记录详情", notes = "根据Id查询工作链接记录详情", response = HttpMessage.class)
    public HttpMessage<Link> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(linkService.findById(id),CustomStatus.SUCCESS);
    }
}
