package org.jetbrains.university.util;

import org.apache.commons.cli.Options;

public class CliMessages {
    public static final Options cliOptions = new Options();
    public static final String USAGE = "./chat <OPTIONS>";
    public static final String SERVER_OPT = "s";
    public static final String CHANNEL_OPT = "c";
    public static final String USERNAME_OPT = "u";
    public static final String PORT_OPT = "p";

    private static final String SERVER_NAME = "server";
    private static final String CHANNEL_NAME = "channel";
    private static final String USERNAME_NAME = "channel";
    private static final String PORT_NAME = "port";
    private static final String SERVER_DESCRIPTION = "specify server address";
    private static final String CHANNEL_DESCRIPTION = "specify channel name";
    private static final String USERNAME_DESCRIPTION = "specify user name";
    private static final String PORT_DESCRIPTION = "specify port";

    static {
        cliOptions.addOption(SERVER_OPT, SERVER_NAME, true, SERVER_DESCRIPTION);
        cliOptions.addOption(CHANNEL_OPT, CHANNEL_NAME, true, CHANNEL_DESCRIPTION);
        cliOptions.addOption(USERNAME_OPT, USERNAME_NAME, true, USERNAME_DESCRIPTION);
        cliOptions.addOption(PORT_OPT, PORT_NAME, true, PORT_DESCRIPTION);
    }

    private CliMessages() {}
}
