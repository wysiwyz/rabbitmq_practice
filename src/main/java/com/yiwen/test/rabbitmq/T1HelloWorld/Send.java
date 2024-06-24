package com.yiwen.test.rabbitmq.T1HelloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import static com.yiwen.test.rabbitmq.T1HelloWorld.QueueNameEnum.HELLO;

/**
 * Implementation of RabbitMQ with Hello world
 */
@Slf4j
public class Send {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        // 把一個 RabbitMQ 節點連接到 localmachine, 如果要連到不同machine就指定它的hostname或IP位址
        factory.setHost("localhost");

        // 因為 connection & channel 都實作 AutoCloseable, 可以用 try-with-resource
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // queue的宣告是冪等(idempotent)的, 如果已經存在就不會再建立
            channel.queueDeclare(HELLO.getName(), false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", HELLO.getName(), null, message.getBytes());
            log.info("[X] Sent '{}'", message);
        }
    }
}
