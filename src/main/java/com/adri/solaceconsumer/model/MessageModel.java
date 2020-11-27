package com.adri.solaceconsumer.model;

import com.solacesystems.jcsmp.BytesXMLMessage;

import java.util.List;

public class MessageModel {
    private List<BytesXMLMessage> messageList;

    public void setSubsMsg(BytesXMLMessage msg){
        messageList.add(msg);
    }
    public List<BytesXMLMessage>getSubsMsg(){
        return messageList;
    }

    public void clearSubsMsg(){
        messageList.clear();
    }
}
