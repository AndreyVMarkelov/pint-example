package test.hb.route.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import test.hb.route.model.ProviderResponse;
import test.hb.route.model.RouteRequest;
import test.hb.route.model.RouteResponse;

@RestController
@RequestMapping(value="v1/route/send")
public class RouteController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${providerUrl}")
    private String providerUrl;

    @RequestMapping(method = RequestMethod.POST)
    public RouteResponse check(@RequestBody RouteRequest routeRequest) {
        ResponseEntity<ProviderResponse> responseEntity = restTemplate.getForEntity(
                providerUrl + "?text= {text}&sender= {sender}&desctination= {desctination}",
                ProviderResponse.class,
                routeRequest.getText(),
                routeRequest.getSender(),
                routeRequest.getDesctination());

        RouteResponse routeResponse = new RouteResponse(responseEntity.getBody().getMessageId(), responseEntity.getBody().getState());
        return routeResponse;
    }
}
