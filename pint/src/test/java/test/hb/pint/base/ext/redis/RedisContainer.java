package test.hb.pint.base.ext.redis;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;

public class RedisContainer extends GenericContainer<RedisContainer> {
    public RedisContainer(Network network, int port) {
        super("redis");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("redis");
        waitingFor(new HostPortWaitStrategy());
    }
}
