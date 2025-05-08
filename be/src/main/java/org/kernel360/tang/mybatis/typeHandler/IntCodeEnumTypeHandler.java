package org.kernel360.tang.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.kernel360.tang.common.IntCodeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({IntCodeEnum.class})
public class IntCodeEnumTypeHandler<E extends Enum<E> & IntCodeEnum> extends BaseTypeHandler<IntCodeEnum> {
    private final Class<E> enumClass;
    private final E[] enumConstants;

    public IntCodeEnumTypeHandler(Class<E> enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("Enum class cannot be null");
        }

        this.enumClass = enumClass;
        this.enumConstants = this.enumClass.getEnumConstants();
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IntCodeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public IntCodeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return getIntCodeEnum(rs.getInt(columnName));
    }

    @Override
    public IntCodeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs.wasNull()) {
            return null;
        }
        return getIntCodeEnum(rs.getInt(columnIndex));
    }

    @Override
    public IntCodeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs.wasNull()) {
            return null;
        }
        return getIntCodeEnum(cs.getInt(columnIndex));
    }

    private IntCodeEnum getIntCodeEnum(int code) {
        for (E enumConstant : enumConstants) {
            if (enumConstant.getCode() == code) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code + " for enum class: " + enumClass.getName());
    }
}
