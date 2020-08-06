package com.example.herokudemo.web.services.loadtest.action;

import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;
import com.example.herokudemo.web.services.loadtest.LoadTest;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class StopAction implements Action {

    private final Logger logger = Logger.getLogger(StopAction.class.getName());

    private final LoadTest loadTest;
    private LoadTestingParameterDTO loadTestingParameterDTO;

    public StopAction(LoadTest loadTest) {
        this.loadTest = loadTest;
    }

    @Override
    public void run() {
        logger.info("Initiating stop process.");
        loadTest.setLoadTestingParameterDTO(loadTestingParameterDTO);
        loadTest.stop();
    }

    @Override
    public void setLoadTestingParameterDTO(LoadTestingParameterDTO loadTestingParameterDTO) {
        this.loadTestingParameterDTO = loadTestingParameterDTO;
    }
}
