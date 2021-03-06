package club.lemos.common.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 树型节点类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseNode implements Serializable {

	private String title;

	private Long key;

	private Long value;

	private Integer type;
}
