package com.example.cargame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Activity_List_Stare extends AppCompatActivity {
    private RecyclerView listStar_LST_victory;


    private fragment_list fragment_list;
    private fregment_Map fragment_map;
    private MaterialTextView listStar_LBL_title;
    private FrameLayout listStar_LAY_map;
    private FrameLayout listStar_LAY_list;




 CallBack_UserProtocol callBack_userProtocol= new CallBack_UserProtocol() {
     @Override
     public ArrayList<Save> saves() {
         return SaveData.getSave();



     }
 };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_score);
        FullScreen.hideSystemUI(this);
        findViews();

        fragment_list = new fragment_list();
        fragment_map = new fregment_Map();


        getSupportFragmentManager().beginTransaction().add(R.id.listStar_LAY_list, fragment_list).commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listStar_LAY_map, fragment_map)
                .commit();


    }





    private void findViews() {

        listStar_LBL_title = findViewById(R.id.listStar_LBL_title);
        listStar_LAY_map = findViewById(R.id.listStar_LAY_map);
        listStar_LAY_list = findViewById(R.id.listStar_LAY_list);

    }



}


