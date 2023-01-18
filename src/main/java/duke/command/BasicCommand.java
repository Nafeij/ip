package duke.command;

import java.util.function.Supplier;

/**
 * An implementation of {@link Command} that does not require any arguments.
 *
 */
public class BasicCommand extends Command {
    private final Supplier<String[]> supplier;

    /**
     * Constructs a new {@link BasicCommand} with the given name, help string, and function.
     * @param name The name of the command.
     * @param helpStr The help string of the command.
     * @param supplier The function to be executed when the command is called.
     */
    public BasicCommand(String name, String helpStr, Supplier<String[]> supplier) {
        super(name, helpStr, false, new String[]{});
        this.supplier = supplier;
    }

    /**
     * Executes the command.
     * @param params The arguments to the command. This is ignored.
     * @return The output of the command.
     */
    @Override
    public String[] execute(String[] params) {
        return supplier.get();
    }
}
