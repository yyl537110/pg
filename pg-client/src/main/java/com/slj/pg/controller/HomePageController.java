package com.slj.pg.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.slj.pg.bean.Organization;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.OrganizationService;
import com.slj.pg.util.ParamConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2019/1/5.
 */
@RestController
@RequestMapping("/homes")
@Api(value = "首页信息管理")
public class HomePageController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/map/{orgId}")
    @ApiOperation(value = "首页信息-地图信息", notes = "首页信息-地图信息", response = HttpMessage.class)
    public HttpMessage<List<Map<String,Object>>> map(@PathVariable(value = "orgId") Integer orgId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("status", ParamConstants.STATUS_NORMAL);
        params.put("parentId",orgId);
        List<Organization> organizations = organizationService.findAll(params);

        List<Map<String,Object>> maps = Lists.newArrayList();
        Map<String,Object> info;
        for (Organization organization: organizations) {
            info = Maps.newHashMap();
            info.put("orgId", organization.getId());
            info.put("pos",organization.getName() + "("+organization.getSubNum() + "个)");
            info.put("width",organization.getWidth());
            info.put("height",organization.getHeight());
            info.put("iconx",organization.getIconx());
            info.put("icony",organization.getIcony());
            info.put("path",organization.getPath());
            info.put("left",organization.getLeft());
            info.put("top",organization.getTop());
            maps.add(info);
        }
        return new HttpMessage<>(maps, CustomStatus.SUCCESS);
    }

}
