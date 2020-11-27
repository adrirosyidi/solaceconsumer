package com.adri.solaceconsumer.services;

import com.adri.solaceconsumer.model.MessageModel;
import com.solacesystems.jcsmp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);
//    private MessageModel messageModel;

    @Autowired
    private SpringJCSMPFactory solaceFactory;

    @Value("${queue.jcsmp.destination.target}")
    private String queueName;

    @Value("${queue.endpoint.properties.accesstype}")
    private int accessType;

    public void retrieveMsg() throws JCSMPException {
//        logger.info("TopicSubscriber initializing...");
        final Queue queue = JCSMPFactory.onlyInstance().createQueue(queueName);
        final JCSMPSession session = solaceFactory.createSession();

        session.connect();

        // Create a Flow be able to bind to and consume messages from the Queue.
        final ConsumerFlowProperties flow_prop = new ConsumerFlowProperties();
        flow_prop.setEndpoint(queue);
        flow_prop.setAckMode(JCSMPProperties.SUPPORTED_MESSAGE_ACK_CLIENT);

        EndpointProperties endpoint_props = new EndpointProperties();
        endpoint_props.setAccessType(accessType);

        final FlowReceiver cons = session.createFlow(new XMLMessageListener() {
            @Override
            public void onReceive(BytesXMLMessage msg) {
                if (msg instanceof TextMessage) {
//                    logger.info("Message : " + ((TextMessage) msg).getText());
//                    messageModel.setSubsMsg(msg);
//                    logger.info("Message : "+ messageModel.getSubsMsg().get(messageModel.getSubsMsg().size() - 1));
                } else {
//                    logger.info("Message received.");
                }
//                logger.info("Message Dump:\n" + msg.dump() + "\n");

                // When the ack mode is set to SUPPORTED_MESSAGE_ACK_CLIENT,
                // guaranteed delivery messages are acknowledged after
                // processing
                msg.ackMessage();
            }

            @Override
            public void onException(JCSMPException e) {

//                logger.info("Consumer received exception: "+ e);
            }
        }, flow_prop, endpoint_props);

        // Start the consumer
//        logger.info("Connected. Awaiting message ...");
        cons.start();

//        // Close consumer
//        cons.close();
//        logger.info("Exiting.");
//        session.closeSession();
    }
}
