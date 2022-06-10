/*
 * Copyright (c) 2022 Joshua J. A. Harwood.
 *
 * Proprietary - not to be distributed or modified without permission.
 */

package com.joshuaharwood.limelight.server.service.rest;

import java.util.logging.Level;

public interface RestLogger {
    void logLastRequest();
    void log(String message);
    void log(Level level, String message);
}
