/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.logic;

import com.joshuaharwood.limelight.server.LimelightServerApplication;
import com.joshuaharwood.limelight.server.model.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class StartupService {
    private static final Logger logger = Logger.getLogger(StartupService.class.getName());
    private final ImageService imageService;

    @Autowired
    public StartupService(ImageService imageService) {
        this.imageService = imageService;
    }

    // TODO: Why doesn't ApplicationReadyEvent fire? This'll do for now...
    @EventListener(ContextRefreshedEvent.class)
    public void doSomethingAfterStartup() {
        logger.info("Image stats: " + imageService.getImageStats());
    }
}
