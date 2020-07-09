package com.example.mybatis.demo.extend;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Objects;
import java.util.concurrent.locks.LockSupport;

import static java.util.stream.Collectors.joining;

public class updateCustomMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        ExtendSql sqlMethod = ExtendSql.UPDATE_CUSTOM;
        String updateScript = buildUpdateScript(tableInfo);
        String whereScript = buildWhereScript(tableInfo);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), updateScript, whereScript);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }

    protected String buildWhereScript(TableInfo tableInfo) {
        String column = tableInfo.getKeyColumn();
        return WHERE + SqlScriptUtils.convertIf(String.format("%s = #{%s}", column, column), String.format("%s != null", column), false);
    }

    private String buildUpdateScript(TableInfo tableInfo) {
        String keyColumn = tableInfo.getKeyColumn();

        return tableInfo.getFieldList().stream().map(fieldInfo -> {
            String column = fieldInfo.getColumn();
            String property = fieldInfo.getProperty();
            FieldStrategy strategy = fieldInfo.getUpdateStrategy();
            if (column.equals(keyColumn) || strategy == FieldStrategy.NEVER) {
                // 过滤掉primary key和不能更新的字段
                return null;
            }

            final String sqlScript = String.format("%s= #{%s}", column, column);

            return SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", property), false);
        }).filter(Objects::nonNull).collect(joining(COMMA));
    }

}
