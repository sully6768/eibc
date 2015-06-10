package org.springdm.producer.impl;

import junit.framework.TestCase;

import org.springdm.producer.ProducerService;

public class BeanImplTest extends TestCase {

    public void testBeanIsABean() {
	ProducerService aBean = new ProducerServiceImpl();
        assertEquals("Echo processed: TEST", aBean.echo("TEST"));
    }

}