package com.example.mybatis.demo.mapper;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    int updateCustom(T record);
}
