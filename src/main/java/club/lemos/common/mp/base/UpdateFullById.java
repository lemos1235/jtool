package club.lemos.common.mp.base;

import club.lemos.common.constant.CommonConstant;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * 全量更新
 */
public class UpdateFullById extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String script = "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>";
        String method = "updateFullById";
        final String additional = optlockVersion(tableInfo);
        String sql = String.format(script, tableInfo.getTableName(),
                sqlSet(false, tableInfo, false, ENTITY, ENTITY_DOT),
                tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, method, sqlSource);
    }

    public String sqlSet(boolean ew, TableInfo table, boolean judgeAliasNull, String alias, String prefix) {
        String sqlScript = getCustomAllSqlSet(table, prefix);
        if (judgeAliasNull) {
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", alias), true);
        }
        if (ew) {
            sqlScript += NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(SqlScriptUtils.unSafeParam(U_WRAPPER_SQL_SET),
                    String.format("%s != null and %s != null", WRAPPER, U_WRAPPER_SQL_SET), false);
        }
        sqlScript = SqlScriptUtils.convertSet(sqlScript);
        return sqlScript;
    }

    /**
     * 全量更新
     * <p>
     * create_user create_time 不参与更新
     * enabled 依然默认策略增量更新
     */
    public String getCustomAllSqlSet(TableInfo table, final String prefix) {
        List<TableFieldInfo> fieldList = table.getFieldList();
        final String newPrefix = prefix == null ? EMPTY : prefix;
        return fieldList.stream()
                .filter(i -> !(i.getColumn().equals(CommonConstant.DB_CREATE_USER_COLUMN_NAME)
                        | i.getColumn().equals(CommonConstant.DB_CREATE_TIME_COLUMN_NAME)))
                .map(i -> {
                    if (i.getColumn().equals(CommonConstant.DB_ENABLED_COLUMN_NAME)) {
                        return i.getSqlSet(false, newPrefix);
                    } else {
                        return i.getSqlSet(true, newPrefix);
                    }
                }).filter(Objects::nonNull).collect(joining(NEWLINE));
    }

}
