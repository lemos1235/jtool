package club.lemos.common.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BeanProperty {
    private final String name;
    private final Class<?> type;
}
