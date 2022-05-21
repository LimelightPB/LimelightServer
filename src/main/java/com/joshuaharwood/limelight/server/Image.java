package com.joshuaharwood.limelight.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private LocalDateTime creationTime = LocalDateTime.now();

    @ContentId private String contentId;
    @ContentLength private Long contentLength;
    @MimeType private String contentMimeType = "text/plain";
}