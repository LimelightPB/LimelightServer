/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.logic;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ContentNotFoundException() {
    }
}
