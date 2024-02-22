package com.upthetreasure489474634635.activities;

import static com.upthetreasure489474634635.SplashScreenActivity.isMyServiceRunning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.databinding.ActivityRulesBinding;
import com.upthetreasure489474634635.services.BgMusicService;

public class RulesActivity extends AppCompatActivity {

    ActivityRulesBinding binding;


    @Override
    protected void onPause() {
        super.onPause();
        if (isMyServiceRunning(BgMusicService.class, RulesActivity.this)) {
            BgMusicService.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isMyServiceRunning(BgMusicService.class, RulesActivity.this)) {
            BgMusicService.onResume();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ref.isSoundEnabled){
                    SoundsClass.playButtonClickSound(RulesActivity.this);
                }
                finish();
            }
        });
    }
}