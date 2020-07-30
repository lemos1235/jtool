package club.lemos.common.mp.base;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class XxServiceImpl<M extends XxBaseMapper<T>, T> extends ServiceImpl<M, T>
        implements IService<T> {

    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateFull(T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal)) ? save(entity) : updateFullById(entity);
        }
        return false;
    }

    public boolean updateFullById(T entity) {
        return retBool(baseMapper.updateFullById(entity));
    }

    public boolean deleteByIdWithFull(T entity) {
        return retBool(baseMapper.deleteByIdWithFill(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateFullBatch(Collection<T> entityList) {
        return saveOrUpdateFullBatch(entityList, 1000);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateFullBatch(Collection<T> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty");
        Class<?> cls = currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
                sqlSession.insert(sqlStatement(SqlMethod.INSERT_ONE), entity);
            } else {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put(Constants.ENTITY, entity);
                String method = "updateFullById";
                String sqlStatement = SqlHelper.table(currentModelClass()).getSqlStatement(method);
                sqlSession.update(sqlStatement, param);
            }
        } );
        return true;
    }

}
