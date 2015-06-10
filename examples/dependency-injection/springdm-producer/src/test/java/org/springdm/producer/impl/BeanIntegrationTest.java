package org.springdm.producer.impl;

import org.springdm.producer.ProducerService;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Local integration test (outside of OSGi).
 * @see BeanOsgiIntegrationTest for integration test inside OSGi.
 */
public class BeanIntegrationTest extends AbstractDependencyInjectionSpringContextTests {

	private ProducerService myBean;
	
	protected String[] getConfigLocations() {
	  return new String[] {"META-INF/spring/bundle-context.xml"};
	}
	
	public void setBean(ProducerService bean) {
	  this.myBean = bean;
	}
	
	public void testBeanIsABean() {
	  assertEquals("Echo processed: TEST", this.myBean.echo("TEST"));
	}

}
