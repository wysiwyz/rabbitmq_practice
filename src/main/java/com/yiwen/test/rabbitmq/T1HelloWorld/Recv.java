package com.yiwen.test.rabbitmq.T1HelloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.yiwen.test.rabbitmq.T1HelloWorld.QueueNameEnum.HELLO;

@Slf4j
public class Recv {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        /**
         * 不加 try-with-resource 原因:
         * 如果 auto-close channel 跟 connection, 會繼續往下最後 exit program
         * 但在 consumer 持續非同步監聽直到訊息抵達之前, program 都還要保持 active
         * */
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(HELLO.getName(), false, false, false, null);
        log.info(" [*] Waiting for messages. To exit, press CTRL + C");

        /**
         * 因為 message delivery 是非同步推送, 所以提供一個物件型態的 callback,
         * 會對訊息做 buffer, 直到準備好要使用 message 的時候
         * */
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info("[X] Received '{}'", message);
        };

        channel.basicConsume(HELLO.getName(), true, deliverCallback, consumerTag -> {});
    }
}
