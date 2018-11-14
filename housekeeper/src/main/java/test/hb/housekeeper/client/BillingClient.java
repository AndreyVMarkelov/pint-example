package test.hb.housekeeper.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import test.hb.housekeeper.model.BalanceRequest;

@Component
public class BillingClient {
    @Autowired
    private RestTemplate restTemplate;

    public boolean checkBalance(String userId){
        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setUserId(userId);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(
                "http://billing/v1/balance/check",
                balanceRequest,
                Boolean.class);
        return response.getBody();
    }
}
