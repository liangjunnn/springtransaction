package com.lj.springtransaction.common;

/**
 * @version : V1.0
 * @ClassName: PageRequest
 * @Description:
 * @author:
 * @date: 2020/3/22 13:15
 */
public class PageRequest<T> {
    /**
     * 默认页面
     */
    private final static Integer DEFAULT_PAGE_NO = 1;

    /**
     * 默认页大小
     */
    private final static Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 每页数据量
     */
    private Integer pageSize;
    /**
     * 具体请求对象
     */
    private T data;

    /**
     * 计算偏移量
     *
     * @return 偏移量
     */
    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 计算limit大小
     *
     * @return limit大小
     */
    public int getLimit() {
        return pageSize;
    }

    // get&set
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
