/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.fuse.examples.services.greeter;

import java.util.logging.Logger;

import javax.jws.WebService;

import org.jboss.fuse.examples.services.greeter.types.FaultDetail;

@WebService
public class GreeterImpl implements Greeter {

    private static final Logger LOG = Logger.getLogger(GreeterImpl.class.getName());

    @Override
    public String greetMe(String me) {
        LOG.info("Executing operation greetMe");
        LOG.info("Executing operation greetMe");
        LOG.info("Message received: " + me + "\n");
        return "Hello " + me;
    }

    @Override
    public String sayHi() {
        LOG.info("Executing operation sayHi");
        LOG.info("Executing operation sayHi" + "\n");
        return "Bonjour";
    }

    @Override
    public void greetMeOneWay(String me) {
        LOG.info("Executing operation greetMeOneWay");
        LOG.info("Executing operation greetMeOneWay\n");
        LOG.info("Hello there " + me);
    }

    @Override
    public void pingMe(String faultType) throws PingMeFault {
        FaultDetail faultDetail = new FaultDetail();
        faultDetail.setMajor((short)2);
        faultDetail.setMinor((short)1);
        LOG.info("Executing operation pingMe, throwing PingMeFault exception, message = "
                 + faultType);
        LOG.info("Executing operation pingMe, throwing PingMeFault exception\n");
        throw new PingMeFault("PingMeFault raised by server", faultDetail);
    }

}
