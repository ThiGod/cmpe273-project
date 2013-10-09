package edu.sjsu.cmpe.kidsontrack.views;

import com.yammer.dropwizard.views.View;

public class WelcomeView extends View {

    private final String message;

    public WelcomeView(String message) {
        super("welcome.ftl");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
