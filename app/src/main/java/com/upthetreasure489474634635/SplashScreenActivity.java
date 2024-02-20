package com.upthetreasure489474634635;

import static com.upthetreasure489474634635.activities.SettingsActivity.changeLanguageApp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.upthetreasure489474634635.activities.GameplayActivity;
import com.upthetreasure489474634635.activities.MenuScreenActivity;
import com.upthetreasure489474634635.activities.SettingsActivity;
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
        }, 3200);
    }

    private void loadDb() {
        if ((Paper.book().read("lang")) != null) {
            Ref.lang = Paper.book().read("lang");

            loadLanguage();
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
            Ref.currentTreasureIndex = (Paper.book().read("counting"));
        }

        if ((Paper.book().read("score")) != null) {
            Ref.score = (Paper.book().read("score"));
        }
        updateCharacterBtnThatAreBuyAlready();
        achieveLoadFromDb();
    }

    private void updateCharacterBtnThatAreBuyAlready() {
        if ((Paper.book().read("ch1")) != null) {
            Ref.ch1 = (Paper.book().read("ch1"));
        }
        if ((Paper.book().read("ch2")) != null) {
            Ref.ch2 = (Paper.book().read("ch2"));
        }
        if ((Paper.book().read("ch3")) != null) {
            Ref.ch3 = (Paper.book().read("ch3"));
        }
        if ((Paper.book().read("ch4")) != null) {
            Ref.ch4 = (Paper.book().read("ch4"));
        }
        if ((Paper.book().read("ch5")) != null) {
            Ref.ch5 = (Paper.book().read("ch5"));
        }
        if ((Paper.book().read("ch6")) != null) {
            Ref.ch6 = (Paper.book().read("ch6"));
        }
        if ((Paper.book().read("ch7")) != null) {
            Ref.ch7 = (Paper.book().read("ch7"));
        }
        if ((Paper.book().read("ch8")) != null) {
            Ref.ch8 = (Paper.book().read("ch8"));
        }
        if ((Paper.book().read("ch9")) != null) {
            Ref.ch9 = (Paper.book().read("ch9"));
        }
        if ((Paper.book().read("ch10")) != null) {
            Ref.ch10 = (Paper.book().read("ch10"));
        }
        if ((Paper.book().read("ch11")) != null) {
            Ref.ch11 = (Paper.book().read("ch11"));
        }
    }

    private void loadLanguage() {
        if (Ref.lang == 1) {
            changeLanguageApp("en", SplashScreenActivity.this);
        } else if (Ref.lang == 2) {
            changeLanguageApp("pt", SplashScreenActivity.this);
        } else if (Ref.lang == 0) {
            changeLanguageApp("ru", SplashScreenActivity.this);
        }
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

    //service check whether its running or not
    public static boolean isMyServiceRunning(Class<?> serviceClass, Activity a) {
        ActivityManager manager = (ActivityManager) a.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}