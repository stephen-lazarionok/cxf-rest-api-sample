package com.sla.sampleapp.application.rest;

import org.springframework.core.task.TaskExecutor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 * Created by Stephen Lazarionok.
 */
public class SampleAppAPI {


    private TaskExecutor taskExecutor;

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @GET @Path("/ping")
    @Produces("application/json")
    public Response ping() {
        log("Ping");
        suspend(1);
        log("Pong");
        return Response.ok("Pong").build();
    }

    @GET @Path("/asyncping")
    @Produces("application/json")
    public void asyncPing(final @Suspended AsyncResponse asyncResponse) {
        log("Async ping");

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                suspend(1);
                log("async pong");
                asyncResponse.resume("async pong");
            }
        });
    }

    private void suspend(final long secs) {
        try {
            log("Suspending...");
            Thread.sleep(secs * 1000);
            log("Resuming...");
        } catch (InterruptedException e) {
            log("Some exception occured while waiting suspending the current thread!");
        }
    }

    private void log(final String msg) {

        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
