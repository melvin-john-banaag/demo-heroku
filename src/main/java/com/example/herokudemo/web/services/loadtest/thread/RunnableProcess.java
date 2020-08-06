package com.example.herokudemo.web.services.loadtest.thread;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.services.loadtest.client.LoadTestClient;

import java.util.logging.Logger;

public class RunnableProcess implements Runnable{

    private final Logger logger = Logger.getLogger(RunnableProcess.class.getName());

    private final LoadTestClient loadTestClient;
    private final int callsPerThread;
    private String threadName;

    public RunnableProcess(LoadTestClient loadTestClient, int callsPerThread){
        this.loadTestClient=loadTestClient;
        this.callsPerThread=callsPerThread;
    }

    @Override
    public void run() {
        logger.info("Change message description according to thread name.");
        CommonMessageDTO message = this.loadTestClient.getMessage();
        message.setDesc("This message is executed by thread: "+threadName);
        this.loadTestClient.setMessage(message);

        logger.info("Number of calls per thread: "+callsPerThread);
        for(int i=0;i<callsPerThread;i++) {
            this.loadTestClient.send();
        }
    }
}
