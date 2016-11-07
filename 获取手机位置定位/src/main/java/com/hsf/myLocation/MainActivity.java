package com.hsf.myLocation;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button get;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Location location;
    private LocationManager locationManager;
    private String provider;
    private String serviceString;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = (Button) findViewById(R.id.get);
        get.setOnClickListener(this);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        sp = getSharedPreferences("info", 0);
        serviceString = Context.LOCATION_SERVICE;// 获取的是位置服务
        // 调用getSystemService()方法来获取LocationManager
        locationManager = (LocationManager) getSystemService(serviceString);
        // 指定LocationManager的定位方法
        provider = LocationManager.GPS_PROVIDER;
        // 调用getLastKnownLocation()方法获取当前的位置信息
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        }

        location = locationManager.getLastKnownLocation(provider);
        //getLocationInfo(location);// 获得经度和纬度的方法
        locationManager.requestLocationUpdates(provider, 2000, 10,
                locationListener);// 产生位置改变事件的条件设定为距离改变10米，时间间隔为2秒，设定监听位置变化

    }

    private void getLocationInfo(Location location) {
        if (location != null) {
            tv1.setText("得到的location不为空");
            double lat = location.getLatitude();//获取纬度
            double lng = location.getLongitude();//获取经度
            sp.edit().putFloat("lat", (float) lat).commit();
            sp.edit().putFloat("lng", (float) lng).commit();
            tv2.setText("维度：" + lat);
            tv3.setText("经度：" + lng);
        } else {
            tv1.setText("得到的location为空，从之前存的位置获取的经纬度");
            tv2.setText("维度：" + sp.getFloat("lat", 0));
            tv3.setText("经度：" + sp.getFloat("lng", 0));
        }
    }

    @Override
    public void onClick(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            //return;
        }
        location = locationManager.getLastKnownLocation(provider);
        getLocationInfo(location);// 获得经度和纬度的方法

    }
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
