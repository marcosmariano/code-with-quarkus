package org.acme.domain;

import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
public class Components {
    HashMap<String,String> components = new HashMap<>();

    public String getComponent(String componentName) {
        components.put("A","https://localhost:8081");
        components.put("B", "https://localhost:8082");
        components.put("C", "https://localhost:8083");
        return components.get(componentName);
    }
}
