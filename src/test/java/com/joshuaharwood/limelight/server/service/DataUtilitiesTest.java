package com.joshuaharwood.limelight.server.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilitiesTest {

    @Test
    void humanReadableByteCountBin() {
        assertEquals("1.21 MiB", DataUtilities.humanReadableByteCountBin(1264083));
    }
}