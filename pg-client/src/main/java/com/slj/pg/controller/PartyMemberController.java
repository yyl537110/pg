package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.PartyMember;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.PartyMemberService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 党员管理Controller
 * Created by yaoyl
 * on 2018/12/24.
 */
@RestController
@RequestMapping("/parties")
@Api(value = "党员信息管理")
public class PartyMemberController implements BaseController<PartyMember> {

    @Autowired
    private PartyMemberService partyMemberService;

    @PostMapping
    @Override
    @ApiOperation(value = "党员信息新增", notes = "党员信息新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid PartyMember partyMember) {
        partyMember.setStatus(ParamConstants.STATUS_NORMAL);
        if(partyMemberService.insert(partyMember)) {
            return new HttpMessage<>(partyMember.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @PutMapping
    @Override
    @ApiOperation(value = "党员信息编辑", notes = "党员信息编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid PartyMember partyMember) {
        Preconditions.checkState(partyMember.getId() != null,CustomStatus.PARAM_MISS);
        if(partyMemberService.update(partyMember)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @DeleteMapping("/{id}")
    @Override
    @ApiOperation(value = "党员信息删除", notes = "党员信息删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable(value = "id") Integer id) {
        PartyMember updateObj = new PartyMember();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(partyMemberService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @PostMapping("/list")
    @Override
    @ApiOperation(value = "党员信息列表", notes = "党员信息列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<PartyMember>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(partyMemberService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @GetMapping("/{id}")
    @Override
    @ApiOperation(value = "根据Id查询党员详情", notes = "根据Id查询党员详情", response = HttpMessage.class)
    public HttpMessage<PartyMember> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(partyMemberService.findById(id),CustomStatus.SUCCESS);
    }
}
