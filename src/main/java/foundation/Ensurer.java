package foundation;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by lukas on 10/3/16.
 */
public final class Ensurer {

    public static String IsNotBlank(String object, String name )
    {
        if(StringUtils.isBlank(object)){
            throw new IllegalArgumentException(String.format("%n is blank", name));
        }
        return object;
    }

    public static <T> T IsNotNull(T object, String name) {
        if(object==null){
            throw new IllegalArgumentException(String.format("%n is null", name ));
        }
        return object;
    }
}
