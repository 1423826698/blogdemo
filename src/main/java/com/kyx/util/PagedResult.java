package com.kyx.util;

import java.util.List;

public class PagedResult {
    //当前页面
    private int page;
    //总页数
    private Long total;
    //页面文章信息
    private List<?> content;

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    //总记录数
    private long records;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
