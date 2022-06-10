/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.shell;

import com.joshuaharwood.limelight.server.service.utils.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.annotation.PostConstruct;

@ShellComponent
public class ShellService {
    private final ImageService imageService;

    @Autowired
    public ShellService(ImageService imageService) {
        this.imageService = imageService;
    }

    @ShellMethod("Get statistics on image entities and content")
    public String imageStats() {
        return imageService.getImageStats();
    }

    @PostConstruct
    public void postConstruct() {

    }
}
