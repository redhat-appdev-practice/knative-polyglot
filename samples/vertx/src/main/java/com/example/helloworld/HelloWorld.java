package com.example.helloworld;

import io.reactivex.Flowable;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;

public class HelloWorld extends AbstractVerticle {

    public static void main(String[] args) {
        EventBusOptions evOpts = new EventBusOptions()
            .setClustered(false);
        VertxOptions opts = new VertxOptions();
        opts.setPreferNativeTransport(false);
        opts.setHAEnabled(false);
        opts.setEventBusOptions(evOpts);
            
        Vertx.vertx(opts).deployVerticle(new HelloWorld());
    }

    public void start() {

        final HttpServer server = vertx.createHttpServer();
        final Flowable<HttpServerRequest> requestFlowable = server.requestStream().toFlowable();

        requestFlowable.subscribe(httpServerRequest -> {

            String target = System.getenv("TARGET");
            if (target == null) {
                target = "NOT SPECIFIED";
            }

            httpServerRequest.response().setChunked(true)
                    .putHeader("content-type", "text/plain")
                    .setStatusCode(200) // OK
                    .end("Hello World: " + target);
        });

        server.listen(8080);
    }
}
