package com.example.wallpaperchanger;


import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View view){
        //Log.i("click", "affirmative");
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.mipmap.ic_test_2_foreground);
    }
}