package com.example.osmofflinelib.views.overlay.mylocation;

import android.location.Location;

public interface IMyLocationProvider {
    boolean startLocationProvider(IMyLocationConsumer myLocationConsumer);

    void stopLocationProvider();

    Location getLastKnownLocation();

    void destroy();
}
