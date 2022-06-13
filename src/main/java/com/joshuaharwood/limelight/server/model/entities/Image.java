/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private LocalDateTime creationTime;
    private LocalDateTime setTime;

    // Spring Content Metadata
    @ContentId
    private String contentId;
    @ContentLength
    private Long contentLength = 0L;
    @MimeType
    private String contentMimeType = "text/plain";

    public Image() {
        creationTime = LocalDateTime.now();
    }
}