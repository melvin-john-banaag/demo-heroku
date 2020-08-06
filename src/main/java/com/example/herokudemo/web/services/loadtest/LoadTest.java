package com.example.herokudemo.web.services.loadtest;

import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;
import com.example.herokudemo.web.services.loadtest.client.LoadTestClient;
import com.example.herokudemo.web.services.loadtest.client.LoadTestClientFactory;
import com.example.herokudemo.web.services.loadtest.thread.RunnableProcess;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class LoadTest {

    private final Logger logger = Logger.getLogger(LoadTest.class.getName());
    private final LoadTestClientFactory loadTestClientFactory;
    private LoadTestingParameterDTO loadTestingParameterDTO;

    private List<Thread> runningThreads;

    public LoadTest(
            LoadTestClientFactory loadTestClientFactory){
        this.loadTestClientFactory = loadTestClientFactory;
    }

    public void start(){
        logger.info("Get endpoint client.");
        LoadTestClient client = this.loadTestClientFactory.getInstance(
                this.loadTestingParameterDTO.getTopic(),
                loadTestingParameterDTO.getCommonMessageDTO());
        RunnableProcess runnable = new RunnableProcess(client, loadTestingParameterDTO.getTotalCallPerThread());
        if (runningThreads ==null)
            this.createAndRunThreads(runnable);
        else {
            logger.info("Threads are already running.");
            this.stop();
            this.createAndRunThreads(runnable);
        }

    }

    private void createAndRunThreads(RunnableProcess runnable){
        int numOfThread = loadTestingParameterDTO.getNumberOfThread();
        int interval = loadTestingParameterDTO.getInterval();

        runningThreads = new ArrayList<Thread>(numOfThread);
        logger.info("Number of threads to be created: "+numOfThread);
        for(int i=0;i<numOfThread;i++){
            String threadName = "LoadTestThread-"+i;

            logger.info("Creating thread: "+threadName);
            Thread thread = new Thread(runnable, threadName);
            runningThreads.add(thread);
            thread.start();

            logger.info("seconds per call: "+interval);
            try {
                Thread.sleep(interval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void stop(){
        if (runningThreads != null && !runningThreads.isEmpty()) {
            logger.info("Currently running threads: "+runningThreads.size());
            logger.info("Stopping currently running threads.");
            for (Thread runningThread : runningThreads) {
                runningThread.interrupt();
            }
            runningThreads = null;
        }else{
            logger.info("No threads currently running.");
        }
    }

    public void setLoadTestingParameterDTO(LoadTestingParameterDTO loadTestingParameterDTO) {
        this.loadTestingParameterDTO = loadTestingParameterDTO;
    }
}
