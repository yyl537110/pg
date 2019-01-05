package com.slj.pg.bean.page;

import java.util.List;

/**
 * Created by yaoyl on 2018/12/24.
 */
public class PageDataInfo<T> {
    private List<T> content;
    private long totalCount;
    private int totalPage;
    private int currentPage;
    private int pageSize;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param content    结果内容
     * @param totalCount 结果总数
     * @param totalPage  结果总页数
     */
    public PageDataInfo(List<T> content, long totalCount, int totalPage, int currentPage, int pageSize) {
        this.content = content;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }


}
