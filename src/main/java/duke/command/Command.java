/**
 * Set of classes that represent commands that Duke recognizes.
 * <p>
 * The Command framework provides a base implementation of  two types of commands: {@link BasicCommand}, which does not
 * accept arguments; and {@link ArgCommand}, which does.
 *
 * @see Duke
 */
package duke.command;

/**
 * The root interface for all Duke command objects.
 *
 * @see BasicCommand
 * @see ArgCommand
 */
public abstract class Command {
    private final String name;
    private final String helpStr;
    private final boolean hasBaseParam;
    private final String[] params;

    /**
     * Defines a new command.
     *
     * @param name          the name of the command, to be called by the user.
     * @param helpStr       the help string of the command.
     * @param hasBaseParam  whether the command takes a single base (unnamed) parameter (e.g. {@code mark 1}).
     * @param params        the named parameters of the command.
     */
    public Command(String name, String helpStr, boolean hasBaseParam, String[] params) {
        this.name = name;
        this.helpStr = helpStr;
        this.hasBaseParam = hasBaseParam;
        this.params = params;
    }

    /**
     * Returns the help string of the command. Used by {@link duke.UI#print()} to generate the help page.
     * @return the help string of the command.
     */
    public String getHelpStr() {
        return helpStr;
    }

    /**
     * Returns the name of the command.
     * @return the name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the names of the parameters of the command.
     * @return an array of the names of the parameters of the command.
     */
    public String[] getParams() {
        return params;
    }

    /**
     * Returns true if the command takes at least one parameter.
     * @return true if the command takes at least one parameter.
     */
    public boolean hasParams() {
        return params.length > 0 || hasBaseParam;
    }

    /**
     * Objects implementing this interface need to define an 'operation', represented by a function that takes an array
     * of string inputs and returns an array of string outputs.
     * This operation is run by the method {@code execute}.
     *
     * @param params the arguments to the command.
     * @return the output of the operation.
     */
    public abstract String[] execute(String[] params);
}