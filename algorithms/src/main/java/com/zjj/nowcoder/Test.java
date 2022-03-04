package com.zjj.nowcoder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public void func(String key, String[] args) {
        System.out.println((args == null) ? "null" : args.length);
    }

    public static void main(String[] args) throws Exception {
//        Test obj = new Test();
//        Method m = obj.getClass().getMethod("func", String.class, String[].class);
////        m.invoke(obj, new String(), new String[1]);
////        m.invoke(obj, new String(), new Object[]{new String[]{"a", "b"}});
////        m.invoke(obj, new String(), (Object) new String[]{"a", "b"});
//        m.invoke(obj, new String(), new String[]{"a"});
//        m.invoke(obj, new String(), new String[]{"a", "b"});
//        m.invoke(obj, new String(), new String[2]);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("1".equals(item)) {
                list.remove(item);
            }
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }
}
