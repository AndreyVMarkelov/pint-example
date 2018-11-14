package test.hb.housekeeper.healthcheck;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public Health health() {
        List<ServiceInstance> billingInstances = discoveryClient.getInstances("billing");
        List<ServiceInstance> routeInstances = discoveryClient.getInstances("route");
        if (!billingInstances.isEmpty() && !routeInstances.isEmpty()) {
            return Health.up().build();
        }
        return Health.down().withDetail("reason", "No billing or route").build();
    }
}
