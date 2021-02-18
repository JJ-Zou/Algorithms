package com.zjj.dubbo.rpc.common.timer;

public interface TimerTask {
    void run(Timeout timeout) throws Exception;
}
