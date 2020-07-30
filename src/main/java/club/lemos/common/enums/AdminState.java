package club.lemos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminState {

    SAVED("已保存", 1),
    COMMITTED("已提交", 2),
    UN_PASS("未通过", 3),
    PASS("已通过", 4),
    ;

    private final String name;
    private final int value;
}
