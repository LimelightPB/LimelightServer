/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.utils;

public interface ImageService {
    Long getNumberOfImageEntities();
    Long getNumberOfImageEntities(boolean withContent);
    String getSizeOfImageContent();
    public String getImageStats();
}
