package com.xiaojiezhu.jrc.web.server.support.model;

import java.util.List;

/**
 * @author xiaojie.zhu
 */
public class LimitResult {

    private long count;

    private List<?> data;


    public LimitResult() {
    }

    public LimitResult(long count, List<?> data) {
        this.count = count;
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LimitResult{" +
                "count=" + count +
                ", data=" + data +
                '}';
    }
}
