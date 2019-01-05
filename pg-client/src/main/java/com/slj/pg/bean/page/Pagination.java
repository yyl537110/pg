package com.slj.pg.bean.page;

import javax.validation.constraints.Min;

/**
 * Created by yaoyl
 * on 2018/12/24.
 */
public class Pagination {
    /**
     * properties
     */
    @Min(0)
    private int pageSize;
    @Min(0)
    private int currentPage;
    private int pageCount;
    private String order;
    private OrderType orderType;

    /**
     * pageSize getter
     *
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * pageSize setter
     *
     * @param pageSize pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * currentPage getter
     *
     * @return currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * currentPage setter
     *
     * @param currentPage currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * pageCount getter
     *
     * @return pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * pageCount setter
     *
     * @param pageCount pageCount
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * order getter
     *
     * @return order
     */
    public String getOrder() {
        return order;
    }

    /**
     * orderType getter
     *
     * @return orderType
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * orderType setter
     *
     * @param orderType orderType
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    /**
     * order setter
     *
     * @param order order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    public enum OrderType {
        ACCEPTDATE("acceptDate");
        private String value;

        private OrderType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
