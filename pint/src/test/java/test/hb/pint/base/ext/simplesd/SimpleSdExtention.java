package test.hb.pint.base.ext.simplesd;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import test.hb.pint.base.ext.NetworkHolder;

public class SimpleSdExtention implements BeforeAllCallback, AfterAllCallback {
    private SimpleSdContainer simpleSdContainer = new SimpleSdContainer(NetworkHolder.network(), 8761);

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        simpleSdContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        simpleSdContainer.start();
    }
}
