package duke.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is managed by Duke.
 * A task has a description and can either be a {@link ToDo}; a {@link Deadline}
 * with a due date or a {@link Event} with a start and end date.
 *
 * @see duke.Duke
 * @see duke.TaskList
 */
public class Task implements Serializable {

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("eee, d MMM uuuu");
    /**
     * The description of the task.
     */
    private final String desc;
    /**
     * The status of the task.
     */
    private boolean isDone;

    /**
     * Constructs a new task with the given description.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the status of the task.
     *
     * @return True if the task is marked as done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = !this.isDone;
        // return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + desc;
    }

}
