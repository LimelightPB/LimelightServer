package com.joshuaharwood.limelight.server;

import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.File;

@Configuration
@EnableFilesystemStores
//@Import(org.springframework.content.rest.config.RestConfiguration.class)
public class ImageStoreConfig {
    @Bean
    FileSystemResourceLoader fileSystemResourceLoader() {
        return new FileSystemResourceLoader(
                new File(System.getProperty("user.home") + "/limelight/images").getAbsolutePath());
    }
}
