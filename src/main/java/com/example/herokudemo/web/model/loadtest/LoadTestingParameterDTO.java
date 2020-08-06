package com.example.herokudemo.web.model.loadtest;

import com.example.herokudemo.web.model.CommonMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class LoadTestingParameterDTO {
    private String action;
    private String topic;
    private int numberOfThread;
    private int totalCallPerThread;
    private int interval;
    private CommonMessageDTO commonMessageDTO;

}
