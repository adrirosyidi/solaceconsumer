package com.adri.solaceconsumer.config;

import com.solacesystems.jcsmp.JCSMPChannelProperties;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPProperties;
import com.solacesystems.jcsmp.SpringJCSMPFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Value("${solace.jcsmp.host}")
    private String host;

    @Value("${solace.jcsmp.msgVpn}")
    private String msgVpn;

    @Value("${solace.jcsmp.clientUsername}")
    private String clientUsername;

    @Value("${solace.jcsmp.clientPassword}")
    private String clientPassword;

    @Bean("springJCSMPFactory")
    public SpringJCSMPFactory springJCSMPFactory() throws JCSMPException {
        JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, host);     // host:port
        properties.setProperty(JCSMPProperties.USERNAME, clientUsername); // client-username
        properties.setProperty(JCSMPProperties.VPN_NAME,  msgVpn); // message-vpn
        properties.setProperty(JCSMPProperties.PASSWORD, clientPassword); // client-password
        // Channel Properties
        JCSMPChannelProperties cp = (JCSMPChannelProperties) properties
                .getProperty(JCSMPProperties.CLIENT_CHANNEL_PROPERTIES);

        SpringJCSMPFactory jcsmpFactory = new SpringJCSMPFactory(properties);
        return jcsmpFactory;
    }
}
