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
    public String deleteImage(Long id) {
        if (imageService.imageExistsById(id)) {
            imageService.deleteImage(id);
            return String.format("Deleted image id %d", id);
        } else {
            return String.format("Image not found for id %d", id);
        }
    }

    @ShellMethod("Delete an Image entity's associated content")
    public String deleteImageContent(Long id) {
        if (imageService.imageExistsById(id)) {
            imageService.deleteImageContent(id);
            return String.format("Deleted image content for id %d", id);
        } else {
            return String.format("Image not found for id %d", id);
        }
    }

    @ShellMethod("Create an image entity")
    public String createImage() {
        return String.format("Created image with id %d",
                imageService.createImage().getId());
    }

    @ShellMethod("Create multiple image entities")
    public String createImages(Long num) {
        for (int i = 0; i < num; i++) {
            imageService.createImage();
        }

        return String.format("Created %d image(s)", num);
    }

    @ShellMethod("Check if image has content")
    public String checkIfHasContent(Long id) {
        if (imageService.imageExistsById(id)) {
            return String.valueOf(imageService.imageHasContent(id));
        } else {
            return String.format("Image not found for id %d", id);
        }
    }

    @PostConstruct
    public void postConstruct() {
        logger.info("Initialised ShellHandler service");
    }
}
