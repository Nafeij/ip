package duke;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles the local storage I/O of the list of tasks managed by Duke.
 *
 * @see Duke
 * @see Task
 * @see TaskList
 */
public class Storage {
    private String fileName = "data.txt";

    /**
     * Writes the given list of tasks to the file specified by {@link Storage#fileName}. The default file name is
     * {@code data.txt}.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(List<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            System.out.println("\t[ERROR] While saving, the following error occurred: \n\t" + e);
        }
    }

    /**
     * Saves the given list of tasks to the file specified.
     *
     * @param tasks The list of tasks to be saved.
     * @param fileName The name of the file to save to.
     */
    public void save(List<Task> tasks, String fileName) {
        this.fileName = fileName;
        this.save(tasks);
    }

    /**
     * Loads the list of tasks from the file specified by {@link Storage#fileName}. The default file name is
     * {@code data.txt}.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     * @throws ClassCastException If the class of a serialized object does not match the expected class.
     */
    public List<Task> load() throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        @SuppressWarnings("unchecked")
        List<Task> tasks = (ArrayList<Task>) ois.readObject();
        fin.close();
        return tasks;
    }

    /**
     * Loads the list of tasks from the file specified.
     *
     * @param fileName The name of the file to load from.
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     * @throws ClassCastException If the class of a serialized object does not match the expected class.
     */
    public List<Task> load(String fileName) throws IOException, ClassNotFoundException, ClassCastException {
        this.fileName = fileName;
        return this.load();
    }
}
