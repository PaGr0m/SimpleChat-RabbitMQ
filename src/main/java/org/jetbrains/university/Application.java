package org.jetbrains.university;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.jetbrains.university.util.ColorPrinter;
import org.jetbrains.university.util.MailUtils;
import org.jetbrains.university.util.Settings;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static java.util.logging.Level.INFO;

public class Application {

    private static final ColorPrinter printer = new ColorPrinter(System.out);

    public static void main(String[] args) throws IOException, TimeoutException {
        Settings settings = new Settings(args);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(settings.getAddress());

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare(settings.getChannelName(), BuiltinExchangeType.FANOUT);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, settings.getChannelName(), settings.getUserName());

            channel.basicConsume(
                    queueName, true,
                    (consumerTag, delivery) -> {
                        if (!delivery.getEnvelope().getRoutingKey().equals(settings.getUserName())) {
                            MailUtils.printMail(delivery.getBody(), printer);
                        }
                    },
                    consumerTag -> { }
            );

            startReader(channel, settings);
        }
    }

    private static void startReader(Channel channel, Settings settings) throws IOException {
        printer.log(INFO, "[*] connected to channel " + settings.getChannelName());
        printer.log(INFO, "[*] To exit type \"exit\" :)");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.nextLine();
            if (msg.equals("exit")) {
                break;
            }

            channel.basicPublish(settings.getChannelName(), settings.getUserName(),
                    null, MailUtils.createMailString(msg, settings));
        }
    }
}
