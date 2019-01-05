package com.slj.pg.service.interfaces;

import com.slj.pg.bean.Organization;

import java.util.List;

/**
 * Created by yaoyl on 2018/12/24.
 */
public interface OrganizationService extends BaseService<Organization> {
    /**
     * 根据传入节点Id获取组织机构树状结构，包含自身节点
     * @param id 顶层节点Id
     * @return
     */
    Organization tree(Integer id);
}
