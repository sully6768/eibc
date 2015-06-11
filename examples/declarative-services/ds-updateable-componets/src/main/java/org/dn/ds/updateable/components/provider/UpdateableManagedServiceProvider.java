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
package org.dn.ds.updateable.components.provider;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dn.ds.updateable.components.UpdateableManagedService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(name="",
           
           configurationPolicy = ConfigurationPolicy.REQUIRE, service={UpdateableManagedService.class})
public class UpdateableManagedServiceProvider implements UpdateableManagedService, Runnable {

    private String name;
    private static volatile boolean running = true;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);;

    @Activate
    public void start(final Map<String, ?> properties) {
        running = true;
        if (properties.containsKey("name"))
            name = (String)properties.get("name");
        else
            System.out.println("No property found for NAME");
    }

    @Deactivate
    public void stop() { running = false; }
    
    @Modified
    public void update(final Map<String, ?> properties) {
        if (properties.containsKey("name")) {
            String newName = (String)properties.get("name");
            if (!newName.equals(name))
                name = newName;
            else
                System.out.println("Same name, no need to change");
        } else {
            System.out.println("No property found for NAME");
        }
    }

    
    @Override
    public void run() {
        while(running) {
            System.out.println("Hello " + name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Error Halting: " + e.getLocalizedMessage());
            }
        }
    }
    @Override
    public void sayHello() { executorService.execute(this); }

    public void halt() { running = false;}
}
