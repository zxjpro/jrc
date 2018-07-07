package com.xiaojiezhu.jrc.common.cache.counter;

/**
 * @author zxj<br>
 * 时间 2017/11/29 15:55
 * 说明 计数器
 */
public interface Counter {

    /**
     * 加数
     * @param x 增加的数量
     */
    void add(long x);

    /**
     * 减数
     * @param x 减少的数量
     */
    void subtract(long x);

    /**
     * 获取当前计数器的数量
     * @return
     */
    Long get();
}
