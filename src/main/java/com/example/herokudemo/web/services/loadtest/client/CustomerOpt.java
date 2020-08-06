package com.example.herokudemo.web.services.loadtest.client;

import com.example.herokudemo.web.client.MulesoftClient;
import com.example.herokudemo.web.model.CommonMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerOpt implements LoadTestClient {

    private CommonMessageDTO commonMessageDTO;
    private final MulesoftClient restTemplateImpl;

    public CustomerOpt(MulesoftClient restTemplateImpl) {
        this.restTemplateImpl = restTemplateImpl;
    }


    @Override
    public void setMessage(CommonMessageDTO commonMessageDTO) {
        this.commonMessageDTO = commonMessageDTO;
    }
    public CommonMessageDTO getMessage(){ return this.commonMessageDTO;}
    @Override
    public void send() {
        this.restTemplateImpl.sendCustomerOpt(this.commonMessageDTO);
    }
}
