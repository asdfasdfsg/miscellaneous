package org.scavver.workshop;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.OutputStream;

public class TestHttpHandler implements HttpHandler {

    private String requestParamValue;

    @Override
    public void handle (HttpExchange exchange) throws IOException {

        if( "GET".equals(exchange.getRequestMethod()) ) {
            requestParamValue = handleGetRequest(exchange);
        } else if( "POST".equals(exchange) ) {
            requestParamValue = handlePostRequest(exchange);
        }

        handleResponse(exchange,requestParamValue);
    }

    private String handlePostRequest(HttpExchange exchange) {
        return exchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private String handleGetRequest(HttpExchange exchange) {
        return exchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

    private void handleResponse(HttpExchange exchange, String requestParamValue)  throws  IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html>").
                append("<body>").
                append("<h1>").
                append("Hello ")
                .append(requestParamValue)
                .append("</h1>")
                .append("</body>")
                .append("</html>");

        // encode HTML content
        String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());

        // this line is a must
        exchange.sendResponseHeaders(200, htmlResponse.length());

        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
