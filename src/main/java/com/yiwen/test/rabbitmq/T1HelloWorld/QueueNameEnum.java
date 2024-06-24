package com.yiwen.test.rabbitmq.T1HelloWorld;

import lombok.Getter;

@Getter
public enum QueueNameEnum {

    HELLO("hello");
    private final String name;
    QueueNameEnum(String name) {
        this.name = name;
    }
}
