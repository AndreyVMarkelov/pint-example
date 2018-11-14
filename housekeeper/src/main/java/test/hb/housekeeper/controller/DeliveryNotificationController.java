package test.hb.housekeeper.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.hb.housekeeper.model.DnRequest;

@RestController
@RequestMapping(value="v1/dn")
public class DeliveryNotificationController {
    @RequestMapping(value="accept",method = RequestMethod.POST)
    public String accept(@RequestBody DnRequest dnRequest) {
        return "Done";
    }
}
