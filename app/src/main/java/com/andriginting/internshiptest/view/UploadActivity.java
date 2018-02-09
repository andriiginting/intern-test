package com.andriginting.internshiptest.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.andriginting.internshiptest.R;

public class UploadActivity extends AppCompatActivity
implements View.OnClickListener{

    Button buttonUpload;
    ImageView imageWisata;

    Bundle bundle;

    public static final int IMAGE_GALLERY = 100;
    public static final String IMAGE_KEY = "image";
    public static final String IMAGE_NAME = "fileName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        getSupportActionBar().setTitle("Share Holiday");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageWisata = findViewById(R.id.image_from_gallery);

        bundle = getIntent().getExtras();
        byte[] bytes = bundle.getByteArray(IMAGE_KEY);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imageWisata.setImageBitmap(bitmap);
    }


    private void uploadWisata(){

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }



}
