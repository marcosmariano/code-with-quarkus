    package org.acme.service;

    import org.acme.config.RouterConfiguration;

    import javax.inject.Inject;
    import javax.inject.Singleton;
    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.stream.Collectors;

    @Singleton
    public class TenantService {
        @Inject
        RouterConfiguration routerConfiguration;

        private HashMap<String,String> tenants;

        public String getTenantHeaderName(){return routerConfiguration.tenantHeaderName();}

        public String getTenantHost(String tenantId) throws Exception
        {
            if(tenantId==null)
                throw new Exception("Tenant Id can not be null");

            splitTenantList(routerConfiguration.tenantList());
            return this.tenants.get(tenantId);
        }

        private  void splitTenantList(String tenantList) throws Exception
        {
            if(tenantList==null)
                throw new Exception("Tenant List can not be null");

            this.tenants = (HashMap<String, String>) Arrays.asList(tenantList.split(";"))
                    .stream()
                    .map(s->s.split("\\|"))
                    .collect(Collectors.toMap(e->e[0],e->(e[1])));
        }
    }
