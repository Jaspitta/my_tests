package com.example.demo.utils;

import java.util.Collections;
import java.util.List;

public abstract class ListUtils {

    public static <V> List<V> removeNullElements(List<V> list){
        if(list != null && !list.isEmpty())
            list.removeAll(Collections.singleton(null));

        return list;
    }
}
