/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.logic;

import com.joshuaharwood.limelight.server.model.entities.Image;

/**
 * Implements Image-related business logic.
 */
public interface ImageService {
    Long getNumberOfImageEntities();
    Long getNumberOfImageEntities(boolean withContent);
    String getHumanSizeOfImageContent();
    String getImageStats();


    void deleteImage(Long id);
    void deleteImage(Image image);
    void deleteImageContent(Long id);
    void deleteImageContent(Image image);
}
