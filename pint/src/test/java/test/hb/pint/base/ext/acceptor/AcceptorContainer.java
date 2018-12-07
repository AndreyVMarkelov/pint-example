package test.hb.pint.base.ext.acceptor;

import java.time.Duration;

import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

public class AcceptorContainer extends GenericContainer<AcceptorContainer> {
    public AcceptorContainer(Network network, int port) {
        super("andreymarkelov/acceptor:latest");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("acceptor");
        HttpWaitStrategy httpWaitStrategy = new HttpWaitStrategy();
        httpWaitStrategy.withStartupTimeout(Duration.ofMinutes(2));
        waitingFor(httpWaitStrategy.forPath("/actuator/health").forStatusCode(200));
        addEnv("EUREKASERVER_PORT", "8761");
        addEnv("EUREKASERVER_URI", "http://simple-sd:8761/eureka/");
        withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(" --- acceptor --- ")));
    }
}
