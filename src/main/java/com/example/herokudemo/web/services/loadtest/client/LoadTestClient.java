package com.example.herokudemo.web.services.loadtest.client;

import com.example.herokudemo.web.model.CommonMessageDTO;

public interface LoadTestClient {
    void setMessage(CommonMessageDTO commonMessageDTO);
    CommonMessageDTO getMessage();
    void send();
}
