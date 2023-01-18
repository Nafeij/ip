package duke.task;

import java.time.LocalDate;

/**
 * An implementation of a task with a description, a start date, and an end date.
 *
 */
public class Event extends Task {

    /**
     * The start date of the task.
     */
    private final LocalDate from;

    /**
     * The end date of the task.
     */
    private final LocalDate to;

    /**
     * Constructs a new {@link Event} with the given description, start date, and end date. The dates are strings in the
     * format {@code yyyy-mm-dd} to be parsed into {@link LocalDate}s.
     * @param desc The description of the task.
     * @param from The start date of the task.
     * @param to The end date of the task.
     * @see LocalDate
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s) (to: %s)", super.toString(),
                from.format(format),
                to.format(format));
    }
}
