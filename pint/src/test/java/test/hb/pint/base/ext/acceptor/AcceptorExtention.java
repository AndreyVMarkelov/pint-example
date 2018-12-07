package test.hb.pint.base.ext.acceptor;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import test.hb.pint.base.ext.NetworkHolder;

public class AcceptorExtention implements BeforeAllCallback, AfterAllCallback, ParameterResolver {
    private static AcceptorContainer acceptorContainer = new AcceptorContainer(NetworkHolder.network(), 8081);

    @Override
    public void afterAll(ExtensionContext context) {
        acceptorContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        acceptorContainer.start();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(AcceptorClient.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return new AcceptorClient("localhost", acceptorContainer.getFirstMappedPort());
    }
}
