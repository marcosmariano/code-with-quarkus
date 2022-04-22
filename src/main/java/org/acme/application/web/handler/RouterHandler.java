package org.acme.application.web.handler;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;

public class RouterHandler {
    @RouteFilter(100)
    void myFilter(RoutingContext rc) {
        System.out.println(rc.normalizedPath());

        //rc.response().setStatusCode(202).putHeader("x-interceptor","Intercept Request").end();
        rc.redirect("https://api.thecatapi.com" + rc.normalizedPath())
                .onSuccess(h->{
                    System.out.println("Sucesso");
                })
                .onFailure(throwable -> new Exception("Erro ao redirecionar"));
        rc.next();
    }
}