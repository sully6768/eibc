package com.redhat.training.osgi.basic.provider.service.impl;

import com.redhat.training.osgi.basic.provider.service.Greeting;

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
