/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.model.repositories;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Seems like Stores can't currently be injected in to tests. We'll emulate
 * their behaviour for now...
 *
 * TODO: Add behaviour after fixing 0L
 */
@DataJpaTest
class ImageRepositoryTest {
    private final ImageRepository imageRepository;
    private static final String imagePath = "/Users/josh/Documents/Python/test_image.jpeg";
    private static final int imageNumber = 5;
//    Image[] imageRefs;

    @Autowired
    ImageRepositoryTest(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @BeforeEach
    void setUp() {
        imageRepository.deleteAll();

        for (int i = 0; i < imageNumber; i++) {
            imageRepository.save(new Image());
        }
    }

    @Test
    void countImages() {
        assertEquals(imageRepository.count(), imageNumber);
        assertNotEquals(imageRepository.count(), imageRepository.count() + 1);
    }


    @Test
    void getImage() {
        assertNotNull(imageRepository.getReferenceById(1L).getCreationTime());
    }

    @Test
    void sumTotalImagesSize() {
        Random random = new Random();
        Long sizeOne = random.nextLong(0, 10000000);
        Long sizeTwo = random.nextLong(0, 10000000);

        Image imageOne = imageRepository.getReferenceById(1L);
        Image imageTwo = imageRepository.getReferenceById(2L);
        imageOne.setContentLength(sizeOne);
        imageTwo.setContentLength(sizeTwo);

        assertEquals(
                imageRepository.sumTotalImagesSize(),
                imageOne.getContentLength() +
                        imageTwo.getContentLength());
    }
}