package com.zjj.jvm.garbage;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * finalize只会执行一次
 * 可达性分析进行第一次筛选，有必要执行finalize方法的对象会被放入F-Queue队列等待执行
 * 此时，可以在finalize方法中复活对象
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    @SneakyThrows
    public static void main(String[] args) {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK.alive();

        SAVE_HOOK = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.alive();
        } else {
            System.out.println("dead");
        }

        SAVE_HOOK = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.alive();
        } else {
            System.out.println("dead");
        }

    }

    public void alive() {
        System.out.println(this + " is alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }
}
