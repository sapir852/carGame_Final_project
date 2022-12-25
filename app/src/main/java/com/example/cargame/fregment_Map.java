package com.example.cargame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class fregment_Map extends Fragment implements OnMapReadyCallback
{
    private GoogleMap mMap;
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_fregment_map, container, false);


            SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.maps);
            mapFragment.getMapAsync(this);




            return view;
        }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng melbourne = new LatLng(32.014785, 34.787335);
        googleMap.addMarker(new MarkerOptions()
                .position(melbourne)
                .title("Flight Destination : Melbourne Airport, Vic, Australia"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(melbourne, 14.0f));
    }

    CallBack_List callBack_list = new CallBack_List() {
        @Override
        public void rowSelected(double longitude, double latitude, String playerName) {
            zoom(longitude, latitude, playerName);
        }

        @Override
        public void clearListClicked() {

        }
    };


    private void zoom(double longitude, double latitude, String name) {
        LatLng point = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(point)
                .title("* Crash Site * | Pilot Name: " + name));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 13.0f));
    }


}
