package com.example.herokudemo.web.client;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.google.gson.Gson;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public interface MulesoftClient {

    void sendCustomerUpdateEmail(CommonMessageDTO commonMessageDTO);

    void sendCustomerOpt(CommonMessageDTO commonMessageDTO);

    void sendProductOffer(CommonMessageDTO commonMessageDTO);

}
