package com.adri.solaceconsumer.controller;

import com.adri.solaceconsumer.model.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerConsumer {

    private static Logger logger = LoggerFactory.getLogger(ControllerConsumer.class);

    public MessageModel messageModel;

    @RequestMapping("/getMsg")
    public void getMsg(){
        logger.info("Message From Controller : "+ messageModel.getSubsMsg().get(messageModel.getSubsMsg().size() - 1));
        messageModel.clearSubsMsg();
    }
}
