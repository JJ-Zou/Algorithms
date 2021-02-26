package com.zjj.spring.ioc.bean;

import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.stream.Stream;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Autowired {

}

public class Reflection {

    public static void main(String[] args) {
        ClassB classB = new ClassB();
        System.out.println(classB.getClassA());
        Class<? extends ClassB> bClass = classB.getClass();
        Stream.of(bClass.getDeclaredFields()).forEach(field -> {
            if (field.getAnnotation(Autowired.class) != null) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object objectA = null;
                try {
                    objectA = type.newInstance();
                    field.set(classB, objectA);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(classB.getClassA());
        classB.print();
    }
}

class ClassA {
    public void print() {
        System.out.println("Hello world!");
    }
}

@Getter
class ClassB {
    @Autowired
    private ClassA classA;

    public void print() {
        classA.print();
    }
}

