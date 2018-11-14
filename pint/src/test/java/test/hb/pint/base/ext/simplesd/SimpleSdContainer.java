package test.hb.pint.base.ext.simplesd;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

public class SimpleSdContainer extends GenericContainer<SimpleSdContainer> {
    public SimpleSdContainer(Network network, int port) {
        super("andreymarkelov/simple-sd:latest");
        addExposedPorts(port);
        withNetwork(network);
        withNetworkAliases("simple-sd");
    }
}
