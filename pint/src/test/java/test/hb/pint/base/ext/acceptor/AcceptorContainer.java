package test.hb.pint.base.ext.acceptor;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

public class AcceptorContainer extends GenericContainer<AcceptorContainer> {
    public AcceptorContainer(Network network, int port) {
        super("andreymarkelov/housekeeper:latest");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("housekeeper");
        waitingFor(new HttpWaitStrategy().forPath("/actuator/health").forStatusCode(200));
        addEnv("EUREKASERVER_PORT", "8761");
        addEnv("EUREKASERVER_URI", "http://simple-sd:8761/eureka/");
    }
}
