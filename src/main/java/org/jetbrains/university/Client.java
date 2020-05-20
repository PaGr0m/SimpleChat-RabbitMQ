package org.jetbrains.university;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.jetbrains.university.util.Settings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Client {

//    public static void main(String[] args) throws IOException, TimeoutException {
//        Settings setting = new Settings(args);
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(setting.getAddress());
//
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.exchangeDeclare(setting.getChannelName(), "fanout");
//
//            channel.basicPublish(setting.getChannelName(), "", null, message.getBytes("UTF-8"));
//            System.out.println(" [x] Sent '" + message + "'");
//        }
//
//        String message = String.join(" ", args);
//
//        channel.basicPublish("", "hello", null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//
//            System.out.println(" [x] Received '" + message + "'");
//            try {
//                doWork(message);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println(" [x] Done");
//            }
//        };
//        boolean autoAck = true; // acknowledgment is covered below
//        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
//    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }

}
