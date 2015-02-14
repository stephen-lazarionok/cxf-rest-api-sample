package com.sla.sampleapp.application.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by Stephen Lazarionok.
 */
public class SampleAppAPI {

    @GET @Path("/")
    @Produces("application/json")
    public Response ping() {

        return Response.ok("Ping is successful").build();
    }
}
