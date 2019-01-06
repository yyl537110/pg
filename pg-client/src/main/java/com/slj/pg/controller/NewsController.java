package com.slj.pg.controller;

import com.google.common.base.Preconditions;
import com.slj.pg.bean.News;
import com.slj.pg.bean.page.PageDataInfo;
import com.slj.pg.bean.page.SearchCondition;
import com.slj.pg.http.CustomStatus;
import com.slj.pg.http.HttpMessage;
import com.slj.pg.service.interfaces.NewsService;
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
@RequestMapping("/news")
@Api(value = "党建风采管理")
public class NewsController implements BaseController<News> {

    @Autowired
    private NewsService newsService;

    @Override
    @PostMapping
    @ApiOperation(value = "党建风采记录新增", notes = "党建风采记录新增", response = HttpMessage.class)
    public HttpMessage<Integer> add(@RequestBody @Valid News news) {
        news.setStatus(ParamConstants.STATUS_NORMAL);
        if(newsService.insert(news)) {
            return new HttpMessage<>(news.getId(), CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(CustomStatus.INNER_ERROR);
    }

    @Override
    @PutMapping
    @ApiOperation(value = "党建风采记录编辑", notes = "党建风采记录编辑", response = HttpMessage.class)
    public HttpMessage<Boolean> update(@RequestBody @Valid News news) {
        Preconditions.checkState(news.getId() != null,CustomStatus.PARAM_MISS);
        if(newsService.update(news)) {
            return new HttpMessage<>(true, CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "党建风采记录删除", notes = "党建风采记录删除", response = HttpMessage.class)
    public HttpMessage<Boolean> delete(@PathVariable(value = "id") Integer id) {
        News updateObj = new News();
        updateObj.setId(id);
        updateObj.setStatus(ParamConstants.STATUS_DELETE);
        if(newsService.update(updateObj)) {
            return new HttpMessage<>(true,CustomStatus.SUCCESS);
        }
        return new HttpMessage<>(false,CustomStatus.INNER_ERROR);
    }

    @Override
    @PostMapping("/list")
    @ApiOperation(value = "党建风采记录查询-列表", notes = "党建风采记录查询-列表", response = HttpMessage.class)
    public HttpMessage<PageDataInfo<News>> list(@RequestBody @Valid SearchCondition searchCondition) {
        return new HttpMessage<>(newsService.page(searchCondition),CustomStatus.SUCCESS);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询党建风采记录详情", notes = "根据Id查询党建风采记录详情", response = HttpMessage.class)
    public HttpMessage<News> detail(@PathVariable(value = "id")Integer id) {
        return new HttpMessage<>(newsService.findById(id),CustomStatus.SUCCESS);
    }
}
