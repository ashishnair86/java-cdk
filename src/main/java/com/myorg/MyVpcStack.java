package com.myorg;

import java.util.Arrays;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.IpAddresses;   // <-- change here
import software.amazon.awscdk.services.ec2.SubnetConfiguration;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;

public class MyVpcStack extends Stack {

    private final Vpc vpc;
    public MyVpcStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public MyVpcStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // Vpc vpc = Vpc.Builder.create(this, "MyJAVAVPC")
        //     .ipAddresses(IpAddresses.cidr("192.168.0.0/16"))
        //      .subnetConfiguration(Arrays.asList(
        //         SubnetConfiguration.builder()
        //         .name("Application-subnet")
        //         .subnetType(SubnetType.PUBLIC)
        //         .cidrMask(24)
        //         .build(),
        //         SubnetConfiguration.builder()
        //         .name("Private-subnet")
        //         .subnetType(SubnetType.PRIVATE_WITH_EGRESS)
        //         .cidrMask(24)
        //         .build()
           //  ))
     vpc = Vpc.Builder.create(this, "MyVPC") 
         .ipAddresses(IpAddresses.cidr("192.168.0.0/16"))
             .subnetConfiguration(Arrays.asList(
                SubnetConfiguration.builder()
                .name("Application-subnet")
                .subnetType(SubnetType.PUBLIC)
                .cidrMask(24)
                .build(),
                SubnetConfiguration.builder()
                .name("Private-subnet")
                .subnetType(SubnetType.PRIVATE_WITH_EGRESS)
                .cidrMask(24)
                .build()
            ))


             .maxAzs(2)
             .natGateways(1)
            .build();


    }
    public Vpc getVpc() {
        return vpc;
    }
}
