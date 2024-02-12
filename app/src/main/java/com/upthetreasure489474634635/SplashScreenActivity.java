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
        if ((Paper.book().read("character")) != null) {
            Ref.character = (Paper.book().read("character"));
        }
        if ((Paper.book().read("counting")) != null) {
            Ref.countForAchiev = (Paper.book().read("counting"));
        }

        achieveLoadFromDb();
    }

    private void achieveLoadFromDb() {
        if (Paper.book().read("achi1") != null) {
            Ref.achi1 = Paper.book().read("achi1");
        }
        if (Paper.book().read("achi2") != null) {
            Ref.achi2 = Paper.book().read("achi2");
        }
        if (Paper.book().read("achi3") != null) {
            Ref.achi3 = Paper.book().read("achi3");
        }
        if (Paper.book().read("achi4") != null) {
            Ref.achi4 = Paper.book().read("achi4");
        }
        if (Paper.book().read("achi5") != null) {
            Ref.achi5 = Paper.book().read("achi5");
        }
        if (Paper.book().read("achi6") != null) {
            Ref.achi6 = Paper.book().read("achi6");
        }
        if (Paper.book().read("achi7") != null) {
            Ref.achi7 = Paper.book().read("achi7");
        }
        if (Paper.book().read("achi8") != null) {
            Ref.achi8 = Paper.book().read("achi8");
        }
        if (Paper.book().read("achi9") != null) {
            Ref.achi9 = Paper.book().read("achi9");
        }
        if (Paper.book().read("achi10") != null) {
            Ref.achi10 = Paper.book().read("achi10");
        }
        if (Paper.book().read("achi11") != null) {
            Ref.achi11 = Paper.book().read("achi11");
        }
        if (Paper.book().read("achi12") != null) {
            Ref.achi12 = Paper.book().read("achi12");
        }
    }
}