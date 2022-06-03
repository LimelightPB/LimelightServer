package com.joshuaharwood.limelight.server.service;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * A collection of utilities related to processing and formatting data.
 */
public class DataUtilities {
    /**
     * Returns a human-readable representation of a binary byte count (1K = 1024).
     * @param bytes Amount of bytes to convert
     * @return String representation of amount of bytes (e.g. 2MB)
     *
     * <p>Credits to <a href="https://programming.guide/java/formatting-byte-size-to-human-readable-format.html">...</a></p>
     */
    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.2f %ciB", value / 1024.0, ci.current());
    }
}
