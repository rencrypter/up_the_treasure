package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.Ref.isSoundEnabled;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.assets.Gameview;
import com.upthetreasure489474634635.services.BgMusicService;

import io.paperdb.Paper;

public class GameplayActivity extends AppCompatActivity {


    Gameview gameview;
    Button playPauseBtn, homeBtn;
    boolean isPlayPauseBtn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        // Create a FrameLayout as the root layout
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Create your Gameview
        gameview = new Gameview(this, point.x, point.y);
        frameLayout.addView(gameview);

        // Create your Button
        playPauseBtn = new Button(this);
        playPauseBtn.setBackgroundResource(R.drawable.ic_pause_btn);
        homeBtn = new Button(this);
        homeBtn.setBackgroundResource(R.drawable.home_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlayPauseBtn) {
                    // If drawable is changed, set it back to the initial state
                    playPauseBtn.setBackgroundResource(R.drawable.ic_play_btn);
                    isPlayPauseBtn = false;
                    gameview.pause();

                } else {
                    // If drawable is not changed, set it to the new drawable
                    playPauseBtn.setBackgroundResource(R.drawable.ic_pause_btn);
                    isPlayPauseBtn = true;

                    gameview.resume();
                }
            }
        });
        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.gravity = Gravity.TOP | Gravity.RIGHT;
        buttonParams.width = 100; // Set desired width in pixels**
        buttonParams.height = 100;
        buttonParams.setMargins(10, 10, 10, 10);
        FrameLayout.LayoutParams buttonParams1 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        buttonParams1.gravity = Gravity.TOP | Gravity.LEFT;
        buttonParams1.width = 100; // Set desired width in pixels**
        buttonParams1.height = 100;
        buttonParams1.setMargins(10, 10, 10, 10);
        playPauseBtn.setLayoutParams(buttonParams);
        homeBtn.setLayoutParams(buttonParams1);
        frameLayout.addView(homeBtn);
        frameLayout.addView(playPauseBtn);

        // Set the content view to the FrameLayout
        setContentView(frameLayout);

        //

    }

    //
    @Override
    protected void onPause() {
        super.onPause();
        gameview.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resume();
    }
}