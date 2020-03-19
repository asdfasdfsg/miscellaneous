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
                        Util.response(exchange, 400);
                        break;

                    default:
                        Util.response(exchange, 404);

                }
                break;

            /** Handling POST requests */

            case "POST":
                switch (requestURI) {

                    case "/numberOfPlaces":
                        Util.response(exchange, WorkshopController.numberOfPlaces(exchange));
                        break;

                    case "/ship":
                        Util.response(exchange, WorkshopController.ship(exchange));
                        break;

                    default:
                        Util.response(exchange, 404);

                }
                break;

            default:
                Util.response(exchange, 404);

        }

    }

}
