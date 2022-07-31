package com.example.wallpaperchanger;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.DisplayMetrics;


import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int height = 0;
    int width = 0;
    private ImageView image;
    private Bitmap bitmap;





// reference to photo in app itself
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);
        getDisplayMetrics(); // get display of device used



    }

    public void getDisplayMetrics(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels ;
        //Log.i("test", height + " " + width);




    }

    public void changeImage(View view) {
        getImage();
    }

    public void setWallpaper(View view){
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());


        try{


            manager.setBitmap(Bitmap.createScaledBitmap(bitmap,width,height,false));
            manager.suggestDesiredDimensions(width, height);
            Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }


    }

    private void getImage() {
        //String wallpaperDirectoryPath = WALLPAPER_DIRECTORY;
        //File wallpaperDirectory = new file(wallpaperDirectoryPath);

        ContentResolver resolver = getContentResolver();



        //-------------------------------

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);


        launchSomeActivity.launch(i);








        //-------------








    }



    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_test_2_foreground);
                        try {
                            bitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        image.setImageBitmap(
                                bitmap);
                    }
                }
            });



}