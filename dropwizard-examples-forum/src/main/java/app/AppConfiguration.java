package app;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Pattern;

public class AppConfiguration extends Configuration {
    @Pattern(regexp = "(prod(uction)?|dev(elopment)?)")
    private String mode = "development";

    @JsonProperty
    public String getMode() {
        return mode;
    }

    @JsonProperty
    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isDevelopmentMode() {
        return this.mode.startsWith("dev");
    }
}
