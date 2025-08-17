package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

public class AppMain {
    public static void main(final String[] args) {
        App app = new App();

        MyVpcStack vpcstack = new MyVpcStack(app, "MyVPC");
        Myec2instance myec2instance = new Myec2instance(app, "MyEC2", StackProps.builder().build(), vpcstack.getVpc());

        app.synth();
    }
}
