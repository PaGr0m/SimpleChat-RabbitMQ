package org.jetbrains.university.util;

import org.apache.commons.cli.*;

import java.util.Optional;

import static org.jetbrains.university.util.CliMessages.*;

public class Settings {
    private String address;
    private String userName;
    private String channelName;
    private @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    Optional<Integer> port;

    public Settings(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine parsed;
        try {
            parsed = parser.parse(CliMessages.cliOptions, args);
            if (parsed.getArgs().length > 0) {
                printHelpAndExit();
            }

            address = parsed.getOptionValue(SERVER_OPT);
            channelName = parsed.getOptionValue(CHANNEL_OPT);
            userName = parsed.getOptionValue(USERNAME_OPT);
            port = Optional.ofNullable(parsed.getOptionValue(PORT_OPT)).map(Integer::parseInt);

            if (address == null || channelName == null || userName == null) {
                printHelpAndExit();
            }
        } catch (ParseException | NumberFormatException e) {
            printHelpAndExit();
        }
    }

    private void printHelpAndExit() {
        new HelpFormatter().printHelp(CliMessages.USAGE, CliMessages.cliOptions);
        System.exit(1);
    }

    public String getAddress() {
        return address;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getUserName() {
        return userName;
    }

    public Optional<Integer> getPort() {
        return port;
    }
}
