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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageStore imageStore;

    /**
     * Constructor. Wires in our dependencies
     *
     * @param imageRepository Image entity Repository
     * @param imageStore      Image content Store
     */
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageStore imageStore) {
        this.imageRepository = imageRepository;
        this.imageStore = imageStore;
    }

    /**
     * Returns an Image entity by its ID, or throws an exception if not found
     * @param id ID of Image to return
     * @return Managed Image entity
     * @throws EntityNotFoundException Thrown if entity for ID not found
     */
    public Image getImage(Long id) throws EntityNotFoundException {
        return imageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Determines if an image with a certain ID exists in the database
     * @param id ID to check
     * @return True if exists, false if not
     */
    public boolean imageExists(Long id) {
        return imageRepository.findById(id).isPresent();
    }

    /**
     * Deletes an image entity and its associated content from the database.
     * @param image A managed image entity to be deleted. Must not be null
     */
    @Override
    public void deleteImage(Image image) {
        if (image.hasContent()) {
            imageStore.unsetContent(image);
        }

        imageRepository.delete(image);
    }

    /**
     * Wrapper for {@link ImageServiceImpl#deleteImage(Image)} that takes an ID
     * rather than a managed Image reference.
     * @param id ID of image to delete
     * @throws EntityNotFoundException Thrown if entity for ID not found
     */
    @Override
    @Transactional
    public void deleteImage(Long id) throws EntityNotFoundException {
        deleteImage(getImage(id));
    }

    /**
     * Deletes all image entities from the database.
     *
     * @apiNote This is dangerous and irreversible!
     */
    @Override
    public void deleteAllImages() {
        imageRepository.findAll().forEach(this::deleteImage);
    }


    @Override
    public Image createImage() {
        return imageRepository.save(new Image());
    }

    /**
     * Creates multiple managed Image entities within the database.
     * @param num The number of image entities to create
     * @return A {@link List} of all the created managed image entities
     */
    @Override
    public List<Image> createImages(Long num) {
        List<Image> images = new ArrayList<>();

        if (num < 1) {
            throw new IllegalArgumentException("Image number must be above " +
                    "zero");
        } else {
            for (int i = 0; i < num; i++) {
                images.add(createImage());
            }
        }

        return images;
    }

    /**
     * Deletes an Image's associated content.
     *
     * @implNote This also updates content metadata in the Image table
     * @param image Image to delete content for
     * @throws ContentNotFoundException Thrown if Image has no associated
     * content
     */
    @Override
    public void deleteImageContent(Image image) throws ContentNotFoundException {
        if (image.hasContent()) {
            imageStore.unsetContent(image);
        } else {
            throw new ContentNotFoundException();
        }
    }

    /**
     * Wrapper for {@link ImageServiceImpl#deleteImageContent(Image)} that takes
     * an ID rather than a managed Image reference.
     * @param id ID of image to delete content for
     * @throws EntityNotFoundException Thrown if Image ID does not exist
     * @throws ContentNotFoundException Thrown if Image has no associated
     * content
     */
    @Transactional
    @Override
    public void deleteImageContent(Long id) throws EntityNotFoundException, ContentNotFoundException {
        Image i = getImage(id);
        deleteImageContent(i);
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
     * Wrapper for {@link ImageServiceImpl#imageHasContent(Image)} that takes
     * an ID, rather than a managed Image reference.
     * @param id ID of the Image entity to check for existing content
     * @return True if the Image has content, false if not
     * @throws EntityNotFoundException Thrown if no Image found for ID
     */
    @Override
    public boolean imageHasContent(Long id) throws EntityNotFoundException {
        Image i = getImage(id);
        return imageHasContent(i);
    }

    /**
     * Checks if an Image entity has content associated with it.
     * @param i Managed Image entity to check for content
     * @return True if the Image has content, false if not
     */
    @Override
    public boolean imageHasContent(Image i) {
        return i.hasContent();
    }

    /**
     * Returns a pretty-printed String detailing counts of Image entities
     * within the database, those that have content, those that don't, and
     * the current occupying size of the Image store.
     * @return String with statistics
     */
    @Override
    public String getImageStats() {
        return String.format("[Total: %d] [With content: %d] [Without content: %d] [Total size: %s]",
                getNumberOfImageEntities(),
                getNumberOfImageEntities(true),
                getNumberOfImageEntities(false),
                getHumanSizeOfImageContent());
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
    public String getHumanSizeOfImageContent() {
        return DataUtilities.humanReadableByteCount(
                getSizeOfImageContent()
        );
    }

    /**
     * Sums the contentLength of each Image entity in the database to get the
     * total byte size of all stored content.
     * @return Long of summed bytes, unformatted
     */
    private Long getSizeOfImageContent() {
        return Objects.requireNonNullElse(imageRepository.sumTotalImagesSize(), 0L);
    }
}