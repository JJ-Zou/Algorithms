package com.zjj.dubbo.remoting;

public interface IdleSensible {
    default boolean canHandleIdle() {
        return false;
    }

}
