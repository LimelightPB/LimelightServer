/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server;

import com.joshuaharwood.limelight.server.service.utils.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@SpringBootApplication
public class LimelightServerApplication {
    private static final Logger logger = Logger.getLogger(LimelightServerApplication.class.getName());
    private final ImageService imageService;

    @Autowired
    public LimelightServerApplication(ImageService imageService) {
        this.imageService = imageService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LimelightServerApplication.class, args);
    }

    /**
     * Logic run on startup.
     */
    @PostConstruct
    public void postConstruct() {
        logger.info(imageService.getImageStats());
    }
}