package com.joshuaharwood.limelight.server.service;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Service
@RepositoryEventHandler
public class ImageRepositoryEventHandler {
    private static final Logger logger = Logger.getLogger(ImageRepositoryEventHandler.class.getName());

    @HandleAfterCreate
    private void afterCreate(Image image) {
        logger.info(String.format("Image SET complete [ID: %d]", image.getId()));
    }

    @PostConstruct
    private void postConstruct() {
        logger.info("Initialised ImageRepositoryEventHandler service");
    }
}
