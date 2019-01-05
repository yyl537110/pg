package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.HelpCadre;
import com.slj.pg.bean.HelpPoor;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.HelpCadreService;
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
@RequestMapping("/helpcadres")
@Api(value = "扶贫干部管理")
public class HelpCadreController implements BaseController<HelpCadre> {

    @Autowired
    private HelpCadreService helpCadreService;

    @Override
    @PostMapping
    @ApiOperation(value = "扶贫干部记录新增", notes = "扶贫干部记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid HelpCadre helpCadre) {
        helpCadre.setStatus(ParamConstants.STATUS_NORMAL);
        if(helpCadreService.insert(helpCadre)) {
            return new HttpMessage<>(helpCadre.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "扶贫干部记录编辑", notes = "扶贫干部记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid HelpCadre helpCadre) {
        Preconditions.checkState(helpCadre.getId() != null,CustomStatus.PARAM_MISS);
        if(helpCadreService.update(helpCadre)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "扶贫干部记录删除", notes = "扶贫干部记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(Integer id) {
        HelpCadre updateObj = new HelpCadre();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(helpCadreService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "扶贫干部记录查询-列表", notes = "扶贫干部记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<HelpCadre>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(helpCadreService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询扶贫干部记录详情", notes = "根据Id查询扶贫干部记录详情", response = HttpMessage.class)
    public HttpMessage<HelpCadre> detail(Integer id) {
        return new HttpMessage<>(helpCadreService.findById(id),CustomStatus.SUCCESS);
    }
}
