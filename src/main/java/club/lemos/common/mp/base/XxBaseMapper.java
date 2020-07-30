package club.lemos.common.mp.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface XxBaseMapper<T> extends BaseMapper<T> {

    default QueryChainWrapper<T> queryChain() {
        return new QueryChainWrapper<>(this);
    }

    default LambdaQueryChainWrapper<T> lambdaQueryChain() {
        return new LambdaQueryChainWrapper<>(this);
    }

    default UpdateChainWrapper<T> updateChain() {
        return new UpdateChainWrapper<>(this);
    }

    default LambdaUpdateChainWrapper<T> lambdaUpdateChain() {
        return new LambdaUpdateChainWrapper<>(this);
    }

    int deleteByIdWithFill(T entity);

    T findOne(Serializable id);

    int updateFullById(@Param(Constants.ENTITY) T entity);
}

