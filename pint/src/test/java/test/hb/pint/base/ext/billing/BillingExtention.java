package test.hb.pint.base.ext.billing;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.testcontainers.containers.Network;

import test.hb.pint.base.ext.NetworkHolder;

public class BillingExtention implements BeforeAllCallback, AfterAllCallback {
    private static final String NETWORK = "networkExtension";

    private BillingContainer billingContainer = new BillingContainer(NetworkHolder.network(), 8082);

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        billingContainer.setNetwork(getOrCreateNetwork(context));
        billingContainer.withNetworkAliases("billing");
        billingContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        billingContainer.start();
    }

    public static Network getOrCreateNetwork(ExtensionContext context) {
        Store store = context.getStore(
            ExtensionContext.Namespace.create(Network.class, context.getUniqueId())
        );
        return Network.class.cast(store.getOrComputeIfAbsent(NETWORK, k -> Network.newNetwork()));
    }
}
