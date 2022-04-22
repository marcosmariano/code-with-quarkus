package org.acme.application.handler;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import org.acme.service.TenantService;

import javax.inject.Inject;

public class RouterHandler {
    @Inject
    TenantService tenantService;

    @RouteFilter
    void myFilter(RoutingContext rc) throws Exception {
        System.out.println("Path: "+rc.normalizedPath());
        System.out.println("Header: "+rc.request().getHeader(tenantService.getTenantHeaderName()));

        rc.redirect(tenantService.getTenantHost(rc.request().getHeader(tenantService.getTenantHeaderName())) + rc.normalizedPath())
                .onSuccess(h->{
                    System.out.println("Redirected with success");
                })
                .onFailure(throwable -> new Exception("Error when redirect request"));
        rc.next();
    }
}