package com.example.herokudemo.web.controller.loadtest;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;
import com.example.herokudemo.web.services.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = Logger.getLogger(TestController.class.getName());
    private final ProducerService producerService;

    public TestController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/sample")
    public ResponseEntity<CommonMessageDTO> testMessage(){
        return new ResponseEntity<CommonMessageDTO>(this.producerService.getSampleCommonMessage(), HttpStatus.OK);
    }

    /**
     * https://herokuxxx/xxx/loop?action=start&topic=CUST_optIn_optOut&numberOfThread=10&totalCallPerThread=1000&interval=1
     * Start a loop on topic CUST_optIn_optOut with 10 threads, calling every 1 second, and call 1000 times per thread.
     *
     * For the message, please add a sequence number to any one field for reference.
     */
    @GetMapping("/loadTest")
    public ResponseEntity<CommonMessageDTO> loadTesting(
            @RequestParam String action,
            @RequestParam String topic,
            @RequestParam int numberOfThread,
            @RequestParam int totalCallPerThread,
            @RequestParam int interval,
            @RequestBody CommonMessageDTO commonMessageDTO
    ){
        logger.info("Action: "+action);
        logger.info("Topic: "+topic);
        logger.info("Number of Thread: "+numberOfThread);
        logger.info("Total call per Thread: "+totalCallPerThread);
        logger.info("Interval: "+interval);
        logger.info("Request Body: "+commonMessageDTO);

        try {
            this.producerService.startLoadTest(LoadTestingParameterDTO.builder()
                    .action(action)
                    .topic(topic)
                    .numberOfThread(numberOfThread)
                    .totalCallPerThread(totalCallPerThread)
                    .interval(interval)
                    .commonMessageDTO(commonMessageDTO)
                    .build());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<CommonMessageDTO>(HttpStatus.OK);
    }

}
