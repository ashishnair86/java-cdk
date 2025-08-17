package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Instance;
import software.amazon.awscdk.services.ec2.InstanceClass;
import software.amazon.awscdk.services.ec2.InstanceSize;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.ec2.MachineImage;
import software.amazon.awscdk.services.ec2.Peer;
import software.amazon.awscdk.services.ec2.SecurityGroup;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;
import software.constructs.Construct;


public class Myec2instance extends Stack {

public Myec2instance(final Construct scope, final String id, final StackProps props, final Vpc vpc ) {

    super(scope, id, props);

   SecurityGroup ec2sg = SecurityGroup.Builder.create(this, "MySG123")
   .vpc(vpc)
   .securityGroupName("Allow-SSH-SG")
    .allowAllOutbound(true)
    .build();

    ec2sg.addIngressRule(Peer.anyIpv4(), software.amazon.awscdk.services.ec2.Port.SSH, "allow ssh");
    ec2sg.addEgressRule(Peer.anyIpv4(), software.amazon.awscdk.services.ec2.Port.tcp(80), "allow http");



   Instance ec2 = Instance.Builder.create(this, "firstEc2")
    .instanceType(InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.MICRO))
    .machineImage(MachineImage.latestAmazonLinux2())
    .vpc(vpc)
    .vpcSubnets(SubnetSelection.builder()
    .subnetType(SubnetType.PUBLIC)
    .build()
    )
    .securityGroup(ec2sg)
   .build();

}
}