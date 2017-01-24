package viennaruns.foundation;

import org.apache.commons.lang3.StringUtils;

public final class Ensurer {

    public static String IsNotBlank(String object, String name) {
        if (StringUtils.isBlank(object)) throw new IllegalArgumentException(String.format("%s is blank", name));
        return object;
    }

    public static <T> T IsNotNull(T object, String name) {
        if (object == null) throw new IllegalArgumentException(String.format("%s is null", name));
        return object;
    }
}
