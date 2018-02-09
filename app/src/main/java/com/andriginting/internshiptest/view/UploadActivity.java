package com.andriginting.internshiptest.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.model.ApiResponse;
import com.andriginting.internshiptest.model.UploadDataModel;
import com.andriginting.internshiptest.network.ApiClient;
import com.andriginting.internshiptest.network.ApiInterface;

import java.util.UUID;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity
implements View.OnClickListener{

    Button buttonUpload;
    ImageView imageWisata;
    RadioGroup rgCategory;
    RadioButton rbSelectedCategory;

    EditText edtTitle,edtDescription,edtLocation;
    Bundle bundle;

    public static final int IMAGE_GALLERY = 100;
    public static final String IMAGE_KEY = "image";
    public static final String IMAGE_URL_UPLOAD = "fileUrl";

    private String fileUrl,userId,uploadUID;
    private String title,description,location,category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        getSupportActionBar().setTitle("Share Holiday");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageWisata = findViewById(R.id.image_from_gallery);
        buttonUpload = findViewById(R.id.button_upload);
        buttonUpload.setOnClickListener(this);

        edtTitle = findViewById(R.id.upload_judul);
        edtDescription = findViewById(R.id.deskripsi_upload);
        edtLocation = findViewById(R.id.upload_lokasi);
        rgCategory = findViewById(R.id.rg_category_upload);

        bundle = getIntent().getExtras();
        byte[] bytes = bundle.getByteArray(IMAGE_KEY);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        fileUrl = bundle.getString(IMAGE_URL_UPLOAD);
        imageWisata.setImageBitmap(bitmap);

    }


    private void uploadWisata(){
        uploadUID = UUID.randomUUID().toString();
        title = edtTitle.getText().toString();
        description = edtDescription.getText().toString();
        location = edtLocation.getText().toString();

        int idCategory = rgCategory.getCheckedRadioButtonId();
        rbSelectedCategory = findViewById(idCategory);

        category = rbSelectedCategory.getText().toString();

        ApiInterface apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        UploadDataModel model = new UploadDataModel(title,location,category,description,uploadUID,fileUrl);
        Call<ApiResponse> uploadDataModelCall = apiInterface.getUpload(model);

        uploadDataModelCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Upload Succes! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

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
        if (view.getId() == R.id.button_upload){
            uploadWisata();
        }
    }



}
