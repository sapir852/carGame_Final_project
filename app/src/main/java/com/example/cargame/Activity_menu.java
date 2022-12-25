package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Activity_menu extends AppCompatActivity {
    AppCompatImageView menu_IMG_background;
    Button menu_BTN_slow;
    Button menu_BTN_fast;
    Button menu_BTN_sensor;
    Button menu_BTN_Table_Score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findView();
        initViews();
        FullScreen.hideSystemUI(this);

        Glide
                .with(Activity_menu.this)
                .load("https://c4.wallpaperflare.com/wallpaper/122/738/503/mclaren-p1-4k-wallpaper-preview.jpg")
                .into(menu_IMG_background);

    }

    private void initViews() {

        menu_BTN_slow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(false,false);
            }
        });

        menu_BTN_fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(true,true);
            }
        });

        menu_BTN_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(true,false);
            }
        });

        menu_BTN_Table_Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHighScores();
            }
        });
    }

    private void findView() {

        menu_IMG_background = findViewById(R.id.menu_IMG_background);
        menu_BTN_slow= findViewById(R.id.menu_BTN_slow);
        menu_BTN_fast = findViewById(R.id.menu_BTN_fast);
        menu_BTN_sensor = findViewById(R.id.menu_BTN_sensor);
        menu_BTN_Table_Score= findViewById(R.id.menu_BTN_Table_Score);

    }

    private void showHighScores() {
        Intent highScoresIntent = new Intent(this, Activity_List_Stare.class);

        startActivity(highScoresIntent);
    }

    public void startGame(boolean sensorMode,boolean fastMod) {
        Intent gameIntent = new Intent(this, Activity_CarGame.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(Activity_CarGame.SENSOR_MODE, sensorMode);
        bundle.putBoolean(Activity_CarGame.FAST_MODE, fastMod);

        gameIntent.putExtras(bundle);
        startActivity(gameIntent);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            FullScreen.hideSystemUI(this);
        }
    }
}