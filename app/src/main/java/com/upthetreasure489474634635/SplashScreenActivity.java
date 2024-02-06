package com.upthetreasure489474634635;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.upthetreasure489474634635.activities.GameplayActivity;
import com.upthetreasure489474634635.activities.MenuScreenActivity;
import com.upthetreasure489474634635.databinding.ActivitySplashScreenBinding;

import io.paperdb.Paper;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //
        Paper.init(this);
        //
        loadDb();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MenuScreenActivity.class));
                finish();
            }
        }, 2500);
    }

    private void loadDb() {
        if ((Paper.book().read("lang")) != null) {
            Ref.lang = Paper.book().read("lang");
        }
        //
        if ((Paper.book().read("isVibrate")) != null) {
            Ref.isVibrateEnabled = (Paper.book().read("isVibrate"));
        }
        if ((Paper.book().read("isSound")) != null) {
            Ref.isSoundEnabled = (Paper.book().read("isSound"));
        }
    }
}