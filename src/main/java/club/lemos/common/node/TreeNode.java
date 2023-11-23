package club.lemos.common.node;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 树型节点类
 */
@Getter
@Setter
public class TreeNode extends BaseNode implements Serializable {

    private String title;

    private Long key;

    private Long value;

    private Integer type;
}
