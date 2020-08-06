package com.example.herokudemo.web.services;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.model.MessageDTO;
import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;
import com.example.herokudemo.web.services.loadtest.action.ActionFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final Logger logger = Logger.getLogger(ProducerServiceImpl.class.getName());

    private final ServletContext servletContext;
    private final String CONTEXT_KEY = "LATEST_MESSAGE";

    private final ActionFactory actionFactory;


    public ProducerServiceImpl(ServletContext servletContext, ActionFactory actionFactory) {

        this.servletContext = servletContext;
        this.actionFactory = actionFactory;
    }

    @Override
    public CommonMessageDTO getSampleCommonMessage() {
        return CommonMessageDTO.builder()
                .topic("testTopic")
                .desc("testDesc")
                .message(MessageDTO.builder()
                        .brand("testBrand")
                        .email("testEmail")
                        .mobile(98989898)
                        .hkId(UUID.randomUUID().toString()).build()).build();
    }

    @Override
    public void saveMesage(CommonMessageDTO message) {
        this.servletContext.setAttribute(CONTEXT_KEY, message);
    }

    @Override
    public CommonMessageDTO getMessage() {
        return (CommonMessageDTO) this.servletContext.getAttribute(CONTEXT_KEY);
    }

    @Override
    public void startLoadTest(LoadTestingParameterDTO loadTestingParameterDTO) {
        actionFactory.getInstance(
                loadTestingParameterDTO.getAction(),
                loadTestingParameterDTO).run();
    }

}
