package org.scavver.workshop.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.scavver.workshop.http.controller.WorkshopController;

import java.io.IOException;

public class RequestHandler implements HttpHandler {

    private String requestMethod;
    private String requestURI;

    @Override
    public void handle (HttpExchange exchange) throws IOException {

        requestURI    = exchange.getRequestURI().toString();
        requestMethod = exchange.getRequestMethod();

        switch (requestMethod) {

            /** Handling GET requests */

            case "GET":
                switch (requestURI) {

                    case "/next":
                        HttpUtils.response(exchange, 400);
                        break;

                    default:
                        HttpUtils.response(exchange, 404);

                }
                break;

            /** Handling POST requests */

            case "POST":
                switch (requestURI) {

                    case "/numberOfPlaces":
                        HttpUtils.response(exchange, WorkshopController.numberOfPlaces(exchange));
                        break;

                    case "/ship":
                        HttpUtils.response(exchange, WorkshopController.ship(exchange));
                        break;

                    default:
                        HttpUtils.response(exchange, 404);

                }
                break;

            default:
                HttpUtils.response(exchange, 404);

        }

    }

}
