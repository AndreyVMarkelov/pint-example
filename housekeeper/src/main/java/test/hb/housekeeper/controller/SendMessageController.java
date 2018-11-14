package test.hb.housekeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.hb.housekeeper.client.BillingClient;
import test.hb.housekeeper.client.RouteClient;
import test.hb.housekeeper.model.RouteResponse;
import test.hb.housekeeper.model.SmsRequest;
import test.hb.housekeeper.model.SmsResponse;

@RestController
@RequestMapping(value="v1/sms")
public class SendMessageController {
    @Autowired
    private BillingClient billingClient;
    @Autowired
    private RouteClient routeClient;

    @RequestMapping(value="send",method = RequestMethod.POST)
    public SmsResponse send(@RequestBody SmsRequest smsRequest) {
        boolean canSend = billingClient.checkBalance(smsRequest.getUserId());
        if (canSend) {
            RouteResponse routeResponse = routeClient.route(smsRequest.getText(), smsRequest.getSender(), smsRequest.getDesctination());
            return new SmsResponse(routeResponse.getMessageId(), routeResponse.getState());
        } else {
            return new SmsResponse(null, 3);
        }
    }
}
