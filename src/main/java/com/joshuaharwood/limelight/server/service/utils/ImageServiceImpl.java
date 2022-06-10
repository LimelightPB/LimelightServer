/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.utils;

import com.joshuaharwood.limelight.server.model.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Returns a count of JPA Image entities in the database, regardless of
     * whether content is associated with that entity.
     *
     * @return Count of Image entities
     */
    public Long getNumberOfImageEntities() {
        return imageRepository.count();
    }

    /**
     * Returns a count of JPA Image entities in the database, depending on
     * whether they have content associated with them or not.
     *
     * @param hasContent Whether counted entities should have associated content
     * @return Count of specified Image entities
     */
    @Override
    public Long getNumberOfImageEntities(boolean hasContent) {
        if (hasContent) {
            return imageRepository.countByContentIdIsNotNull();
        } else {
            return imageRepository.countByContentIdIsNull();
        }
    }

    /**
     * Returns a human-readable binary sum of the content lengths of each Image
     * entity in the database (/1024).
     *
     * @return Human-readable byte count as a String
     */
    @Override
    public String getSizeOfImageContent() {
        return DataUtilities.humanReadableByteCount(
                imageRepository.sumTotalImagesSize()
        );
    }

    public String getImageStats() {
        return String.format("[Total: %d] [With content: %d] [Without content: %d] [Total size: %s]",
                getNumberOfImageEntities(),
                getNumberOfImageEntities(true),
                getNumberOfImageEntities(false),
                getSizeOfImageContent());
    }
}
