package com.example.osmofflinelib.views.overlay;

import android.content.Context;
import android.view.MotionEvent;

import com.example.osmofflinelib.events.MapEventsReceiver;
import com.example.osmofflinelib.utils.GeoPoint;
import com.example.osmofflinelib.views.MapView;
import com.example.osmofflinelib.views.Projection;

/**
 * Empty overlay than can be used to detect events on the map,
 * and to throw them to a MapEventsReceiver.
 *
 * @author M.Kergall
 * @see MapEventsReceiver
 */
public class MapEventsOverlay extends Overlay {

    private MapEventsReceiver mReceiver;

    /**
     * Use {@link #MapEventsOverlay(MapEventsReceiver)} instead
     */
    @Deprecated
    public MapEventsOverlay(Context ctx, MapEventsReceiver receiver) {
        this(receiver);
    }

    /**
     * @param receiver the object that will receive/handle the events.
     *                 It must implement MapEventsReceiver interface.
     */
    public MapEventsOverlay(MapEventsReceiver receiver) {
        super();
        mReceiver = receiver;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e, MapView mapView) {
        Projection proj = mapView.getProjection();
        GeoPoint p = (GeoPoint) proj.fromPixels((int) e.getX(), (int) e.getY());
        return mReceiver.singleTapConfirmedHelper(p);
    }

    @Override
    public boolean onLongPress(MotionEvent e, MapView mapView) {
        Projection proj = mapView.getProjection();
        GeoPoint p = (GeoPoint) proj.fromPixels((int) e.getX(), (int) e.getY());
        //throw event to the receiver:
        return mReceiver.longPressHelper(p);
    }

}

