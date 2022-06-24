/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.shell;

import com.joshuaharwood.limelight.server.service.logic.ContentNotFoundException;
import com.joshuaharwood.limelight.server.service.logic.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.logging.Logger;

@ShellComponent
public class ShellHandler {
    private static final Logger logger = Logger.getLogger(ShellHandler.class.getName());
    private final ImageService imageService;

    @Autowired
    public ShellHandler(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Returns pretty-printed statistics on image entities and content within
     * the database
     * @return Pretty-printed statistics string
     */
    @ShellMethod("Get statistics on image entities and content")
    public String imageStats() {
        return imageService.getImageStats();
    }

    /**
     * Deletes an image entity from the database, along with its content.
     * @param id ID of image entity to delete
     * @return Success/failure string
     */
    @ShellMethod("Delete an Image entity from the database")
    public String deleteImage(Long id) {
            try {
                imageService.deleteImage(id);
                return String.format("Image id %d deleted", id);
            } catch (EntityNotFoundException e) {
                return String.format("Image id %d not found", id);
            }
    }

    @ShellMethod("Delete all images in the database. Use with caution!" +
            " If you are sure, add 'confirm' as a parameter")
    public String deleteAllImages(String confirm) {
        if (confirm.equals("confirm")) {
            imageService.deleteAllImages();
            return imageService.getImageStats();
        } else {
            return "Incorrect confirmation parameter";
        }
    }

    @ShellMethod("Delete an Image entity's associated content")
    public String deleteImageContent(Long id) {
//        if (imageService.imageExists(id)) {
//            imageService.deleteImageContent(id);
//            return String.format("Deleted image content for id %d", id);
//        } else {
//            return String.format("Image not found for id %d", id);
//        }

        try {
            imageService.deleteImageContent(id);
            return String.format("Deleted image content for id %d", id);
        } catch (EntityNotFoundException e) {
            return String.format("Image not found for id %d", id);
        } catch (ContentNotFoundException e) {
            return String.format("Image found but no content found for id %d", id);
        }
    }

    /**
     * Creates a single images with no content.
     * @return Success/failure string
     */
    @ShellMethod("Create an image entity")
    public String createImage() {
        return String.format("Created image with id %d",
                imageService.createImage().getId());
    }

    /**
     * Creates n number of images with no content.
     * @param num Number of images to create
     * @return Success/failure string
     */
    @ShellMethod("Create multiple image entities")
    public String createImages(Long num) {
        try {
            imageService.createImages(num);
            return String.format("Created %d image(s)", num);
        } catch (IllegalArgumentException e) {
            return String.format("Invalid argument %d", num);
        }
    }

    @ShellMethod("Check if image has content")
    public String checkIfHasContent(Long id) {
        if (imageService.imageExists(id)) {
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
