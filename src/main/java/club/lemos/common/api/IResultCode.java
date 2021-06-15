package club.lemos.common.api;

import java.io.Serializable;

/**
 * 业务代码接口
 */
public interface IResultCode extends Serializable {

    /**
     * 状态码
     *
     * @return int
     */
    int getCode();

    /**
     * 消息
     *
     * @return String
     */
    String getMsg();

    /**
     * 消息描述
     */
    default String getErrorDescription() {
        return null;
    }
}
