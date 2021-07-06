package com.tasks.store;

import com.tasks.model.Task;

import javax.inject.Singleton;
import java.util.*;

// add Singleton annotation so that micronaut can inject it (we later inject it into our test and use it)
@Singleton
public class InMemoryStore {

    private final List<Task> cachedTasks = new ArrayList<>();

    public InMemoryStore() {
        cachedTasks.add(new Task(1, "Walk the dog", false));
        cachedTasks.add(new Task(2, "Finish laundry", false));
        cachedTasks.add(new Task(3, "Complete training", false));
        cachedTasks.add(new Task(4, "Go to the gym", false));
    }

    public List<Task> getAllTasks() {
        return cachedTasks;
    }

    public List<Task> addNewTask(String text) {
        Task newTask = new Task(cachedTasks.size() + 1, text, false);
        cachedTasks.add(newTask);
        return cachedTasks;
    }
}