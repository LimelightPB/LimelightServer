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

    public Long getNumberOfImages() {
        return imageRepository.count();
    }

    @Override
    public String getSizeOfImages() {
        return DataUtilities.humanReadableByteCount(imageRepository.totalImageSizeBytes());
    }
}
