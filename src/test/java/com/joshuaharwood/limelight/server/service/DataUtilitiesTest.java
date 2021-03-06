package com.joshuaharwood.limelight.server.service;

import com.joshuaharwood.limelight.server.service.utils.DataUtilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilitiesTest {

    @Test
    void humanReadableByteCount() {
        assertEquals("1.21 MiB", DataUtilities.humanReadableByteCount(1264083));
    }
}