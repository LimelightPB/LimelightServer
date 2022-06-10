/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.rest;

import com.joshuaharwood.limelight.server.model.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class ImageController {
    private final ImageRepository imageRepository;
    private final RestLogger restLogger;

    @Autowired
    public ImageController(ImageRepository imageRepository, RestLogger restLogger) {
        this.imageRepository = imageRepository;
        this.restLogger = restLogger;
    }

    @RequestMapping(value = "/images/count")
    private Long getNumberOfUsers() {
        restLogger.logLastRequest();
        return imageRepository.count();
    }

    @RequestMapping("/images/size")
    private Long getTotalSizeOfImages() {
        restLogger.logLastRequest();
        return imageRepository.sumTotalImagesSize();
    }

    @PostConstruct
    private void postConstruct() {
        restLogger.log("Initialised ImageController service");

    }
}
