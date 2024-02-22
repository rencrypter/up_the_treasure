package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.SplashScreenActivity.achieveLoadFromDb;
import static com.upthetreasure489474634635.SplashScreenActivity.isMyServiceRunning;
import static com.upthetreasure489474634635.SplashScreenActivity.updateCharacterBtnThatAreBuyAlready;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.databinding.ActivityMenuScreenBinding;
import com.upthetreasure489474634635.services.BgMusicService;

import io.paperdb.Paper;

public class MenuScreenActivity extends AppCompatActivity {

    ActivityMenuScreenBinding binding;

    @Override
    protected void onPause() {
        super.onPause();
        if (isMyServiceRunning(BgMusicService.class, MenuScreenActivity.this)) {
            BgMusicService.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isMyServiceRunning(BgMusicService.class, MenuScreenActivity.this)) {
                    BgMusicService.onResume();
                }
            }
        }, 100);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        dbLoad();
        //
        bgMusicService();
        //
        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playMenuButtonClickSound(MenuScreenActivity.this);
                }

                startActivity(new Intent(MenuScreenActivity.this, GameplayActivity.class));
                finish();
            }
        });

        binding.rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playMenuButtonClickSound(MenuScreenActivity.this);
                }

                startActivity(new Intent(MenuScreenActivity.this, RulesActivity.class));
            }
        });

        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playMenuButtonClickSound(MenuScreenActivity.this);
                }

                startActivity(new Intent(MenuScreenActivity.this, SettingsActivity.class));
                finish();
            }
        });

        binding.storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playMenuButtonClickSound(MenuScreenActivity.this);
                }

                startActivity(new Intent(MenuScreenActivity.this, StoreActivity.class));
            }
        });
        //
        binding.achieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playMenuButtonClickSound(MenuScreenActivity.this);
                }

                startActivity(new Intent(MenuScreenActivity.this, AchievementActivity.class));
            }
        });
    }

    private void dbLoad() {

        if ((Paper.book().read("score")) != null) {
            Ref.score = (Paper.book().read("score"));
        }
        updateCharacterBtnThatAreBuyAlready();
        achieveLoadFromDb();
    }

    private void bgMusicService() {
        if (Ref.isSoundEnabled) {

            if (isMyServiceRunning(BgMusicService.class, MenuScreenActivity.this)) {
                Log.d("TAG", "bgMusicService: service is already on");
            } else {
                Intent i = new Intent(MenuScreenActivity.this, BgMusicService.class);
                startService(i);
                Ref.isSoundEnabled = true;//
            }
        } else {
            Log.d("TAG", "music is off");
        }
    }
}