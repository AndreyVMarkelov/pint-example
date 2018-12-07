package test.hb.pint.base;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;
import test.hb.pint.base.ext.acceptor.Acceptor;
import test.hb.pint.base.ext.acceptor.AcceptorClient;
import test.hb.pint.base.ext.billing.Billing;
import test.hb.pint.base.ext.mock.MockServer;
import test.hb.pint.base.ext.redis.Redis;
import test.hb.pint.base.ext.route.Route;
import test.hb.pint.base.ext.simplesd.SimpleSd;

import static org.junit.Assert.assertTrue;

@MockServer @Redis @SimpleSd @Billing @Route @Acceptor
class SendMessageTest {
    @Test @DisplayName("Full circle test")
    void testSuccessSend(AcceptorClient aClient, MockServerClient mClient) {
        mClient.when(HttpRequest.request().withPath("/sendsms"))
                .respond(HttpResponse.response()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"messageId\": \"123\", \"state\": 1}")
                        .withStatusCode(200));
        String resp = aClient.send(
                "Hi, how are you?", "IB", "79817442839", "andrey");
        assertTrue(resp.contains("123"));
        mClient.verify(
                HttpRequest.request().withPath("/sendsms"),
                VerificationTimes.once());
    }
}
