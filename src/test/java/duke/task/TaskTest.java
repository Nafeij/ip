package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private final Task toDo = new ToDo("test");
    private final Task deadline = new Deadline("test", "2020-01-01");
    private final Task event = new Event("test", "2020-01-01", "2020-01-02");

    @Test
    void testToDo() {
        assertEquals("test", toDo.getDesc());
        assertFalse(toDo.isDone());
        assertEquals("[T][ ] test", toDo.toString());
    }

    @Test
    void testToDoMark() {
        toDo.mark();
        assertEquals("test", toDo.getDesc());
        assertTrue(toDo.isDone());
        assertEquals("[T][X] test", toDo.toString());
    }

    @Test
    void testDeadline() {
        assertEquals("test", deadline.getDesc());
        assertFalse(deadline.isDone());
        assertEquals("[D][ ] test (by: Wed, 1 Jan 2020)", deadline.toString());
    }

    @Test
    void testDeadlineMark() {
        deadline.mark();
        assertEquals("test", deadline.getDesc());
        assertTrue(deadline.isDone());
        assertEquals("[D][X] test (by: Wed, 1 Jan 2020)", deadline.toString());
    }

    @Test
    void testEvent() {
        assertEquals("test", event.getDesc());
        assertFalse(event.isDone());
        assertEquals("[E][ ] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString());
    }

    @Test
    void testEventMark() {
        event.mark();
        assertEquals("test", event.getDesc());
        assertTrue(event.isDone());
        assertEquals("[E][X] test (from: Wed, 1 Jan 2020) (to: Thu, 2 Jan 2020)", event.toString());
    }
}