package club.lemos.common.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点基类
 */
@Data
public class BaseNode implements INode {

	/**
	 * 主键ID
	 */
	protected Long id;

	/**
	 * 父节点ID
	 */
	protected Long parentId;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<INode> children = new ArrayList<>();

}
