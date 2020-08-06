package com.example.helloworld;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;

public class HelloWorld extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new HelloWorld());
    }

    public void start() {
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(this::handleRequest);
        server.listen(8080);
    }

    private void handleRequest(HttpServerRequest req) {
        req.response()
            .putHeader("Content-Type", "text/plain")
            .setStatusCode(200)
            .end("Hello World"); 
    }
}