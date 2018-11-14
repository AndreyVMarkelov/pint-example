package test.hb.pint.base.ext.mock;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.mockserver.client.MockServerClient;

import test.hb.pint.base.ext.NetworkHolder;

public class MockServerExtension implements BeforeAllCallback, AfterAllCallback, ParameterResolver {
    private static final MockServerContainer mockServerContainer = new MockServerContainer(NetworkHolder.network(), 1080, "WARN");

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        mockServerContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        mockServerContainer.start();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(MockServerClient.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return mockServerContainer.getClient();
    }
}
