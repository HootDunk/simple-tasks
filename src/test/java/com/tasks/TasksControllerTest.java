package com.tasks;

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
        final List result = client.toBlocking().retrieve("/", List.class);
        System.out.println(result);
        assertEquals(5, result.size());

        // ensure that the 5th task has all the expected fields

        // look at MarketsControllerTest
            // add the dependency to test each value with the assertThat function
    }

    @Test
    void updatesTasksAndReturnsUpdateTaskList() {
        final HttpResponse<Object> added = client.toBlocking().exchange(POST("/", "Pay phone bill"));
        // ensure http response is ok
        assertEquals(HttpStatus.OK, added.getStatus());
        // ensure new task object is in the store
        assertEquals(5, store.getAllTasks().size());
    }


    // test bad request (no text provided / empty string provided)






}
