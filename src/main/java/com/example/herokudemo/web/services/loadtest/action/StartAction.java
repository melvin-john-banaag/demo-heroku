package com.example.herokudemo.web.services.loadtest.action;

import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;
import com.example.herokudemo.web.services.loadtest.LoadTest;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class StartAction implements Action {

    private final Logger logger = Logger.getLogger(StartAction.class.getName());

    private final LoadTest loadTest;
    private LoadTestingParameterDTO loadTestingParameterDTO;

    public StartAction(LoadTest loadtest){
        this.loadTest = loadtest;
    }

    @Override
    public void run() {
        logger.info("Initiating start process.");
        this.loadTest.setLoadTestingParameterDTO(this.loadTestingParameterDTO);
        this.loadTest.start();
    }

    @Override
    public void setLoadTestingParameterDTO(LoadTestingParameterDTO loadTestingParameterDTO) {
        this.loadTestingParameterDTO = loadTestingParameterDTO;
    }
}
