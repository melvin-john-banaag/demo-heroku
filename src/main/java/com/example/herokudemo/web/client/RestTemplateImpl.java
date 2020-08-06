package com.example.herokudemo.web.client;

import com.example.herokudemo.web.model.CommonMessageDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.logging.Logger;

@Component
@ConfigurationProperties("heroku.demo")
public class RestTemplateImpl implements MulesoftClient{

    private final Logger logger = Logger.getLogger(MulesoftClient.class.getName());

    private final String CUST_UPDATE_PATH = "/api/cust_update_email";
    private final String CUST_OPT_PATH = "/api/CUST_optOut_optIn";
    private final String PROD_OFFER_PATH = "/api/prod_offer";

    private String apiHost;

    private final RestTemplate restTemplate;

    public RestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendCustomerUpdateEmail(CommonMessageDTO commonMessageDTO){
        String generateUrl = apiHost.concat(CUST_UPDATE_PATH);
        this.forwardMessagePost(generateUrl, commonMessageDTO);
    }

    public void sendCustomerOpt(CommonMessageDTO commonMessageDTO){
        String generateUrl = apiHost.concat(CUST_OPT_PATH);
        this.forwardMessagePost(generateUrl, commonMessageDTO);
    }

    public void sendProductOffer(CommonMessageDTO commonMessageDTO){
        String generateUrl = apiHost.concat(PROD_OFFER_PATH);
        this.forwardMessagePost(generateUrl, commonMessageDTO);
    }

    private void forwardMessagePost(String url, CommonMessageDTO message){
        logger.info("Forwarding to URL: " + url);
        message.getMessage().setPublishTime(new Date().getTime());
        this.restTemplate.postForLocation(url, message);
        logger.info("Done sending to URL: " + url);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
