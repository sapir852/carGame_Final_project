package com.example.cargame;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Collections;

public class Activity_Game_Over extends AppCompatActivity {
    public static final String KEY_SCORE = "KEY_SCORE" ;
    private TextView gameOver_TXT_score;
    private EditText gameOver_ETXT_namePlayer;
    private MaterialButton gameOver_BTN_savePlayer;
    private int score;
    private String playerName;
    private SaveData DB;
    private AppCompatImageView gameOver_IMG_backgrond;
    Button gameOver_BTN_exit;
    Button gameOver_BTN_play_again;
    private GpsTracker gpsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        findView();
        initViews();
        FullScreen.hideSystemUI(this);


        Glide
                .with(Activity_Game_Over.this)
                .load("https://c4.wallpaperflare.com/wallpaper/122/738/503/mclaren-p1-4k-wallpaper-preview.jpg")
                .into(gameOver_IMG_backgrond);


        score = getIntent().getExtras().getInt("Score");

        gameOver_TXT_score.setText("your Score is :" + score);

    }
    private void initViews() {

        gameOver_BTN_savePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName = gameOver_ETXT_namePlayer.getText().toString();
                double latitude = 0.0;
                double longitude = 0.0;

                // * Start of Location Service
                gpsPlayer = new GpsTracker(Activity_Game_Over.this);
                if (gpsPlayer.canGetLocation()) {
                    latitude = gpsPlayer.getLatitude();
                    longitude = gpsPlayer.getLongitude();
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                } else {
                    gpsPlayer.showSettingsAlert();
                }
                // * End of Location Service

                gameOver_ETXT_namePlayer.setVisibility(View.GONE);
                gameOver_BTN_savePlayer.setVisibility(View.GONE);

                savePlayer(playerName, score, longitude, latitude);
            }


    });
        gameOver_BTN_play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }

        });

        gameOver_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }

        });


    }


    private void savePlayer(String player_name, int score, double longitude, double latitude) {

        String js = MySPV3.getInstance().getString("MY_DB", "");
        DB = new Gson().fromJson(js, SaveData.class);


        DB.getSave().add(new Save()
                .setName(playerName)
                .setScore(score)
                .setLat(latitude)
                .setLon(longitude)
        );

        Collections.sort(DB.getSave(), new SortByScore());

        String json = new Gson().toJson(DB);
        MySPV3.getInstance().putString("MY_DB", json);
    }
    private void playAgain() {

        Intent gameIntent = new Intent(this, Activity_CarGame.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(Activity_CarGame.SENSOR_MODE, getIntent().getExtras().getBoolean(Activity_CarGame.SENSOR_MODE));

        gameIntent.putExtras(bundle);
        startActivity(gameIntent);
        this.finish();


    }
    private void findView() {

        gameOver_IMG_backgrond= findViewById(R.id.gameOver_IMG_backgrond);
        gameOver_TXT_score = findViewById(R.id.gameOver_TXT_score);
        gameOver_ETXT_namePlayer = findViewById(R.id.gameOver_ETXT_namePlayer);
        gameOver_BTN_savePlayer =findViewById(R.id.gameOver_BTN_saveName);
        gameOver_BTN_exit =findViewById(R.id.gameOver_BTN_exit);
        gameOver_BTN_play_again =findViewById(R.id.gameOver_BTN_play_again);


    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            FullScreen.hideSystemUI(this);
        }
    }
}