package duke;

import duke.command.Command;

/**
 * Utility class for parsing user input to Duke and arguments to TaskList.
 *
 * @see Duke
 * @see TaskList
 */
public class Parser {
    Command[] commands;

    /**
     * Constructs a new Parser with the given commands.
     * @param commands the commands to be parsed.
     */
    public Parser(Command[] commands) {
        this.commands = commands;
    }

    /**
     * Returns the command corresponding to the given input.
     * @param name the line to be parsed.
     * @return the command corresponding to the given input.
     */
    public Command parseCommand(String name) {
        for (Command cmd: commands) {
            if (name.equals(cmd.getName())) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("Command not found: " + name);
    }

    /**
     * Returns the arguments corresponding to the given input and command.
     * @param argPart the line of arguments to be parsed.
     * @param cmd the command called.
     * @return the arguments corresponding to the given input and command.
     */
    public static String[] parseArgs(String argPart, Command cmd) {
        return Parser.parseArgs(argPart, cmd.getParams());
    }

    /**
     * Returns the arguments corresponding to the given input and parameters.
     * <p>
     * The parameters are used as regexes to split the input.
     * @param argPart the line of arguments to be parsed.
     * @param regexes the parameters of the command.
     * @return the arguments corresponding to the given input and parameters.
     */
    public static String[] parseArgs(String argPart, String[] regexes) {
        String[] outputs = new String[regexes.length + 1];
        for (int i = 0; i < regexes.length; i++) {
            String[] temp = argPart.split(regexes[i],2);
            if (temp.length > 1){
                outputs[i] = temp[0];
                argPart = temp[1];
            } else {
                throw new IllegalArgumentException("Missing argument.");
            }
        }
        outputs[regexes.length] = argPart;
        return outputs;
    }
}
