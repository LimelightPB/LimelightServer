/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.logic;

import com.joshuaharwood.limelight.server.model.entities.Image;

import java.util.List;

/**
 * Implements Image-related business logic.
 */
public interface ImageService {
    Long getNumberOfImageEntities();

    Long getNumberOfImageEntities(boolean withContent);

    String getHumanSizeOfImageContent();

    boolean imageHasContent(Image i);

    boolean imageHasContent(Long id);

    String getImageStats();

    Image createImage();

    List<Image> createImages(Long num);

    boolean imageExists(Long id);

    void deleteImage(Long id);

    void deleteImage(Image image);

    void deleteAllImages();

    void deleteImageContent(Long id);

    void deleteImageContent(Image image);
}
