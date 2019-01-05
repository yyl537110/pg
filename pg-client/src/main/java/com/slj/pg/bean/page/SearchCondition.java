package com.slj.pg.bean.page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
public class SearchCondition {

    /**
     * properties
     */
    @NotNull
    @Valid
    private Pagination pagination;
    @NotNull
    private Map<String, Object> searchContent;

    /**
     * pagination getter
     *
     * @return pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * pagination setter
     *
     * @param pagination pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Map<String, Object> getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(Map<String, Object> searchContent) {
        this.searchContent = searchContent;
    }
}
