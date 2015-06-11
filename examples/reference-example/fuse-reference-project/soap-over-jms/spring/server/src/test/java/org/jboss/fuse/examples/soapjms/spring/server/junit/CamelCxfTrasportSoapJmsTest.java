/*
 * Copyright 2012 FuseSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.fuse.examples.soapjms.spring.server.junit;

import org.apache.activemq.broker.BrokerService;
import org.jboss.fuse.examples.services.greeter.Greeter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Camel route unit test.
 */

public class CamelCxfTrasportSoapJmsTest {
    private static final String BROKER_URI = "tcp://localhost:33333";

    private ClassPathXmlApplicationContext clientApplicationContext;
    private ClassPathXmlApplicationContext serverApplicationContext;
    private Greeter greeter;
    private BrokerService broker;

    @Before
    public void setup() throws Exception {
        broker = new BrokerService();
        broker.setBrokerId("test-broker");
        broker.addConnector(BROKER_URI);
        broker.setBrokerName("test-broker");
        broker.setPersistent(false);
        broker.setUseJmx(true);
        broker.getManagementContext().setConnectorPort(9999);
        broker.start();

        serverApplicationContext = new ClassPathXmlApplicationContext("test-context.xml", "META-INF/spring/camel-context.xml");
        serverApplicationContext.start();

        clientApplicationContext = new ClassPathXmlApplicationContext("test-client-context.xml");
        clientApplicationContext.start();
    }

    @After
    public void tearDown() throws Exception {
        clientApplicationContext.stop();
        serverApplicationContext.stop();
        broker.stop();
    }

    @Test
    public void testPingRoute() throws Exception {
        // GreeterJMSImpl greeterImpl =
        // this.serverApplicationContext.getBean("greeterServer",
        // GreeterJMSImpl.class);
        // assertNotNull(greeterImpl);

        greeter = this.clientApplicationContext.getBean("greeterProxy", Greeter.class);
        assertNotNull(greeter);
        greeter.greetMe("Scott");

        greeter.greetMeOneWay("Ping");
    }

}
