package club.lemos.common.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5885155226898287919L;

    /**
     * 错误码
     */
    private int code = 400;

    private String msg = "";

    private String errorDescription;

    /**
     * @deprecated 请使用 ApiException(IResultCode resultCode)
     *
     * @param msg 错误信息
     */
    @Deprecated
    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ApiException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.errorDescription = resultCode.getErrorDescription();
    }

    public ApiException(R<?> result) {
        super(result.getMsg());
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.errorDescription = result.getErrorDescription();
    }
}

