package com.xiaojiezhu.jrc.common.cache.counter;

/**
 * @author zxj<br>
 * 时间 2017/11/29 15:59
 * 说明 计数器的生成工厂
 */
public interface CounterFactory {

    /**
     * 获取一个计数器
     * @param name 计数器名称，不同的计数器名各自独立
     * @return
     */
    Counter getCounter(String name);

}
