package duke;

import duke.command.Command;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Utility class for the printing of output by Duke.
 *
 * @see Duke
 */
public class UI {

    private String[] helpMsg;

    /**
     * Accepts a list of commands used to compile the help message.
     * @param commands The list of commands.
     * @see Command
     */
    public void setCommands(Command[] commands) {
        this.helpMsg = generateHelp(commands);
    }

    private String[] generateHelp(Command[] commands) {
        Stream<String> strings = Arrays.stream(commands)
                .map(c -> String.format("\t%4s : %s", c.getName(), c.getHelpStr()));
        return Stream.concat(Stream.of("Usage: <command> [<args>]"), strings).toArray(String[]::new);
    }

    /**
     * Prints the help message.
     */
    public void print() {
        this.print(this.helpMsg);
    }

    /**
     * Prints the given message, with proper formatting and indentation.
     * @param msg The message to be printed.
     */
    public void print(String msg){
        this.print(msg.split("\n"));
    }

    /**
     * Prints the given lines of messages, with proper formatting and indentation.
     * @param lines The message to be printed, split into lines.
     */
    public void print(String[] lines) {
        StringBuilder outputs = new StringBuilder();
        for (String str : lines) {
            outputs.append("\t").append(str).append("\n");
        }
        String line = "____________________________________________________________\n";
        System.out.print(outputs + line);
    }

    /**
     * Prints an introductory message.
     */
    public void printIntro() {

        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___| ,
                """;
        System.out.println("Hello, I'm\n" + logo + "how may I help?");
    }

    /**
     * Prints a formatted error message.
     * @param e The exception to be displayed.
     */
    public void error(Exception e) {
        this.print("\t[ERROR] " + e);
    }

    /**
     * Prints a formatted initialization error message.
     * @param e The exception to be displayed.
     */
    public void loadError(Exception e) {
        this.print("\t[ERROR] While loading, the following error occurred: \n\t" + e);
    }
}
