package com.joshuaharwood.limelight.server.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestLoggerImpl implements RestLogger {
    private static final Logger logger = Logger.getLogger(RestLoggerImpl.class.getName());
    private final HttpServletRequest request;

    @Autowired
    public RestLoggerImpl(HttpServletRequest request) {
        this.request = request;
    }

    public void logLastRequest() {
        logger.info("Served " + request.getMethod() + " request at " + request.getRequestURI());
    }

    public void log(String message) {
        logger.info(message);
    }

    public void log(Level level, String message) {
        logger.log(level, message);
    }
}
