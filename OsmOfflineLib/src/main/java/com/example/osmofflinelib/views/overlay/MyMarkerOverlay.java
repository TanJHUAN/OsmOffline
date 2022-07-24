package com.example.osmofflinelib.views.overlay;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;
import com.example.osmofflinelib.library.R;
import com.example.osmofflinelib.utils.GeoPoint;
import com.example.osmofflinelib.views.MapView;
import com.example.osmofflinelib.views.Projection;

import java.util.ArrayList;


public class MyMarkerOverlay extends Overlay {
    /**
     * 基本属性
     */
    private Context context;//cons
    private MapView mMapView;//cons
    private OverlayItem overlayItem;
    private Point point;
    private GeoPoint geo;
    private ItemizedOverlayWithFocus itemItemizedOverlayWithFocus;
    private ArrayList<OverlayItem> overlayItems = new ArrayList<>();
    /**
     * 传入context
     * constructrue
     */
    public MyMarkerOverlay(Context context,MapView mapView){
        super(context);
        this.context = context;
        mMapView = mapView;
    }
    //地图点击事件
    @Override
    public boolean onSingleTapUp(MotionEvent e, final MapView mapView) {
        Projection projection = mMapView.getProjection();
        point = new Point((int)e.getX(),(int)e.getY());
        geo = (GeoPoint)projection.fromPixels((int)e.getX(),(int)e.getY());
        //初始化overlayitem
        overlayItem = new OverlayItem("test","MARKERITEM",geo);
        overlayItem.setMarker(context.getResources().getDrawable(R.drawable.map_marker));
        //添加进数组，添加进图层
        overlayItems.add(overlayItem);
        /**
         * do something
         */
        //新建图层，添加标记
        itemItemizedOverlayWithFocus = new ItemizedOverlayWithFocus(
                 overlayItems, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                     @Override
                     public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                         return true;
                     }
                     @Override
                     public boolean onItemLongPress(final int index, final OverlayItem item) {
                         return false;
                     }
                 }, context);
        /**
         * 将该itmizedoverlay添加到地图
         */
        drawMarker();
        return super.onSingleTapUp(e, mapView);
    }

    public int getItemSize(){
        return overlayItems.size();
    }
 
 
    public void removeThis(){
        mMapView.getOverlays().remove(this);
        mMapView.invalidate();
    }

    //撤销
    public void removeLast(){
        int size = mMapView.getOverlays().size();
        if (overlayItems.size() > 0){
            mMapView.getOverlays().remove(size - 1);
            overlayItems.remove(overlayItems.size() - 1);
            mMapView.invalidate();
        }else {
            Toast.makeText(context, "已经是最后一步，无法撤销", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 将itmizedoverly添加到地图上
     * 并添加到图层管理
     * 12.25未完成图层管理
     */
    public void drawMarker(){
        mMapView.getOverlays().add(itemItemizedOverlayWithFocus);
        mMapView.invalidate();
        //mMapView.getOverlayManager().remove(itemItemizedOverlayWithFocus);
    }
 
}