package club.lemos.common.mp.handler;


import club.lemos.common.constant.CommonConstant;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

@Slf4j
public class BaseMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("start insert fill ....");
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, CommonConstant.DB_CREATE_TIME_FIELD_NAME, LocalDateTime.class, now);
        this.strictInsertFill(metaObject, CommonConstant.DB_UPDATE_TIME_FIELD_NAME, LocalDateTime.class, now);
        this.strictInsertFill(metaObject, CommonConstant.DB_ENABLED_FIELD_NAME, Integer.class, 1);
        this.strictInsertFill(metaObject, CommonConstant.DB_ENABLED_FIELD_NAME, Boolean.class, true);
        this.strictInsertFill(metaObject, CommonConstant.DB_IS_DELETED_FIELD_NAME, Integer.class, CommonConstant.DB_NOT_DELETED);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        this.strictUpdateFill(metaObject, CommonConstant.DB_UPDATE_TIME_FIELD_NAME, LocalDateTime.class, LocalDateTime.now());
    }

}
