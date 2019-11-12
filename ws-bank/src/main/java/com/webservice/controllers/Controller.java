package com.webservice.controllers;

public class Controller {
	public int responseCode;
	public String data[];

	public void render(int statusCode, Object message) {
		System.out.println(statusCode + " " +  message);
	}
}


