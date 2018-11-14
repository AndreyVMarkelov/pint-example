package test.hb.pint.base.ext.billing;

import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;

public class BillingContainer extends GenericContainer<BillingContainer> {
    public BillingContainer(Network network, int port) {
        super("andreymarkelov/billing:latest");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("billing");
        waitingFor(new HostPortWaitStrategy());
        addEnv("EUREKASERVER_PORT", "8761");
        addEnv("EUREKASERVER_URI", "http://simple-sd:8761/eureka/");
        withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(" --- billing --- ")));
    }
}
