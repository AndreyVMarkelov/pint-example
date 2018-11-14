package test.hb.pint.base.ext.route;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import test.hb.pint.base.ext.NetworkHolder;

public class RouteExtention implements BeforeAllCallback, AfterAllCallback {
    private RouteContainer routeContainer = new RouteContainer(NetworkHolder.network(), 8083);

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        routeContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        routeContainer.start();
    }
}
