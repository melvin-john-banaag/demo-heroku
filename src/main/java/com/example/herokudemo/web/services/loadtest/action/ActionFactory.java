package com.example.herokudemo.web.services.loadtest.action;

import com.example.herokudemo.web.model.loadtest.LoadTestingParameterDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class ActionFactory {

    public static final String START = "start";
    public static final String STOP = "stop";
    private final Map<String, Action> actionMap = new HashMap<String, Action>();
    private final Action startAction;
    private final Action stopAction;
    Logger logger = Logger.getLogger(ActionFactory.class.getName());

    public ActionFactory(Action startAction, Action stopAction) {
        this.startAction = startAction;
        this.stopAction = stopAction;
        actionMap.put(START, startAction);
        actionMap.put(STOP, stopAction);
    }

    public Action getInstance(String action, LoadTestingParameterDTO loadTestingParameterDTO) {
        logger.info("Creating action instance using: " + action);
        Action actionImpl = actionMap.get(action);
        if (actionImpl == null) {
            logger.info("Action key is invalid: " + action);
            return null;
        }
        actionImpl.setLoadTestingParameterDTO(loadTestingParameterDTO);
        return actionImpl;
    }
}
