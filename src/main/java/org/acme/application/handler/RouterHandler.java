package org.acme.application.handler;

import io.quarkus.vertx.web.RouteFilter;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.acme.service.TenantService;

import javax.inject.Inject;

public class RouterHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RouterHandler.class);

    @Inject
    TenantService tenantService;

    @RouteFilter
    void myFilter(RoutingContext rc) throws Exception {
        LOG.info("Path: "+rc.normalizedPath());
        LOG.info("Header: "+rc.request().getHeader(tenantService.getTenantHeaderName()));

        rc.redirect(tenantService.getTenantHost(rc.request().getHeader(tenantService.getTenantHeaderName())) + rc.request().uri())
                .onSuccess(h-> LOG.info("Redirected with success"))
                .onComplete(c->LOG.info("End Request"))
                .onFailure(throwable -> new Exception("Error when redirect request"));
    }
}