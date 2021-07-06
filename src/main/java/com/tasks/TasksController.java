package com.tasks;

import com.tasks.error.CustomError;
import com.tasks.model.Task;
import com.tasks.store.InMemoryStore;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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



    @Operation(summary = "Adds a task to the list")
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @ApiResponse(responseCode = "400", description = "No task provided")
    @Post("/")
    public HttpResponse addTask(@Body String text) {
        if (text.isEmpty()){
            final CustomError notFound = CustomError.builder()
                    .status(HttpStatus.BAD_REQUEST.getCode())
                    .error(HttpStatus.BAD_REQUEST.name())
                    .message("No task provided")
                    .path("/tasks/")
                    .build();

            return HttpResponse.badRequest(notFound);
        }
        return HttpResponse.ok(store.addNewTask(text));
    }
}
