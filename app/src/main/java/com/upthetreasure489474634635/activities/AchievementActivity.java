package com.upthetreasure489474634635.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.upthetreasure489474634635.R;
import com.upthetreasure489474634635.Ref;
import com.upthetreasure489474634635.SoundsClass;
import com.upthetreasure489474634635.adapters.ImageAdapter;
import com.upthetreasure489474634635.databinding.ActivityAchievementBinding;

public class AchievementActivity extends AppCompatActivity {

    ActivityAchievementBinding binding;

    private Integer[] images = {
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery,
            R.drawable.ic_lock_gallery
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAchievementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        ImageAdapter adapter = new ImageAdapter(this, images);
        binding.gridView.setAdapter(adapter);

//        // Change the image at a specific index (for example, index 2).
//        int indexToChange = 2;
//        int newImageResource = R.drawable.new_image; // Replace 'new_image' with your desired image resource
//
//        if (indexToChange >= 0 && indexToChange < images.length) {
//            images[indexToChange] = newImageResource;
//            adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
//        } else {
//            // Handle invalid index
//        }
        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle grid item click if needed
                // For example, you can display the clicked image in a larger ImageView
                if(Ref.isSoundEnabled){
                    SoundsClass.playButtonClickSound(AchievementActivity.this);
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ref.isSoundEnabled){
                    SoundsClass.playButtonClickSound(AchievementActivity.this);
                }
                finish();
            }
        });

        //

    }
}