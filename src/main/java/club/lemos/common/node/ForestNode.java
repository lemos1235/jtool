package club.lemos.common.node;

import lombok.Getter;
import lombok.Setter;


/**
 * 森林节点类
 */
@Getter
@Setter
public class ForestNode extends BaseNode {

    /**
     * 节点内容
     */
    private Object content;

    public ForestNode(Long id, Long parentId, Object content) {
        this.id = id;
        this.parentId = parentId;
        this.content = content;
    }

}
