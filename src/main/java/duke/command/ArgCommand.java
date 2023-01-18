package duke.command;

import java.util.function.Function;

/**
 * An implementation of {@link Command} that requires arguments.
 *
 */
public class ArgCommand extends Command {
    private final Function<String[], String[]> function;

    /**
     * Constructs a new {@link ArgCommand} with the given name, help string, argument regexes, and function.
     * @param name The name of the command.
     * @param helpStr The help string of the command.
     * @param params The named parameters of the command.
     * @param function The function to be executed when the command is called.
     */
    public ArgCommand(String name, String helpStr, String[] params, Function<String[], String[]> function) {
        super(name, helpStr, true, params);
        this.function = function;
    }

    /**
     * Executes the command.
     * @param params The arguments to the command.
     * @return The output of the command.
     */
    @Override
    public String[] execute(String[] params) {
        return function.apply(params);
    }
}