package club.lemos.common.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Api错误码
 * <p>
 * 统一格式：A-BB-CCC
 * A:错误级别，如1代表系统级错误，2代表服务级错误；
 * B:项目或模块名称
 * C:具体错误编号
 */
@Getter
@Setter
public class ApiCode implements IResultCode {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private String errorDescription;

    public ApiCode() {
    }

    public ApiCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiCode(int code, String msg, String errorDescription) {
        this.code = code;
        this.msg = msg;
        this.errorDescription = errorDescription;
    }

    public static final ApiCode SERVER_ERROR = new ApiCode(100001, "服务内部错误");
    public static final ApiCode SERVER_BUSY = new ApiCode(100002, "服务器繁忙，请稍后再试");
    public static final ApiCode OPT_DISABLED = new ApiCode(100004, "操作被禁用");

    public static final ApiCode PARAM_VALID_ERROR = new ApiCode(100011, "参数校验错误");
    public static final ApiCode PARAM_MISS = new ApiCode(100012, "缺少参数");
    public static final ApiCode PARAM_TYPE_ERROR = new ApiCode(100013, "参数类型不匹配");
    public static final ApiCode MYBATIS_ERROR = new ApiCode(100021, "Mybatis错误");
    public static final ApiCode LOGGING_REPORT_ERROR = new ApiCode(100031, "日志上报错误");
}
