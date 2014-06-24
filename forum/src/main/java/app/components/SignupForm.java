package app.components;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Email;

public class SignupForm {
    @NotEmpty
    @Email
    private String email = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static SignupForm emptyForm() {
        return new SignupForm();
    }

    public static SignupForm defaultForm() {
        return new SignupForm();
    }
}
