package com.joshuaharwood.limelight.server.model.repositories;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageRepository extends JpaRepository<Image, Long> {
}
