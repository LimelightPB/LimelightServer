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
    @Query(value = "SELECT sum(i.contentLength) from Image i")
    Long sumTotalImagesSize();

    long countByContentIdIsNotNull();
    long countByContentIdIsNull();
}
