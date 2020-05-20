package org.jetbrains.university.util;

import org.apache.commons.cli.*;

public class Settings {
    private String address;
    private String channelName;

    public Settings(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine parsed;
        try {
            parsed = parser.parse(CliMessages.cliOptions, args);
            if (parsed.getArgs().length > 0) {
                printHelpAndExit();
            }

            address = parsed.getOptionValue(CliMessages.SERVER_OPT);
            channelName = parsed.getOptionValue(CliMessages.CHANNEL_OPT);

            if (address == null || channelName == null) {
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
}