/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dn.ds.simple.osgi.service;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.prefs.PreferencesService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * TODO Add Class documentation for SimpleActivator
 *
 * @author sully6768
 */
public class SimpleActivator implements BundleActivator, Echo {
    // ServiceTracker for PreferencesServices
    private ServiceTracker serviceTracker;
    // BundleContext
    private BundleContext bc;
    // Registration of Echo service
    private ServiceRegistration registration;

    // activation
    public void start(BundleContext context) throws Exception {
        bc = context;
        // init and start ServiceTracker to track PreferencesService
        serviceTracker = new ServiceTracker(context, PreferencesService.class.getName(), new Customizer());
        serviceTracker.open();
    }

    // deactivation
    public void stop(BundleContext context) throws Exception {
        // stop ServiceTracker to track PreferencesService
        serviceTracker.close();
        serviceTracker = null;
    }

    public String echo(String str) {
        return str;
    }

    // customizer that handles tracked service registration/modification/unregistration events
    private class Customizer implements ServiceTrackerCustomizer {
        
        public Object addingService(ServiceReference reference) {
            System.out.println("PreferencesService is linked");
            // register Echo service
            Dictionary<String, String> props = new Hashtable<String, String>();
            props.put(ECHO_TYPE_PROP, "BundleActivator");
            registration = bc.registerService(Echo.class.getName(), SimpleActivator.this, props);

            return bc.getService(reference);
        }

        public void modifiedService(ServiceReference reference, Object service) {
        }

        public void removedService(ServiceReference reference, Object service) {
            // unregister Echo service
            registration.unregister();
            System.out.println("PreferencesService is unlinked");
        }
    }
}
