package com.redhat.training.osgi.filtering.breakfix.service.impl;

import com.redhat.training.osgi.filtering.breakfix.service.Greeting;

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
