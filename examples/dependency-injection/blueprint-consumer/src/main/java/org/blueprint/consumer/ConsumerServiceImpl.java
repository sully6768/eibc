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
package org.blueprint.consumer;

import org.blueprint.producer.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerServiceImpl implements ConsumerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	ProducerService service;
	
	
    public ProducerService getService() {
		return service;
	}


	public void setService(ProducerService service) {
		this.service = service;
	}


	public void init() {
        if (service == null ) {
        	logger.error("Could not locate ProducerService");
        } else {
        	logger.info(service.echo("HELLO WORLD"));
        }
			
    }


}