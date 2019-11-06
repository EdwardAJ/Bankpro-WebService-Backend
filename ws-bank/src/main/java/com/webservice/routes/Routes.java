package com.webservice.routes;

import javax.xml.ws.Endpoint;
import com.webservice.controllers.*;

public class Routes {
    public static void main (String[] args) {
        System.out.println("Attempting to connect...");
        // Endpoint.publish("http://localhost:2000/ws-bank/user", new UserController());
        System.out.println("Voilla!");
    }
}