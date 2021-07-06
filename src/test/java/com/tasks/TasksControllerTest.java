package com.tasks;

import com.tasks.model.Task;
import com.tasks.store.InMemoryStore;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import static io.micronaut.http.HttpRequest.POST;
import static io.micronaut.http.HttpRequest.PUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;


@MicronautTest
public class TasksControllerTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/tasks")
    RxHttpClient client;

    @Inject
    InMemoryStore store;

    @Test
    void returnsAllTasks() {
        // ensure the get request returns the exact value in the store
        final List result = client.toBlocking().retrieve("/", List.class);
        final List storeResult = store.getAllTasks();
        assertEquals(result.size(), storeResult.size());
        System.out.println(result);
    }

    @Test
    void updatesTasksAndReturnsUpdateTaskList() {
        final HttpResponse<Object> updatedTasks = client.toBlocking().exchange(POST("/", "Pay phone bill"));
        assertEquals(HttpStatus.OK, updatedTasks.getStatus());
        assertEquals(5, store.getAllTasks().size());
    }

//    @Test
//    void changeTheTextOfATask() {
//        final HttpResponse<Object> updatedTasks = client.toBlocking().exchange(PUT("/1", "Walk the dogs"));
//        System.out.println(updatedTasks.getBody());
//        System.out.println(store.getAllTasks());
//    }




}
