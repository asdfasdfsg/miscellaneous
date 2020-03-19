package org.scavver.workshop.http.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.scavver.workshop.entity.Starship;
import org.scavver.workshop.entity.Workshop;
import org.scavver.workshop.http.Util;

import java.io.IOException;

public class WorkshopController {

    private static Workshop workshop;
    private static Starship starship;

    private static Gson gson;

    public static String numberOfPlaces(HttpExchange exchange) throws IOException {

        gson = new Gson();

        workshop = gson.fromJson(Util.jsonHandler(exchange), Workshop.class); // Deserialization

        // Logic

        return gson.toJson(workshop); // Serialization

    }

    public static String ship(HttpExchange exchange) throws IOException {

        gson = new Gson();

        starship = gson.fromJson(Util.jsonHandler(exchange), Starship.class); // Deserialization

        // Logic

        return gson.toJson(starship); // Serialization

    }

}
