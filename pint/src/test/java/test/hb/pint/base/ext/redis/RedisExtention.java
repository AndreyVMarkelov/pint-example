package test.hb.pint.base.ext.redis;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import test.hb.pint.base.ext.NetworkHolder;

public class RedisExtention implements BeforeAllCallback, AfterAllCallback {
    private RedisContainer redisContainer = new RedisContainer(NetworkHolder.network(), 6379);

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        redisContainer.stop();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        redisContainer.start();
    }
}
