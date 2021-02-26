package com.zjj.dubbo.common.timer;

public interface TimerTask {
    void run(Timeout timeout) throws Exception;
}
