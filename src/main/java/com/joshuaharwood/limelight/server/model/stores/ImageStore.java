/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.model.stores;

import com.joshuaharwood.limelight.server.model.entities.Image;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
@StoreRestResource
public interface ImageStore extends FilesystemContentStore<Image, String> {
    @Configuration
    @EnableFilesystemStores
    class ImageStoreConfig {
        @Bean
        FileSystemResourceLoader fileSystemResourceLoader() {
            return new FileSystemResourceLoader(
                    new File(System.getProperty("user.home") + "/limelight/images").getAbsolutePath());
        }
    }
}
