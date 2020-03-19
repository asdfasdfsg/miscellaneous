package org.scavver.workshop;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import org.scavver.workshop.entity.Starship;
import org.scavver.workshop.entity.Workshop;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainHttpHandler implements HttpHandler {

    private String requestMethod;
    private String requestURI;
    private String jsonRequest;
    private String jsonResponse;

    private Gson gson;

    @Override
    public void handle (HttpExchange exchange) throws IOException {

        requestURI    = exchange.getRequestURI().toString();
        requestMethod = exchange.getRequestMethod();

        gson = new Gson();

        if ("/next".equals(requestURI) && "GET".equals(requestMethod)) {

            // Logic

            response(exchange, 400);

        } else if ("/numberOfPlaces".equals(requestURI) && "POST".equals(requestMethod)) {

            jsonRequest = jsonRequestHandler(exchange);

            Workshop workshop = gson.fromJson(jsonRequest, Workshop.class); // Deserialization

            // Logic



            jsonResponse = gson.toJson(workshop); // Serialization

            response(exchange, jsonResponse);

        } else if ("/ship".equals(requestURI) && "POST".equals(requestMethod)) {

            jsonRequest = jsonRequestHandler(exchange);

            Starship starship = gson.fromJson(jsonRequest, Starship.class); // Deserialization

            // Logic



            jsonResponse = gson.toJson(starship); // Serialization

            response(exchange, jsonResponse);

        } else {

            response(exchange, 404);

        }

    }

    /**
     * Gets a HTTP request body with 'Content-Type: application/json' header.
     *
     * @param exchange
     * @return
     * @throws IOException
     */
    private String jsonRequestHandler(HttpExchange exchange) throws IOException {

        List<String> requestHeader = new ArrayList<>();

        requestHeader.add("application/json");

        if (exchange.getRequestHeaders().containsValue(requestHeader)) {

            return IOUtils.toString(exchange.getRequestBody(), StandardCharsets.UTF_8.name());

        } else {

            return null;

        }

    }

    /**
     * 200 HTTP response with a body.
     *
     * @param exchange
     * @param responseBody
     * @throws IOException
     */
    private void response(HttpExchange exchange, String responseBody) throws IOException {

        OutputStream outputStream = exchange.getResponseBody();

        exchange.getResponseHeaders().add("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, responseBody.length());

        outputStream.write(responseBody.getBytes());
        outputStream.flush();
        outputStream.close();

    }

    /**
     * Empty HTTP response with custom code.
     *
     * @param exchange
     * @param rCode
     * @throws IOException
     */
    private void response(HttpExchange exchange, int rCode) throws IOException {

        exchange.sendResponseHeaders(rCode, -1);

    }
}
