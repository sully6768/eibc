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
package org.jboss.fuse.examples.soapjms.spring.itest;

import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.logLevel;
import static org.jboss.fuse.examples.commons.itest.FusePaxExamTestSupport.fuseMavenDistributionConfiguration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.options;

import java.io.File;

import javax.inject.Inject;

import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.apache.karaf.tooling.exam.options.LogLevelOption.LogLevel;
import org.jboss.fuse.examples.services.greeter.Greeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.junit.ProbeBuilder;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test case to exercise the Greeter Client and Server bundles and features.
 */
@RunWith(JUnit4TestRunner.class)
public class GreeterITCase {

    private static final Logger LOG = LoggerFactory.getLogger(GreeterITCase.class);

    @Inject
    private BundleContext bundleContext;

    /**
     * Verifies that the bundle has been installed and is in the
     * {@link Bundle#ACTIVE} state and that the Camel Jetty proxy route is
     * available.
     * 
     * @throws Exception
     */
    @Test(timeout = 30000)
    public void testBundleSucessfullyInstalled() throws Exception {
        // First test to ensure the bundle installed and is running
        // artifactId of the current project
        assertBundleActive("org.jboss.fuse.examples.soapjms.spring.server");
        assertBundleActive("org.jboss.fuse.examples.soapjms.spring.client");
        
        
        Greeter greeter = retrieveGreeterService();
        assertNotNull(greeter);
        
        String reply = greeter.greetMe("Scott");
        assertNotNull(reply);
        assertEquals("Hello Scott", reply);
        
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Greeter retrieveGreeterService() throws InterruptedException {

        ServiceTracker tracker = new ServiceTracker(bundleContext, 
              Greeter.class.getName(), null);
        tracker.open();
        Greeter greeterService = (Greeter) tracker.waitForService(5000);
        tracker.close();
        assertNotNull(greeterService);
        return greeterService;
     }

    /**
     * Asserts that the named bundle is registered with the container's
     * {@link BundleContext} and is in the {@link Bundle#ACTIVE} state.
     * 
     * @param bundleName The symbolic name of the bundle to be looked up.
     */
    protected void assertBundleActive(String bundleName) {
        LOG.info("Asserting {} is active", bundleName);
        Bundle[] bundles = bundleContext.getBundles();
        boolean found = false;
        boolean active = false;

        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().equals(bundleName)) {
                found = true;
                if (bundle.getState() == Bundle.ACTIVE) {
                    LOG.info("  ACTIVE");
                    active = true;
                } else {
                    LOG.info("  NOT ACTIVE");
                }
                break;
            }
        }
        assertTrue(bundleName + " not found in container", found);
        assertTrue(bundleName + " not active", active);
    }

    /**
     * PAX Exam configuration that sets the default LOG level and add test
     * configurations found under the src/test/resources/etc folder.
     * 
     * @return
     */
    @Configuration
    public Option[] config() {
        return options(
                   // You can use either of the following methods
                   // If you installed the Fuse ESB distro into your
                   // local repo there is nothing to change. Otherwise
                   // switch the the following and pass in the location
                   // of your local distro file.
//                    fuseLocalFileDistributionConfiguration("file:/Users/scenglan/Developer/fuse/smx/dist/fuse-esb-full-7.1.0.fuse-047.tar.gz"),
                   fuseMavenDistributionConfiguration(),
                    // OSGi Logging Level
                   logLevel(LogLevel.INFO),
                   
            
                   // Custom Logging
                   new KarafDistributionConfigurationFileReplacementOption("etc/org.ops4j.pax.logging.cfg", 
                                                                           new File("src/test/resources/etc/org.ops4j.pax.logging.cfg")),
            
                   // Updated Port values
                   new KarafDistributionConfigurationFileReplacementOption("etc/system.properties", 
                                                                           new File("src/test/resources/etc/system.properties")),
            
                   // Enables security for Fuse MQ connectivity
                   new KarafDistributionConfigurationFileReplacementOption("etc/users.properties", 
                                                                           new File("src/test/resources/etc/users.properties")),
            
                   // Contains a custom Fuse MQ installation configuration
                   new KarafDistributionConfigurationFileReplacementOption("etc/org.fusesource.mq.fabric.server-default.cfg", 
                                                                           new File("src/test/resources/etc/org.fusesource.mq.fabric.server-default.cfg")),

                   // This is a modified feature configuration that includes
                   // our SOAP/JMS Feature Files
                   new KarafDistributionConfigurationFileReplacementOption("etc/org.jboss.fuse.examples.soapjms.spring.server.cfg",
                                                                           new File("src/test/resources/etc/org.jboss.fuse.examples.soapjms.spring.server.cfg")),
                   
                   // This is a modified feature configuration that includes
                   // our SOAP/JMS Feature Files
                   new KarafDistributionConfigurationFileReplacementOption("etc/org.jboss.fuse.examples.soapjms.spring.client.cfg",
                                                                           new File("src/test/resources/etc/org.jboss.fuse.examples.soapjms.spring.client.cfg")),
                   // This is a modified feature configuration that includes
                   // our SOAP/JMS Feature Files
                   new KarafDistributionConfigurationFileReplacementOption("etc/org.apache.karaf.features.cfg",
                                                                           new File("src/test/resources/etc/org.apache.karaf.features.cfg")));

    }
    


	/**
	 * @param probe
	 * @return
	 */
	@ProbeBuilder
	public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
		// makes sure the generated Test-Bundle contains this import!
		probe.setHeader(Constants.BUNDLE_SYMBOLICNAME, "org.jboss.fuse.examples.soapjms.spring.itest");
		probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "org.jboss.fuse.examples.commons.itest,*,org.apache.felix.service.*;status=provisional");
		return probe;
	}
}
