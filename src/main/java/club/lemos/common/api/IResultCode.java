package club.lemos.common.api;

import java.io.Serializable;

/**
 * 业务代码接口
 */
public interface IResultCode extends Serializable {

	/**
	 * 消息
	 *
	 * @return String
	 */
	String getMsg();

	/**
	 * 状态码
	 *
	 * @return int
	 */
	int getCode();

}
