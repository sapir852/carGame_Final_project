package com.example.cargame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class StepDetector {

    public interface CallBack_steps {
        void move_sensor_mode(float x);

    }

    private Sensor sensor;
    private SensorManager mSensorManager;
    private CallBack_steps callBack_steps;
    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBackSensor(CallBack_steps callBack_steps) {
        this.callBack_steps = callBack_steps;
    }
    public StepDetector(Context context, CallBack_steps _callBack_steps) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.callBack_steps = _callBack_steps;
    }


    private SensorEventListener accSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            callBack_steps.move_sensor_mode(event.values[0]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void resumed(){
        mSensorManager.registerListener(accSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void paused() {
        mSensorManager.unregisterListener(accSensorEventListener);
    }
}

