package com.example.cargame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;


import java.util.Timer;
import java.util.TimerTask;

public class Activity_CarGame extends AppCompatActivity {
    private final int NUM_OF_COLUMNS = 5;
    private final int MAX_LIVES = 3;

    private AppCompatImageView game_IMG_background;
    private ExtendedFloatingActionButton game_BTN_Right;
    private ExtendedFloatingActionButton game_BTN_Left;
    private MaterialTextView  game_TEXT_score;


    private ShapeableImageView[] game_IMG_hearts;
    private ImageView[] game_IMG_car;
    private int move = 2;
    int l=1;
    int r=-1;

    private ImageView[][] rock;
    private int[][] vals;
    private int live=MAX_LIVES;

    private int colum_Choose;

    private int miniTime=0;
    private int score=0;
    private StepDetector stepDetector;

    private boolean sensorMode = false;
    private boolean fastMode = false;
    public static final String SENSOR_MODE = "SENSOR_MODE";
    public static final String FAST_MODE = "FAST_MODE";
    private int delay=500;


    public interface CallBack_Timer {
        void tick();

    }


    private MyTicker myTicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initViews();
        FullScreen.hideSystemUI(this);

        Glide
                .with(Activity_CarGame.this)
                .load("https://i.pinimg.com/originals/0c/1b/c0/0c1bc08b26b95f83240cc4edba40f0e3.jpg")
                .into(game_IMG_background);

        sensorMode = getIntent().getExtras().getBoolean(SENSOR_MODE);



        initVals();
        if (sensorMode) {
            stepDetector = new StepDetector(this,callBack_steps);

            if(fastMode){
                delay=100;
            }
            game_BTN_Left.setVisibility(View.INVISIBLE);
            game_BTN_Right.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        startTimer();


    }

    @Override
    protected void onResume() {
        super.onResume();

            myTicker.start();

        if (sensorMode) {
            stepDetector.resumed();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        myTicker.stop();
        if (sensorMode) {
            stepDetector.paused();
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        myTicker.stop();
    }
    private void initVals(){
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[i].length; j++) {
                vals[i][j] = 0;
            }
        }
    }


    private void moveCar(int direction) {

        if (direction==-1 && move < 4) {
            game_IMG_car[move].setVisibility(View.INVISIBLE);
            move++;
            game_IMG_car[move].setVisibility(View.VISIBLE);
        } else if (direction==1 && move >= 1) {
            game_IMG_car[move].setVisibility(View.INVISIBLE);
            move--;
            game_IMG_car[move].setVisibility(View.VISIBLE);
        }
    }

    private StepDetector.CallBack_steps callBack_steps = new StepDetector.CallBack_steps() {
        @Override
        public void move_sensor_mode(float x) {
            if (x > 4) {
                game_IMG_car[move].setVisibility(View.INVISIBLE);
                move = 0;
                game_IMG_car[move].setVisibility(View.VISIBLE);
            } else if (x < 4 && x > 2) {
                game_IMG_car[move].setVisibility(View.INVISIBLE);
                move = 1;
                game_IMG_car[move].setVisibility(View.VISIBLE);
            } else if (x < 2 && x > -2) {
                game_IMG_car[move].setVisibility(View.INVISIBLE);
                move = 2;
                game_IMG_car[move].setVisibility(View.VISIBLE);
            } else if (x < -2 && x > -4) {
                game_IMG_car[move].setVisibility(View.INVISIBLE);
                move = 3;
                game_IMG_car[move].setVisibility(View.VISIBLE);
            } else {
                game_IMG_car[move].setVisibility(View.INVISIBLE);
                move = 4;
                game_IMG_car[move].setVisibility(View.VISIBLE);
            }
        }
    };


    private void initViews() {
        game_BTN_Left.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveCar(l);
            }
        }));
        game_BTN_Right.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCar(r);
            }
        }));
    }


    private void logic() {


        colum_Choose = (int) Math.floor(Math.random() * NUM_OF_COLUMNS);

        for (int i = vals.length - 1; i > 0; i--) {
            for (int j = 0; j < vals[0].length; j++) {
                vals[i][j] = vals[i - 1][j]; // save the rock for next colum
            }
        }

        for (int i = 0; i < vals[0].length; i++) {
            vals[0][i] = 0;
        }
        if(miniTime==3)
        {
            vals[0][colum_Choose] = 2;
            miniTime=0;

        }else {
            vals[0][colum_Choose] = 1;
        }

        updateUILogic();
    }
    private void updateUILogic() {

        for (int i = 0; i < rock.length; i++) {
            for (int j = 0; j < rock[i].length; j++) {
                ImageView im = rock[i][j];
                if (vals[i][j] == 0) {
                    im.setVisibility(View.INVISIBLE);
                } else if (vals[i][j] == 1 ) {
                    im.setVisibility(View.VISIBLE);
                    im.setImageResource(R.drawable.ic_rock);
                } else if (vals[i][j] == 2 ) {
                    im.setVisibility(View.VISIBLE);
                    im.setImageResource(R.drawable.ic_coin);


                }}}}


    private void chekGame() {
        if (vals[NUM_OF_COLUMNS-1][move] == 1 ) {

            MySignal.getInstance().vibrate();
            MySignal.getInstance().playSound(R.raw.boom_sound);
            live--;

            game_IMG_hearts[live].setVisibility(View.INVISIBLE);

            if (live <= 0) {
                gameOver();
            }
        } if (vals[NUM_OF_COLUMNS-1][move] == 2 ) {
            score+=5;
            MySignal.getInstance().playSound(R.raw.coins_sound);
            game_TEXT_score.setText("" + score);


        }
    }

    private void gameOver() {
        myTicker.stop();
        MySignal.getInstance().vibrate();
        MySignal.getInstance().toast("GAME OVER!!!! ");
        openScorePage(score);

    }
    private void openScorePage( int score){

        Intent intent = new Intent(Activity_CarGame.this, Activity_Game_Over.class);
        intent.putExtra("Score", score);
        intent.putExtra(SENSOR_MODE, sensorMode);
        startActivity(intent);
        this.finish();
    }

    private void findView() {
        game_TEXT_score = findViewById(R.id.game_TEXT_score);
        game_IMG_background = findViewById(R.id.game_IMG_background);

        game_BTN_Right = findViewById(R.id.game_BTN_Right);

        game_BTN_Left = findViewById(R.id.game_BTN_Left);



        game_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3),};

        game_IMG_car = new ImageView[]{
                findViewById(R.id.game_IMG_left),
                findViewById(R.id.game_IMG_midl_left),
                findViewById(R.id.game_IMG_midl),
                findViewById(R.id.game_IMG_midl_right),
                findViewById(R.id.game_IMG_right),};

        rock= new ImageView[][]{
                {findViewById(R.id.game_IMG_rock_00), findViewById(R.id.game_IMG_rock_01), findViewById(R.id.game_IMG_rock_02),findViewById(R.id.game_IMG_rock_03),findViewById(R.id.game_IMG_rock_04)},
                {findViewById(R.id.game_IMG_rock_10), findViewById(R.id.game_IMG_rock_11), findViewById(R.id.game_IMG_rock_12), findViewById(R.id.game_IMG_rock_13), findViewById(R.id.game_IMG_rock_14)},
                {findViewById(R.id.game_IMG_rock_20), findViewById(R.id.game_IMG_rock_21), findViewById(R.id.game_IMG_rock_22), findViewById(R.id.game_IMG_rock_23), findViewById(R.id.game_IMG_rock_24)},
                {findViewById(R.id.game_IMG_rock_30), findViewById(R.id.game_IMG_rock_31), findViewById(R.id.game_IMG_rock_32), findViewById(R.id.game_IMG_rock_33), findViewById(R.id.game_IMG_rock_34)},
                {findViewById(R.id.game_IMG_rock_40), findViewById(R.id.game_IMG_rock_41), findViewById(R.id.game_IMG_rock_42), findViewById(R.id.game_IMG_rock_43), findViewById(R.id.game_IMG_rock_44)},
        };

        vals = new int[rock.length][rock[0].length];


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            FullScreen.hideSystemUI(this);
        }
    }



    private void startTimer() {
        CallBack_Timer callBack_timer = new CallBack_Timer() {
            @Override
            public void tick() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ticker();
                        miniTime++;
                        chekGame();
                        logic();
                    }
                });
            }};

        myTicker = new MyTicker(callBack_timer,delay);
    }
    public void ticker() {

    }


}
