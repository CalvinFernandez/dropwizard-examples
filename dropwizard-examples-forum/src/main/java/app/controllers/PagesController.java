package app.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import app.views.AppView;

@Path("/")
public class PagesController {
    private final Map<String, Object> params;

    public PagesController() {
        this(ImmutableMap.<String, Object> of());
    }

    public PagesController(Map<String, Object> params) {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            builder.put(param.getKey(), param.getValue());
        }
        this.params = builder.build();
    }

    @GET
    public Response home() {
        return index();
    }

    @GET
    @Path("index.html")
    public Response index() {
        return page("index");
    }

    @GET
    @Path("pages/{name}.html")
    public Response page(@PathParam("name") String name) {
        return Response.ok(new AppView("pages/" + name, params)).build();
    }
}
