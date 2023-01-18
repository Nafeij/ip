package duke.task;

import java.time.LocalDate;

/**
 * An implementation of a task with a description and a deadline.
 *
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    private final LocalDate by;

    /**
     * Constructs a new {@link Deadline} with the given description and deadline. The deadline is a string in the format
     * {@code yyyy-mm-dd} to be parsed into a {@link LocalDate}.
     * @param desc The description of the task.
     * @param by The deadline of the task.
     * @see LocalDate
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(format) + ")";
    }
}
