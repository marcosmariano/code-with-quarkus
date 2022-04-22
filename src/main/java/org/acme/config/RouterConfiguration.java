package org.acme.config;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "router", namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
public interface RouterConfiguration {
    String tenantList();
    String tenantHeaderName();
}
