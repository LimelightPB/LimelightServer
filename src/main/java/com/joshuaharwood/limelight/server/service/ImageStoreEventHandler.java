package com.joshuaharwood.limelight.server.service;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.content.commons.annotations.HandleAfterGetContent;
import org.springframework.content.commons.annotations.HandleAfterSetContent;
import org.springframework.content.commons.annotations.StoreEventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
@StoreEventHandler
public class ImageStoreEventHandler {
    private static final Logger logger =
            Logger.getLogger(ImageStoreEventHandler.class.getName());

    @HandleAfterSetContent
    private void afterSetImage(Image image) {
        image.setSetTime(LocalDateTime.now());

        logger.info(
                String.format("Image content PUT complete [ID: %d] [Size: %s]",
                        image.getId(),
                        DataUtilities.humanReadableByteCount(image.getContentLength())
                )
        );
    }

    @HandleAfterGetContent
    private void afterGetImage(Image image) {
        logger.info(
                String.format("Image content GET complete [ID: %d] [Size: %s]",
                        image.getId(),
                        DataUtilities.humanReadableByteCount(image.getContentLength())
                )
        );
    }

    @PostConstruct
    private void postConstruct() {
        logger.info("Initialised ImageStoreEventHandler service");
    }
}