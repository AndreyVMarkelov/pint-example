package test.hb.pint.base.ext.route;

import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;

public class RouteContainer extends GenericContainer<RouteContainer> {
    public RouteContainer(Network network, int port) {
        super("andreymarkelov/route:latest");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("route");
        waitingFor(new HostPortWaitStrategy());
        addEnv("EUREKASERVER_PORT", "8761");
        addEnv("EUREKASERVER_URI", "http://simple-sd:8761/eureka/");
        addEnv("PROVIDER_URL", "http://mockserver:1080/sendsms");
        withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(" --- route --- ")));
    }
}
