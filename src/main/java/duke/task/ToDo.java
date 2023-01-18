package duke.task;

/**
 * A simple implementation of a task with a description.
 *
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@link ToDo} with the given description.
     * @param desc The description of the task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
