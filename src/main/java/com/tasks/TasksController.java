package com.tasks;

import com.tasks.model.Task;
import com.tasks.store.InMemoryStore;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;


@Tag(name = "Tasks Service")
@Controller("/tasks")
public class TasksController {
    private final InMemoryStore store;

    public TasksController(final InMemoryStore store) { this.store = store; }

    @Operation(summary = "Returns all tasks")
    @Get("/")
    public List<Task> all() { return store.getAllTasks(); }

    // ahhh, the @Body is referring to the body of the request
    // ** Make it so that you can't add empty tasks
    @Operation(summary = "Adds a task to the list")
    @Put("/")
    public List<Task> update(@Body String text) {
        return store.updateTasks(text);
    }
}
