package com.zjj.jvm.method;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public class MethodHandlerTest {
    class GrandFather {
        void hello(String input) {
            System.out.println("GrandFather" + input);
        }
    }

    class Father extends GrandFather {
        @Override
        void hello(String input) {
            System.out.println("Father" + input);
        }
    }

    class Son extends Father {
        @Override
        void hello(String input) {
            MethodType methodType = MethodType.methodType(void.class, String.class);
            try {
                Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                field.setAccessible(true);
                MethodHandle methodHandle = ((MethodHandles.Lookup) field.get(null)).findSpecial(GrandFather.class, "hello", methodType, GrandFather.class);
                methodHandle.invoke(this, input);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MethodHandlerTest handlerTest = new MethodHandlerTest();
        Son son = handlerTest.new Son();
        son.hello("world");
    }
}
