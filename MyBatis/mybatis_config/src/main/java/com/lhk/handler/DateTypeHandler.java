package com.lhk.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 自定义的类型转换器
 * @author TheMutents
 * @creat on 2021-12-26-23:50
 */
public class DateTypeHandler extends BaseTypeHandler<Date> {

    /**
     * 将java类型转换为数据库类型
     * @param preparedStatement
     * @param i
     * @param date
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        //得到时间的毫秒数
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }


    /**
     * 将数据库的某些类型转为就Java的类型
     * @param resultSet 在数据库中查询出的结果集
     * @param s 数据库中需要类型转换的字段名称
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
       //将结果集中需要转换类型的字段类型（BigInt）转为Date类型
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库的某些类型转为就Java的类型
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库的某些类型转为就Java的类型
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
