package com.zjj.dubbo.common.cimpiler;

public interface Compiler {
    Class<?> compile(String code, ClassLoader classLoader);

}
