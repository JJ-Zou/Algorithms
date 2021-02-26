package com.zjj.dubbo.config;

import com.zjj.dubbo.config.annotation.Argument;
import com.zjj.dubbo.config.support.Parameter;

import java.io.Serializable;

public class ArgumentConfig implements Serializable {
    private static final long serialVersionUID = 4027811883913641895L;
    private Integer index = -1;
    private String type;
    private Boolean callback;

    public ArgumentConfig() {
    }

    public ArgumentConfig(Argument argument) {
        this.index = argument.index();
        this.type = argument.type();
        this.callback = argument.callback();
    }

    @Parameter(excluded = true)
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Parameter(excluded = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCallback(Boolean callback) {
        this.callback = callback;
    }

    public Boolean isCallback() {
        return callback;
    }
}
