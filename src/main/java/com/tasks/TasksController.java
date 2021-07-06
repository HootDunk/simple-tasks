package com.tasks;

import com.tasks.model.Task;
import com.tasks.store.InMemoryStore;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name = "Tasks Service")
@Controller("/tasks")
public class TasksController {
    private final InMemoryStore store;

    public TasksController(final InMemoryStore store) { this.store = store; }

    @Operation(summary = "Returns all tasks")
    @Get("/")
    public List<Task> all() { return store.getAllTasks(); }

    @Operation(summary = "Adds a task to the list")
    @Post("/")
    public List<Task> addTask(@Body String text) {
        return store.addTask(text);
    }

    @Operation(summary = "Update a task by its id")
    @Put("/{id}")
    public Task updateTaskText(@PathVariable int id, @Body String newText) {
        return store.updateTask(id, newText);
    }

    @Operation(summary = "Delete a task by its id")
    @Delete("/{id}")
    public void delete(@PathVariable int id) {
        store.deleteTask(id);
    }
}
