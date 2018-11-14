package test.hb.pint.base.ext.mock;

import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;

import com.github.dockerjava.api.command.InspectContainerResponse;

public class MockServerContainer extends GenericContainer<MockServerContainer> {
    private final int mockServerPort;
    private MockServerClient client;

    public MockServerContainer(Network network, int mockServerPort, String logLevel) {
        super("jamesdbloom/mockserver:mockserver-5.4.1");
        this.mockServerPort = mockServerPort;
        withCommand("/opt/mockserver/run_mockserver.sh -logLevel " + logLevel + " -serverPort " + mockServerPort);
        withNetwork(network);
        withNetworkAliases("mockserver");
        waitingFor(new HostPortWaitStrategy());
        addExposedPorts(mockServerPort);
    }

    public MockServerClient getClient() {
        return client;
    }

    @Override
    protected void containerIsStarted(InspectContainerResponse containerInfo) {
        super.containerIsStarted(containerInfo);
        this.client = new MockServerClient(getContainerIpAddress(), getMappedPort(mockServerPort));
    }
}
