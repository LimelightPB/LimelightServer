/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.logic;

import com.joshuaharwood.limelight.server.model.entities.Image;
import com.joshuaharwood.limelight.server.model.repositories.ImageRepository;
import com.joshuaharwood.limelight.server.model.stores.ImageStore;
import com.joshuaharwood.limelight.server.service.utils.DataUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageStore imageStore;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageStore imageStore) {
        this.imageRepository = imageRepository;
        this.imageStore = imageStore;
    }

    // Primitive CRUD method exposures
    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteImageContent(Long id) {
        if (imageExistsById(id)) {
            Image i = imageRepository.getReferenceById(id);
            imageStore.unsetContent(i);
        }
    }

    @Override
    public Image createImage() {
        return imageRepository.save(new Image());
    }

    // Derived data methods


    @Override
    public boolean imageExistsById(Long id) {
        return imageRepository.existsById(id);
    }

    @Override
    public boolean imageHasContent(Long id) {
        return imageRepository.existsByIdAndContentIdIsNotNull(id);
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

    // TODO: ContentLength is now 0L by default. Gotta convert the repo methods
    // from void/nonvoid...
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
    public String getHumanSizeOfImageContent() {
        return DataUtilities.humanReadableByteCount(
                getSizeOfImageContent()
        );
    }

    private Long getSizeOfImageContent() {
        return Objects.requireNonNullElse(imageRepository.sumTotalImagesSize(), 0L);
    }

    public String getImageStats() {
        return String.format("[Total: %d] [With content: %d] [Without content: %d] [Total size: %s]",
                getNumberOfImageEntities(),
                getNumberOfImageEntities(true),
                getNumberOfImageEntities(false),
                getHumanSizeOfImageContent());
    }
}