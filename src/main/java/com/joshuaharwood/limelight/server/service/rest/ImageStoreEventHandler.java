package com.joshuaharwood.limelight.server.service.rest;

import com.joshuaharwood.limelight.server.model.entities.Image;
import com.joshuaharwood.limelight.server.service.DataUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.content.commons.annotations.HandleAfterGetContent;
import org.springframework.content.commons.annotations.HandleAfterSetContent;
import org.springframework.content.commons.annotations.StoreEventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@StoreEventHandler
public class ImageStoreEventHandler {
    private final RestLogger restLogger;

    @Autowired
    public ImageStoreEventHandler(RestLogger restLogger) {
        this.restLogger = restLogger;
    }

    @HandleAfterSetContent
    private void afterSetImage(Image image) {
        image.setSetTime(LocalDateTime.now());
        restLogger.logLastRequest();
    }

    // TODO: Not firing. Why?
    @HandleAfterGetContent
    private void afterGetImage(Image image) {
        restLogger.logLastRequest();
    }

    @PostConstruct
    private void postConstruct() {
        restLogger.log("Initialised ImageStoreEventHandler service");
    }
}