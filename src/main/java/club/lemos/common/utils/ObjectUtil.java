package club.lemos.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * 对象工具类
 */
public class ObjectUtil extends org.springframework.util.ObjectUtils {

    /**
     * 判断元素不为空
     *
     * @param obj object
     * @return boolean
     */
    public static boolean isNotEmpty(@Nullable Object obj) {
        return !ObjectUtil.isEmpty(obj);
    }

    /**
     * 获取非空的属性名称
     * @param source Object
     * @return String[]
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null) {
                emptyNames.add(pd.getName());
            } else if (srcValue instanceof String) {
                if (StringUtil.isBlank((String) srcValue)) {
                    emptyNames.add(pd.getName());
                }
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static boolean isArray(Object obj) {
        return obj!=null && obj.getClass().isArray();
    }
}
