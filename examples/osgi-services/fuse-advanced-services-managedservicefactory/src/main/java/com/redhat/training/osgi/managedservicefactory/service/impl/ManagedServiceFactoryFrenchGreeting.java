package com.redhat.training.osgi.managedservicefactory.service.impl;

import com.redhat.training.osgi.whiteboard.api.Greeting;

public class ManagedServiceFactoryFrenchGreeting implements Greeting {
	
	private String name;
	
	public ManagedServiceFactoryFrenchGreeting(String name) {
		this.name = name;
	}


	@Override
	public String getHelloGreeting() {
		return "Bonjour " + name;
	}


	@Override
	public String getGoodbyeGreeting() {
		return "Au revoir " + name;
	}


	@Override
	public String getLanguage() {
		return "French";
	}

}
