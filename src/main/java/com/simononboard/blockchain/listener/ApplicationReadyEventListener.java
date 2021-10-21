package com.simononboard.blockchain.listener;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.security.Security;

@Component
public class ApplicationReadyEventListener {
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Security.addProvider(new BouncyCastleProvider());
    }
}