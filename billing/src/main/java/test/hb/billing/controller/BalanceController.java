package test.hb.billing.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.hb.billing.model.BalanceRequest;

@RestController
@RequestMapping(value="v1/balance")
public class BalanceController {
    @RequestMapping(value="check",method = RequestMethod.POST)
    public boolean check(@RequestBody BalanceRequest balanceRequest) {
        return true;
    }
}
