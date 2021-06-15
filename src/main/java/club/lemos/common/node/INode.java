package club.lemos.common.node;

import java.util.List;


public interface INode {

	/**
	 * 主键
	 *
	 * @return Long
	 */
	Long getId();

	/**
	 * 父主键
	 *
	 * @return Long
	 */
	Long getParentId();

	/**
	 * 子孙节点
	 *
	 * @return List
	 */
	List<INode> getChildren();

}
