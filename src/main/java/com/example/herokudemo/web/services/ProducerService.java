package com.example.herokudemo.web.services;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;

public interface ProducerService {

    CommonMessageDTO getSampleCommonMessage();
    void saveMesage(CommonMessageDTO message);
    CommonMessageDTO getMessage();
    void startLoadTest(LoadTestingParameterDTO loadTestingParameterDTO) throws InterruptedException;
}
