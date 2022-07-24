package com.example.osmofflinelib.utils;

/**
 * Compute a map tile area from a map tile area source
 *
 * @author Fabrice Fontaine
 * @since 6.0.3
 */

public interface MapTileAreaComputer {

    MapTileArea computeFromSource(final MapTileArea pSource, final MapTileArea pReuse);
}
