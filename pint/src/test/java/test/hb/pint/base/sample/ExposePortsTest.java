package test.hb.pint.base.sample;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

import java.io.OutputStream;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExposePortsTest {
    private static HttpServer server;

    @SuppressWarnings("restriction")
	@BeforeAll
    public static void setUpClass() throws Exception {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/", exchange -> {
            byte[] content = "Hello World!".getBytes();
            exchange.sendResponseHeaders(200, content.length);
            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(content);
                responseBody.flush();
            }
        });

        server.start();
        Testcontainers.exposeHostPorts(server.getAddress().getPort());
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
        server.stop(0);
    }

    @Test
    public void testExposedHost() throws Exception {
        assertResponse(new GenericContainer().withCommand("top"));
    }

    @Test
    public void testExposedHostWithNetwork() throws Exception {
        try (Network network = Network.newNetwork()) {
            assertResponse(new GenericContainer().withNetwork(network).withCommand("top"));
        }
    }

    protected void assertResponse(GenericContainer container) throws Exception {
        try {
            container.start();
            String response = container.execInContainer("wget", "-O", "-", "http://host.testcontainers.internal:" + server.getAddress().getPort()).getStdout();
            assertEquals(response, "Hello World!", "received response");
        } finally {
            container.stop();
        }
    }
}
