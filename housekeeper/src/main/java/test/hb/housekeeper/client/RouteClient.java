package test.hb.housekeeper.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import test.hb.housekeeper.model.RouteRequest;
import test.hb.housekeeper.model.RouteResponse;

@Component
public class RouteClient {
    @Autowired
    private RestTemplate restTemplate;

    public RouteResponse route(String text, String sender, String desctination) {
        RouteRequest routeRequest = new RouteRequest();
        routeRequest.setText(text);
        routeRequest.setSender(sender);
        routeRequest.setDesctination(desctination);

        ResponseEntity<RouteResponse> response = restTemplate.postForEntity(
                "http://route/v1/route/send",
                routeRequest,
                RouteResponse.class);
        return response.getBody();
    }
}
