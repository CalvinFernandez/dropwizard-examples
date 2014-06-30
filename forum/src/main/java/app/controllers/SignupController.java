package app.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import com.google.common.collect.ImmutableMap;

import app.components.FormHelper;
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
                        "form",
                        new FormHelper<SignupForm>(SignupForm.defaultForm())));
    }

    @POST
    @Path("entry")
    @Consumes("application/x-www-form-urlencoded")
    public Response confirm(@Context UriInfo uinfo,
            MultivaluedMap<String, String> formParams) {
        SignupForm form = SignupForm.bindFrom(formParams);
        Set<ConstraintViolation<SignupForm>> errors = validator.validate(form);
        if (!errors.isEmpty()) {
            return view(
                    "signup/entry",
                    ImmutableMap.<String, Object> of(
                            "form",
                            new FormHelper<SignupForm>(form, errors)));
        }

        return redirect("/signup/verify", uinfo);
    }

    @GET
    @Path("verify")
    public Response verify() {
        return view("todo");
    }
}
