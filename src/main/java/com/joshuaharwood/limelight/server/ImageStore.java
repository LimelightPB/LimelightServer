package com.joshuaharwood.limelight.server;

import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.content.rest.StoreRestResource;

@StoreRestResource
public interface ImageStore extends FilesystemContentStore<Image, String> {
}
