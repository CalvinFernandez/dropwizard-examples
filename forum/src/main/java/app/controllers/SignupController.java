package app.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import com.google.common.collect.ImmutableMap;

import app.components.SignupForm;

@Path("/signup")
public class SignupController extends AppController {
    private final Validator validator;

    public SignupController(Validator validator) {
        this.validator = validator;
    }

    @GET
    @Path("entry")
    public Response entry() {
        return view(
                "signup/entry",
                ImmutableMap.<String, Object> of(
                        "form", SignupForm.defaultForm()));
    }

    @POST
    @Path("entry")
    public Response confirm(@Context UriInfo uinfo,
            @FormParam("email") String email) {

        SignupForm form = SignupForm.emptyForm();
        form.setEmail(email);

        Set<ConstraintViolation<SignupForm>> results = validator.validate(form);
        if (!results.isEmpty()) {
            return view(
                    "signup/entry",
                    ImmutableMap.<String, Object> of(
                            "form", form));
        }

        return redirect("/signup/verify", uinfo);
    }

    @GET
    @Path("verify")
    public Response verify() {
        return view("todo");
    }
}
