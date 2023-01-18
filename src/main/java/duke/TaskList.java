package duke;

import duke.task.*;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * An object representation of the persistent collection of tasks managed by Duke.
 *
 * @see Duke
 * @see Task
 * @see Storage
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage = new Storage();

    /**
     * Constructs a new {@link TaskList} with the given list of tasks.
     *
     * @param tasks The list of tasks to be managed by the {@link TaskList}.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        storage.save(tasks);
    }

    /**
     * Constructs a new {@link TaskList} with an empty list of tasks.
     *
     * @param fileName The name of the file to save the list of tasks to.
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     * @throws ClassCastException If the class of a serialized object does not match the expected class.
     */
    public TaskList(String fileName) throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load(fileName);
        } catch (FileNotFoundException e) {
            storage.save(tasks, fileName);
        }
        this.tasks = tasks;
    }

    /**
     * Constructs a new {@link TaskList} with an empty list of tasks.
     *
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     * @throws ClassCastException If the class of a serialized object does not match the expected class.
     */
    public TaskList() throws IOException, ClassNotFoundException, ClassCastException {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            storage.save(tasks);
        }
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the tasks in the list.
     * @return A string representation of the tasks in the list.
     */
    public String[] toStrings() {
        if (this.tasks.size() == 0) return new String[]{"No tasks found."};
        String[] outputs = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            outputs[i] = addOrdinal(i,this.tasks.get(i));
        }
        return outputs;
    }

    /**
     * Adds a task of the given type to the list of tasks.
     *
     * @param arguments The arguments of the task to be added. The first element determines the type of task.
     * @return A string representation of the task added.
     */
    public String[] add(String[] arguments) {
        Task newTask;
        switch (arguments[0]) {
            case "/todo" -> newTask = new ToDo(arguments[1]);
            case "/deadline" -> {
                String[] deadlineArgs = Parser.parseArgs(arguments[1], new String[]{" /by "});
                newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            }
            case "/event" -> {
                String[] eventArgs = Parser.parseArgs(arguments[1], new String[]{" /from ", " /to "});
                newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
            }
            default -> throw new IllegalArgumentException("Invalid task type: " + arguments[0]);
        }
        this.tasks.add(newTask);
        this.save();
        return new String[]{"added: " + newTask};
    }

    /**
     * Marks (toggles) the task at the given index(es).
     * @param strings String representations of the index(es) of the task(s) to be marked as done.
     * @return A string representation of the task(s) marked.
     */
    public String[] mark(String[] strings) {
        List<Integer> indexes = new ArrayList<>();
        Consumer<Integer> mark = ind -> {
            this.tasks.get(ind).mark();
            indexes.add(ind);
        };
        this.find(strings, mark);
        this.save();
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task task = this.tasks.get(indexes.get(0));
            return new String[]{(task.isDone() ? "marked: " : "unmarked: ") + addOrdinal(indexes.get(0), task)};
        } else {
            List<String> outputs = indexes.stream()
                    .map(i -> "\t" + addOrdinal(i,this.tasks.get(i)))
                    .collect(Collectors.toList());
            outputs.add(0, "marked:");
            return outputs.toArray(new String[0]);
        }
    }

    /**
     * Deletes the task at the given index(es).
     * @param strings String representations of the index(es) of the task(s) to be deleted.
     * @return A string representation of the task(s) deleted.
     */
    public String[] delete(String[] strings) {
        List<Integer> indexes = new ArrayList<>();
        Consumer<Integer> delete = ind -> {
            if (this.tasks.size() <= ind) {
                throw new IllegalArgumentException("Task not found: " + ind);
            }
            indexes.add(ind);
        };
        this.find(strings, delete);
        if (indexes.size() == 0) {
            throw new IllegalArgumentException("No tasks found.");
        } else if (indexes.size() == 1) {
            Task rmTask = this.tasks.remove((int) indexes.get(0));
            this.save();
            return new String[]{"deleted: " + addOrdinal(indexes.get(0),rmTask)};
        } else {
            indexes.sort(Collections.reverseOrder());
            List<String> outputs = new ArrayList<>();
            for (int i : indexes) {
                outputs.add("\t" + addOrdinal(i, this.tasks.remove(i)));
            }
            this.save();
            Collections.reverse(outputs);
            outputs.add(0, "deleted:");
            return outputs.toArray(new String[0]);
        }
    }

    private void find(String[] argument, Consumer<Integer> consumer) {
        try{
            String[] inds = argument[0].split("\\s");
            for (String s : inds) {
                int ind = Integer.parseInt(s) - 1;
                consumer.accept(ind);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    private String addOrdinal(int index, Task task) {
        return (index + 1) + ". " + task;
    }

    private void save() {
        storage.save(this.tasks);
    }
}