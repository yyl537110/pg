package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.Meeting;
import com.slj.pg.bean.OrgActivity;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.MeetingService;
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
@RequestMapping("/meetings")
@Api(value = "三会一课管理")
public class MeetingController implements BaseController<Meeting> {

    @Autowired
    private MeetingService meetingService;

    @Override
    @PostMapping
    @ApiOperation(value = "三会一课记录新增", notes = "三会一课记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid Meeting meeting) {
        meeting.setStatus(ParamConstants.STATUS_NORMAL);
        if(meetingService.insert(meeting)) {
            return new HttpMessage<>(meeting.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "三会一课记录编辑", notes = "三会一课记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid Meeting meeting) {
        Preconditions.checkState(meeting.getId() != null,CustomStatus.PARAM_MISS);
        if(meetingService.update(meeting)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "三会一课记录删除", notes = "三会一课记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable(value = "id") Integer id) {
        Meeting updateObj = new Meeting();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(meetingService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "三会一课记录查询-列表", notes = "三会一课记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<Meeting>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(meetingService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询三会一课记录详情", notes = "根据Id查询三会一课记录详情", response = HttpMessage.class)
    public HttpMessage<Meeting> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(meetingService.findById(id),CustomStatus.SUCCESS);
    }
}
