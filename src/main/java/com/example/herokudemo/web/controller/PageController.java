package com.example.herokudemo.web.controller;

import com.example.herokudemo.web.model.CommonMessageDTO;
import com.example.herokudemo.web.services.ProducerService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/page")
public class PageController {

    private final ProducerService producerService;

    private final Logger logger = Logger.getLogger(PublisherController.class.getName());

    public PageController(ProducerService producerService) {
        this.producerService = producerService;
    }

    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/latestMessage")
    public String getLatestMessage(Model model){
        Gson gson = new Gson();
        CommonMessageDTO message = producerService.getMessage();

        logger.info("Trying to access page.");
        logger.info("Data retrieved: "+ gson.toJson(message));

        model.addAttribute("message", message == null ? new CommonMessageDTO() : message);
        return "message";
    }
}
