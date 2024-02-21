package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.SplashScreenActivity.isMyServiceRunning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.VibrationEffect;
import com.upthetreasure489474634635.databinding.ActivityMenuScreenBinding;
import com.upthetreasure489474634635.services.BgMusicService;

public class MenuScreenActivity extends AppCompatActivity {

    ActivityMenuScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        bgMusicService();
        //
        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(MenuScreenActivity.this);
//                }
                startActivity(new Intent(MenuScreenActivity.this, GameplayActivity.class));
            }
        });

        binding.rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(MenuScreenActivity.this);
//                }
                startActivity(new Intent(MenuScreenActivity.this, RulesActivity.class));
            }
        });

        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(MenuScreenActivity.this);
//                }
                startActivity(new Intent(MenuScreenActivity.this, SettingsActivity.class));
                finish();
            }
        });

        binding.storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(MenuScreenActivity.this);
//                }
                startActivity(new Intent(MenuScreenActivity.this, StoreActivity.class));
            }
        });
        //
        binding.achieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ref.isSoundEnabled) {
                    SoundsClass.playButtonClickSound(MenuScreenActivity.this);
                }
//                if(Ref.isVibrateEnabled){
//                    VibrationEffect.VibrationEffect(MenuScreenActivity.this);
//                }
                startActivity(new Intent(MenuScreenActivity.this, AchievementActivity.class));
            }
        });
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