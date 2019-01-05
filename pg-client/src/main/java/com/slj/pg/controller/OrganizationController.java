package com.slj.pg.controller;

import com.slj.pg.bean.Organization;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
@RestController
@RequestMapping("/orgs")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/tree/{id}")
    public HttpMessage<Organization> tree(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(organizationService.tree(id), CustomStatus.SUCCESS);
    }

    @GetMapping("/{id}")
    public HttpMessage<Organization> detail(@PathVariable(value = "id") Integer id) {
        return new HttpMessage<>(organizationService.findById(id), CustomStatus.SUCCESS);
    }
}
