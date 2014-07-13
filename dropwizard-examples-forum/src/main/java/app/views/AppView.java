package app.views;

import io.dropwizard.views.View;

import java.util.Map;
import com.google.common.collect.ImmutableMap;

public class AppView extends View {
    private final Map<String, Object> attributes;

    public AppView(String path) {
        this(path, ImmutableMap.<String, Object> of());
    }

    public AppView(String path, Map<String, Object> attributes) {
        super(path + ".ftl");
        this.attributes = attributes;
    }

    public Object get(String name) {
        return attributes.get(name);
    }
}
