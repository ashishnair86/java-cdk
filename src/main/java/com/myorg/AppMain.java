package com.myorg;

import software.amazon.awscdk.App;

public class AppMain {
    public static void main(final String[] args) {
        App app = new App();

        new MyVpcStack(app, "MynewVPC");

        app.synth();
    }
}
