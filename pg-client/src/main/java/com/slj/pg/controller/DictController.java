package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.Dict;
import com.slj.pg.bean.HelpCadre;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.DictService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yaoyl
 * on 2018/12/31.
 */
@RestController
@RequestMapping("/dicts")
@Api(value = "数据字典管理管理")
public class DictController implements BaseController<Dict> {

    @Autowired
    private DictService dictService;

    @Override
    @PostMapping
    @ApiOperation(value = "数据字典记录新增", notes = "数据字典记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid Dict dict) {
        if(dictService.insert(dict)) {
            return new HttpMessage<>(dict.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "数据字典记录编辑", notes = "数据字典记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid Dict dict) {
        Preconditions.checkState(dict.getId() != null,CustomStatus.PARAM_MISS);
        if(dictService.update(dict)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "数据字典记录删除", notes = "数据字典记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        if(dictService.deleteById(id)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "数据字典记录查询-列表", notes = "数据字典记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<Dict>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(dictService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询数据字典记录详情", notes = "根据Id查询数据字典记录详情", response = HttpMessage.class)
    public HttpMessage<Dict> detail(Integer id) {
        return new HttpMessage<>(dictService.findById(id),CustomStatus.SUCCESS);
    }
}
