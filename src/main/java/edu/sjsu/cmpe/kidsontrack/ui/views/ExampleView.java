package edu.sjsu.cmpe.kidsontrack.ui.views;

import com.yammer.dropwizard.views.View;

public class ExampleView extends View {

    private final String message;

    public ExampleView(String message) {
        super("example.ftl");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}