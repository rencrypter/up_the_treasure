package com.upthetreasure489474634635.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.upthetreasure489474634635.FlyingCharacterView;
import com.upthetreasure489474634635.databinding.ActivityGameplayBinding;

import java.util.Timer;
import java.util.TimerTask;

public class GameplayActivity extends AppCompatActivity {

    private FlyingCharacterView gameView;

    private Handler handler = new Handler();
    private final static long Interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FlyingCharacterView(this);
        setContentView(gameView);
        //
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        },0, Interval);

    }
}