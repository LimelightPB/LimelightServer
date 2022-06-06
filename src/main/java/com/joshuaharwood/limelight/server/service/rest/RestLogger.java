package com.joshuaharwood.limelight.server.service.rest;

import java.util.logging.Level;

public interface RestLogger {
    void logLastRequest();
    void log(String message);
    void log(Level level, String message);
}
