package test.hb.pint.base.ext.billing;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import test.hb.pint.base.ext.NetworkHolder;

public class BillingExtention implements BeforeAllCallback, AfterAllCallback {
    private BillingContainer billingContainer = new BillingContainer(NetworkHolder.network(), 8082);

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        billingContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        billingContainer.start();
    }
}
