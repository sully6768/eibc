package com.redhat.training.osgi.managedservice.service.impl;

import com.redhat.training.osgi.whiteboard.api.Greeting;

public class ManagedServiceEnglishGreeting implements Greeting {
	
	private String name;
	
	public ManagedServiceEnglishGreeting(String name) {
		this.name = name;
	}


	@Override
	public String getHelloGreeting() {
		return "Hello " + name;
	}


	@Override
	public String getGoodbyeGreeting() {
		return "Goodbye " + name;
	}


	@Override
	public String getLanguage() {
		return "English";
	}

}
