package org.jetbrains.university.chat;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.jetbrains.university.util.Settings;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        Settings settings = new Settings(argv);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(settings.getAddress());
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(settings.getChannelName(), false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Scanner sc = new Scanner(System.in);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

        while (true) {
            System.out.println("READY");
            String msg = sc.nextLine();
            if (msg.equals("exit")) {
                break;
            }
            channel.basicPublish("", settings.getChannelName(), null, msg.getBytes(StandardCharsets.UTF_8));
        }
    }
}