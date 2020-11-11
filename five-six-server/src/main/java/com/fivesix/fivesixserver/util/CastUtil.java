package com.fivesix.fivesixserver.util;

import java.util.List;
import java.util.stream.Collectors;

/*
 * cast object to specific class
 */
public class CastUtil {

    public static <T> List<T> objectConvertToList(Object obj, Class<T> clazz) {
        //List<T> result = new ArrayList<>();
        if(obj instanceof List<?>)
        {
            return ((List<?>) obj).stream()
                                  .map(clazz::cast)
                                  .collect(Collectors.toList());
            /*
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
            */
        }
        return null;
    }

}
