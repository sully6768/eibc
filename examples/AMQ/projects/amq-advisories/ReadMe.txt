AMQ Advisory Message Demo with Camel and Spring-DM (OSGi)
=========================================

Install JBoss AMQ

Start JBoss AMQ
./bin/amq clean

Copy the AMQ Config to the etc directory of your JBoss AMQ installation

src/main/amq-configs/advisory-broker.xml
src/main/amq-config/org.fusesource.mq.fabric.server-advisory_broker.cfg

To build this project use 

    mvn install

You can run this project using he following Maven goal:

    mvn camel:run

To deploy the project in OSGi. For example using Apache ServiceMix
or Apache Karaf. You will run the following command from its shell:

    osgi:install -s mvn:org.jboss.integration.amq/org.jboss.integration.amq.advisories/1.0.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/
