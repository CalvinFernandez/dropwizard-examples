package app.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Locale;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;

import com.google.common.collect.ImmutableMap;

import io.dropwizard.views.freemarker.FreemarkerViewRenderer;

import app.views.AppView;

import org.apache.commons.io.IOUtils;

abstract class AppController {
    public Response ok(String body) {
        return Response.status(Response.Status.OK)
                .header("Content-Type", MediaType.TEXT_PLAIN)
                .entity(body)
                .build();
    }

    public Response ok(String body, MediaType mediaType) {
        return Response.status(Response.Status.OK)
                .header("Content-Type", mediaType)
                .entity(body)
                .build();
    }

    public Response redirect(String loc, UriInfo uinfo) {
        return Response.seeOther(uinfo.getBaseUriBuilder().path(loc).build())
                .build();
    }

    public Response view(String path) {
        return view(path, ImmutableMap.<String, Object> of());
    }

    public Response view(String path, Map<String, Object> attr) {
        try {
            return Response.status(Response.Status.OK)
                    .header("Content-Type", MediaType.TEXT_HTML)
                    .entity(render(path, attr))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(e.getMessage())
                    .build();
        }
    }

    public String render(String path)
            throws IOException, WebApplicationException {
        return render(path, ImmutableMap.<String, Object> of());
    }

    public String render(String path, Map<String, Object> attr)
            throws IOException, WebApplicationException {
        AppView view = new AppView(path, attr);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            new FreemarkerViewRenderer().render(view, Locale.US, os);
            return new String(os.toByteArray(), "UTF-8");
        } catch (IOException e) {
            throw e;
        } catch (WebApplicationException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
}
