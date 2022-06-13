/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.rest;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.content.commons.annotations.HandleAfterGetContent;
import org.springframework.content.commons.annotations.HandleAfterSetContent;
import org.springframework.content.commons.annotations.HandleAfterUnsetContent;
import org.springframework.content.commons.annotations.StoreEventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

//TODO: Reevaluate RestLogger...

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

    @HandleAfterUnsetContent
    private void afterDeleteContent(Image image) {
        image.setSetTime(null);
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