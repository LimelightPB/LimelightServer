/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.shell;

import com.joshuaharwood.limelight.server.service.logic.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@ShellComponent
public class ShellHandler {
    private static final Logger logger = Logger.getLogger(ShellHandler.class.getName());
    private final ImageService imageService;

    @Autowired
    public ShellHandler(ImageService imageService) {
        this.imageService = imageService;
    }

    @ShellMethod("Get statistics on image entities and content")
    public String imageStats() {
        return imageService.getImageStats();
    }

    @ShellMethod("Delete an Image entity from the database")
    public String deleteImageById(Long id) {
        imageService.deleteImage(id);
        return imageService.getImageStats();
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("Initialised ShellHandler service");
    }
}
