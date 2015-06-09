package com.redhat.training.osgi.filtering.breakfix.service.impl;

import com.redhat.training.osgi.filtering.breakfix.service.Greeting;

public class EnglishGreeting implements Greeting {

	@Override
	public String getHelloGreeting() {
		return "Hello";
	}

	@Override
	public String getGoodbyeGreeting() {
		return "Goodbye";
	}

	@Override
	public String getLanguage() {
		return "English";
	}
	


}
