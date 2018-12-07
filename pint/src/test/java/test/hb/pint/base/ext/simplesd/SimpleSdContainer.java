package test.hb.pint.base.ext.simplesd;

import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;

public class SimpleSdContainer extends GenericContainer<SimpleSdContainer> {
    public SimpleSdContainer(Network network, int port) {
        super("andreymarkelov/simple-sd:latest");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("simple-sd");
        withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(" --- simple-sd --- ")));
    }
}
