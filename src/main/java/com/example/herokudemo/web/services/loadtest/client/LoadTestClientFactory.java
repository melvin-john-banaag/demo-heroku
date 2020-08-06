package com.example.herokudemo.web.services.loadtest.client;

import com.example.herokudemo.web.model.CommonMessageDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class LoadTestClientFactory {

    private final Logger logger = Logger.getLogger(LoadTestClientFactory.class.getName());

    private final LoadTestClient customerOpt;
    private final LoadTestClient productOffer;
    private final LoadTestClient updateEmail;

    public LoadTestClientFactory(LoadTestClient customerOpt, LoadTestClient productOffer, LoadTestClient updateEmail) {
        this.customerOpt = customerOpt;
        this.productOffer = productOffer;
        this.updateEmail = updateEmail;
    }

    public LoadTestClient getInstance(String key, CommonMessageDTO commonMessageDTO){
        LoadTestClient client=null;

        for(ClientKey clientKey : ClientKey.values()){
            if(clientKey.toString().equalsIgnoreCase(key)) {
                client = this.createRunnableClientMapping().get(clientKey);
                break;
            }
        }
        if(client == null) {
            logger.info("Topic parameter key is invalid: " + key);
            return null;
        }

        client.setMessage(commonMessageDTO);
        return client;
    }

    private Map<ClientKey, LoadTestClient> createRunnableClientMapping(){
        logger.info("Creating runnable client instance map.");
        Map<ClientKey, LoadTestClient> runnableClients = new HashMap<ClientKey, LoadTestClient>(3);
        runnableClients.put(ClientKey.CUST_OPT, customerOpt);
        runnableClients.put(ClientKey.PROD_OFFER, productOffer);
        runnableClients.put(ClientKey.CUST_EMAIL, updateEmail);
        return runnableClients;
    }
}
