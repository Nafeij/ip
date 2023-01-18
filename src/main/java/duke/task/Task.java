/**
 * Set of classes representing the types of tasks that can be tracked by Duke.
 * <p>
 * The Task framework provides a base implementation of three types of tasks: {@link ToDo}; {@link Deadline}, which has
 * a defined due date; and {@link Event}, which has a defined start and end date.
 *
 * @since 0.1
 * @see Duke
 * @see TaskList
 */
package duke.task;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * The root interface for all Duke task objects.
 *
 * @since 0.1
 * @see ToDo
 * @see Deadline
 * @see Event
 */
public class Task implements Serializable {

    /**
     * The description of the task.
     */
    private final String desc;

    /**
     * The completion status of the task.
     */
    private boolean isDone;
    static final DateTimeFormatter format = DateTimeFormatter.ofPattern("eee, d MMM uuuu");

    /**
     * Defines a new (unmarked) task.
     *
     * @param desc the description of the task.
     */
    public Task(String desc){
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the status of the task (whether it is marked).
     * @return the status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark(){
        this.isDone = !this.isDone;
        // return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task, furnished with a status icon.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + desc;
    }
}
