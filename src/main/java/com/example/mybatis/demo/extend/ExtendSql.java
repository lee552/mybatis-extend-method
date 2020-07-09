package com.example.mybatis.demo.extend;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ExtendSql {

    UPDATE_CUSTOM("updateCustom", "自定义更新", "<script>\nUPDATE %s set %s %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;
}
