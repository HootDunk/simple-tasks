package com.tasks.store;

import com.tasks.model.Task;

import javax.inject.Singleton;
import java.util.*;

// add Singleton annotation so that micronaut can inject it (we later inject it into our test and use it)
@Singleton
public class InMemoryStore {

    private final List<Task> cachedTasks = new ArrayList<>();
    private int taskCount = 1;

    public InMemoryStore() {
        cachedTasks.add(new Task(taskCount++, "Walk the dog", false));
        cachedTasks.add(new Task(taskCount++, "Finish laundry", false));
        cachedTasks.add(new Task(taskCount++, "Complete training", false));
        cachedTasks.add(new Task(taskCount++, "Go to the gym", false));
    }

    public List<Task> getAllTasks() {
        return cachedTasks;
    }

    public List<Task> addTask(String text) {
        Task newTask = new Task(taskCount++, text, false);
        cachedTasks.add(newTask);
        return cachedTasks;
    }

    public Task updateTask(int id, String newText) {
        Task matchingTask = cachedTasks.stream()
                .filter(task -> id == task.getId())
                .findAny()
                .orElse(null);
        matchingTask.setText(newText);
        return matchingTask;
    }

    public void deleteTask(int id) {
        int index = cachedTasks.indexOf(cachedTasks.stream()
                .filter(task -> id == task.getId())
                .findAny()
                .orElse(null));
        cachedTasks.remove(index);
    }
}