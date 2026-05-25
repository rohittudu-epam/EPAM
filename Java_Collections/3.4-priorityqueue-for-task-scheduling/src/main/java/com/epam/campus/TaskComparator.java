package com.epam.campus;

import java.util.Comparator;

/**
 * Comparator for ordering Task objects by priority in descending order.
 * Higher priority values (5) are considered greater than lower values (1),
 * ensuring that higher priority tasks are processed first in a PriorityQueue.
 */
public class TaskComparator implements Comparator<Task>{
    /**
     * Compares two tasks by their priority in descending order.
     *
     * @param t1 The first task
     * @param t2 The second task
     * @return A negative integer if t2 has higher priority, positive if t1 has higher priority, 0 if equal
     */
    public int compare(Task t1, Task t2) {
        return Integer.compare(t2.getPriority(), t1.getPriority());
    }
}
