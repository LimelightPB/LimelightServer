/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.rest;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RepositoryEventHandler
public class ImageRepositoryEventHandler {
    private final RestLogger restLogger;

    @Autowired
    public ImageRepositoryEventHandler(RestLogger restLogger) {
        this.restLogger = restLogger;
    }

    @HandleAfterCreate
    private void afterCreate(Image image) {
        restLogger.logLastRequest();
    }

//    No annotation for this. Will research later
//    private void afterGet(Image image) {
//        logger.info(String.format("Image entity GET complete [ID: %d]", image.getId()));
//    }

    @PostConstruct
    private void postConstruct() {
        restLogger.log("Initialised ImageRepositoryEventHandler service");
    }
}
