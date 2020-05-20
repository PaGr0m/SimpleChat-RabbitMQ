package org.jetbrains.university.util;

import org.apache.commons.cli.Options;

public class CliMessages {
    private CliMessages() {}

    public static final Options cliOptions = new Options();

    public static final String USAGE = "./chat <OPTIONS>";

    public static final String SERVER_OPT = "s";
    public static final String CHANNEL_OPT = "c";

    private static final String SERVER_NAME = "server";
    private static final String CHANNEL_NAME = "channel";

    private static final String SERVER_DESCRIPTION = "specify server address";
    private static final String CHANNEL_DESCRIPTION = "specify channel name";

    static {
        cliOptions.addOption(SERVER_OPT, SERVER_NAME, true, SERVER_DESCRIPTION);
        cliOptions.addOption(CHANNEL_OPT, CHANNEL_NAME, true, CHANNEL_DESCRIPTION);
    }
}