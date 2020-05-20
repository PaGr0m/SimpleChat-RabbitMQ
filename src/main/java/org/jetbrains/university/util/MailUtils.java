package org.jetbrains.university.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Timestamp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.university.generated.MailOuterClass.Mail;

import java.util.Date;
import java.util.logging.Level;

public abstract class MailUtils {

    public static void printMail(@NotNull byte[] mailRaw, @NotNull ColorPrinter output) throws InvalidProtocolBufferException {
        Mail mail = Mail.parseFrom(mailRaw);

        Date sendDate = new Date(mail.getSendTime().getSeconds() * 1000);
        String header = String.format("[%s] %s :",
                mail.getSender(),
                sendDate.toString());
        output.log(Level.SEVERE, header);
        output.println(mail.getText());
        output.println("");
    }

    public static byte[] createMailString(String message, Settings settings) {
        return Mail.newBuilder().setSender(settings.getUserName())
                .setSendTime(Timestamp.newBuilder().setSeconds(
                        System.currentTimeMillis() / 1000).build())
                .setText(message).build().toByteArray();
    }
}
