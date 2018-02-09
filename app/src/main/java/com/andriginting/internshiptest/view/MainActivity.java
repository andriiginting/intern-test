package com.andriginting.internshiptest.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.util.UserPreference;
import com.andriginting.internshiptest.view.fragment.DataranRendahFragment;
import com.andriginting.internshiptest.view.fragment.DataranTinggiFragment;
import com.andriginting.internshiptest.view.fragment.PantaiFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.andriginting.internshiptest.view.UploadActivity.IMAGE_GALLERY;
import static com.andriginting.internshiptest.view.UploadActivity.IMAGE_KEY;
import static com.andriginting.internshiptest.view.UploadActivity.IMAGE_URL_UPLOAD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab;

    public static String TAG_KATEGORI_TINGGI = "1";
    public static String TAG_KATEGORI_RENDAH = "2";
    public static String TAG_KATEGORI_PANTAI= "3";
    public static int CURRENT_TAG_NUMBER = 0;
    public static String CURRENT_TAG= "Dataran Tinggi";

    UserPreference userPreference;
    File fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Gits Tempat Wisata");

       fab = findViewById(R.id.fab_add_content);
       fab.setOnClickListener(this);

       userPreference = new UserPreference(this);
        if (savedInstanceState == null){
            CURRENT_TAG_NUMBER = 0;
            loadFragment();
        }


    }

    private Fragment goToFragment(){
        switch (CURRENT_TAG_NUMBER){
            case 0 :
                DataranTinggiFragment tinggiFragment = new DataranTinggiFragment();
                return tinggiFragment;
            case 1 :
                DataranRendahFragment rendahFragment = new DataranRendahFragment();
                return  rendahFragment;
            case 2:
                PantaiFragment pantaiFragment = new PantaiFragment();
                return pantaiFragment;
                default:
                    return new DataranTinggiFragment();
        }
    }
    private void loadFragment(){
                Fragment fragment = goToFragment();
                FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                ft.replace(R.id.frame_container_wisata,fragment,CURRENT_TAG);
                ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment =null;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (id){
            case R.id.logout:{
                userPreference.isLogin(UserPreference.PREF_IS_LOGIN,false);
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
            }
            case R.id.menu_item_kategori_rendah:{
                CURRENT_TAG_NUMBER =1;
                break;
            }
            case R.id.menu_item_kategori_pantai:{
                CURRENT_TAG_NUMBER =2;
                break;
            }
        }

        if (item.isChecked()){
            item.setChecked(true);
        }

        if (fragment !=null){
            fragmentTransaction.replace(R.id.frame_container_wisata,fragment);
            fragmentTransaction.commit();
        }else{
            loadFragment();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_add_content:
                Intent chooseImage = new Intent(Intent.ACTION_PICK);
                chooseImage.setType("image/*");
                startActivityForResult(chooseImage,IMAGE_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        byte[] bytesArray = null;
        if (resultCode == RESULT_OK ){
            try{
                Uri imageSelected = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageSelected);

                Bitmap selected = BitmapFactory.decodeStream(imageStream);

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(imageSelected,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String image = cursor.getString(columnIndex);
                cursor.close();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                selected.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                bytesArray  = outputStream.toByteArray();

                fileName = new File(image);


            }catch (FileNotFoundException e){
                Log.e("error",e.getMessage());
            }
            Intent intent = new Intent(this,UploadActivity.class);
            intent.putExtra(IMAGE_KEY,bytesArray);
            intent.putExtra(IMAGE_URL_UPLOAD,fileName);
            startActivity(intent);
        }
    }
}
