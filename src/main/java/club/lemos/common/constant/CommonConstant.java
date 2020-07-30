package club.lemos.common.constant;


/**
 * 通用常量
 */
public interface CommonConstant {

    /**
     * 状态相关
     */
    int DB_NOT_DELETED = 0;
    int DB_IS_DELETED = 1;
    int DB_ADMIN_NON_LOCKED = 0;
    int DB_ADMIN_LOCKED = 1;
    int DB_DISABlED = 0;
    int DB_ENABlED = 1;

    /**
     * 开发环境相关
     */
    String DEV_CODE = "dev";
    String HOME_CODE = "home";
    String PROD_CODE = "prod";
    String TEST_CODE = "test";

    /**
     * api 相关
     */
    String DEFAULT_NULL_MESSAGE = "暂无承载数据";
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    String DEFAULT_FAILURE_MESSAGE = "操作失败";
    String DEFAULT_UNAUTHORIZED_MESSAGE = "签名认证失败";

    /**
     * 时间相关
     */
    String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    String PATTERN_DATE = "yyyy-MM-dd";
    String PATTERN_TIME = "HH:mm:ss";

    /**
     * 数据库相关
     */
    String DB_CREATE_TIME_FIELD_NAME ="createTime";
    String DB_UPDATE_TIME_FIELD_NAME ="updateTime";
    String DB_IS_DELETED_FIELD_NAME ="isDeleted";
    String DATETIME_MIN ="1000-01-01T00:00:00";
    String DATETIME_MAX ="9999-12-31T23:59:59";

    String DB_CREATE_USER_COLUMN_NAME ="create_user";
    String DB_CREATE_TIME_COLUMN_NAME ="create_time";
    String DB_UPDATE_USER_COLUMN_NAME ="update_user";
    String DB_UPDATE_TIME_COLUMN_NAME ="update_time";
    String DB_ENABLED_COLUMN_NAME ="enabled";

    String DB_TENANT_ID = "tenant_id";
}
