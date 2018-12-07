package test.hb.pint.base.ext.acceptor;

import org.testcontainers.shaded.org.apache.http.client.methods.CloseableHttpResponse;
import org.testcontainers.shaded.org.apache.http.client.methods.HttpPost;
import org.testcontainers.shaded.org.apache.http.entity.StringEntity;
import org.testcontainers.shaded.org.apache.http.impl.client.CloseableHttpClient;
import org.testcontainers.shaded.org.apache.http.impl.client.HttpClientBuilder;
import org.testcontainers.shaded.org.apache.http.util.EntityUtils;

public class AcceptorClient {
    private final String defaultHost;
    private final int defaultPort;
    private final CloseableHttpClient httpClient;

    public AcceptorClient(String defaultHost, int defaultPort) {
        this.defaultHost = defaultHost;
        this.defaultPort = defaultPort;
        this.httpClient = HttpClientBuilder.create().build();
    }

    public String send(
            String text,
            String sender,
            String desctination,
            String userId) {
        HttpPost httpPost = new HttpPost("http://" + defaultHost + ":" + defaultPort + "/v1/sms/send");

        String json = "{" + 
                "\"text\": \"" + text + "\"," + 
                "\"sender\": \"" + sender + "\"," + 
                "\"desctination\": \"" + desctination + "\"," + 
                "\"userId\": \"" + userId + "\"" + 
                "}\n";

        try {
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            throw new RuntimeException("Error send message to acceptor service", ex);
        }
    }
}
