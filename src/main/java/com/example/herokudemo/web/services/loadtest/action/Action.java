package com.example.herokudemo.web.services.loadtest.action;

import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;

public interface Action {
    void run();
    void setLoadTestingParameterDTO(LoadTestingParameterDTO loadTestingParameterDTO);
}
