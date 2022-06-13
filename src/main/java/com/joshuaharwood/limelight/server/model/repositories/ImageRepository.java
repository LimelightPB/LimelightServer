/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.model.repositories;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageRepository extends JpaRepository<Image, Long> {
    /**
     * Sums the total size of content associated with image entities in the
     * database.
     * @return Sum of total image size as raw bytes.
     */
    @Query(value = "SELECT sum(i.contentLength) from Image i")
    Long sumTotalImagesSize();

    /**
     * Counts the number of image entities with content associated
     * @return Count of images with content associated
     */
    long countByContentIdIsNotNull();

    /**
     * Counts the number of image entities with no content associated
     * @return Count of images with no content associated
     */
    long countByContentIdIsNull();

    /**
     * Check if an image entity has content associated with it
     * @param id ID of the image
     * @return Whether the image has content associated
     */
    @Query("select (count(i) > 0) from Image i where i.id = ?1 and i.contentId is not null")
    boolean existsByIdAndContentIdIsNotNull(Long id);
}
