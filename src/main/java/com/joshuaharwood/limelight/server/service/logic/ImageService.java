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

    boolean imageExistsById(Long id);

    boolean imageHasContent(Long id);

    void deleteImage(Long id);
    void deleteImageContent(Long id);

    Image createImage();
}
