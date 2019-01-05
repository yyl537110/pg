package com.slj.pg.controller;

import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.HttpMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
public interface BaseController<T> {

    HttpMessage<Integer> add(T t);

    HttpMessage<Boolean> update(T t);

    HttpMessage<Boolean> delete(Integer id);

    HttpMessage<PageDataInfo<T>> list(SearchCondition searchCondition);

    HttpMessage<T> detail(Integer id);
}
