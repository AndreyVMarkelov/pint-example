package test.hb.billing.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.hb.billing.model.ChargeRequest;

@RestController
@RequestMapping(value="v1/charge")
public class ChargeController {
    @RequestMapping(method = RequestMethod.POST)
    public String charge(@RequestBody ChargeRequest chargeRequest) {
        return "OK";
    }
}
