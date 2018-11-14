package test.hb.route.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.hb.route.model.RouteRequest;
import test.hb.route.model.RouteResponse;

@RestController
@RequestMapping(value="v1/route/dn")
public class DnController {
    @RequestMapping(method = RequestMethod.POST)
    public RouteResponse check(@RequestBody RouteRequest routeRequest) {
        RouteResponse routeResponse = new RouteResponse("1234545", 0);
        return routeResponse;
    }
}
