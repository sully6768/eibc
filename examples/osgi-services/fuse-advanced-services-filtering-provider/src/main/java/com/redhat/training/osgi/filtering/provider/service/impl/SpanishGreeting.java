package com.redhat.training.osgi.filtering.provider.service.impl;

import com.redhat.training.osgi.filtering.provider.service.Greeting;

public class SpanishGreeting implements Greeting {

	@Override
	public String getHelloGreeting() {
		return "Hola";
	}

	@Override
	public String getGoodbyeGreeting() {
		return "Adios";
	}

	@Override
	public String getLanguage() {
		return "Spanish";
	}


}
