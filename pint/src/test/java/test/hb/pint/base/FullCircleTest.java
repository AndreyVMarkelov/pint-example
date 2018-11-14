package test.hb.pint.base;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import test.hb.pint.base.ext.acceptor.Acceptor;
import test.hb.pint.base.ext.acceptor.AcceptorClient;
import test.hb.pint.base.ext.billing.Billing;
import test.hb.pint.base.ext.mock.MockServer;
import test.hb.pint.base.ext.redis.Redis;
import test.hb.pint.base.ext.route.Route;
import test.hb.pint.base.ext.simplesd.SimpleSd;

@MockServer
@Redis
@SimpleSd
@Billing
@Route
@Acceptor
class FullCircleTest {
    @Test @DisplayName("Full circle test")
    void testSuccessSend(AcceptorClient acceptorClient, MockServerClient client) throws Exception {
        client.when(HttpRequest.request().withPath("/sendsms"))
            .respond(HttpResponse.response()
                    .withHeader("content-type", "application/json")
                    .withBody("{\"messageId\": \"0123456789\", \"state\": 1}").withStatusCode(200));
        String resp = acceptorClient.send("Hi, how are you?", "Infobip", "79817442839", "andrey");
        assertTrue(resp.contains("0123456789"));
    }
}
