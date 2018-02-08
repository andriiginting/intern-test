package com.andriginting.internshiptest.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.model.WisataModel;
import com.andriginting.internshiptest.model.WisataResponse;
import com.andriginting.internshiptest.network.ApiClient;
import com.andriginting.internshiptest.network.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;

    ImageView imageCollapsing;
    TextView titleDetail;
    TextView locationDetail;
    TextView categoryDetail;
    TextView descriptionDetail;

    String itemIdWisata;
    WisataModel wisataModel;
    public static String ITEM_ID = "itemId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_wisata);
        toolbar = findViewById(R.id.toolbar_wisata);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        imageCollapsing = findViewById(R.id.image_collapsing_wisata);
        titleDetail = findViewById(R.id.title_detail_wisata);
        locationDetail = findViewById(R.id.location_detail_wisata);
        descriptionDetail = findViewById(R.id.description_detail_wisata);
        categoryDetail = findViewById(R.id.category_detail_wisata);

        itemIdWisata = getIntent().getStringExtra(ITEM_ID);

        getDetailData();
    }

    private void getDetailData() {

        final ApiInterface apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        Call<WisataResponse> apiResponseCall = apiInterface.getDetailData(itemIdWisata);

        apiResponseCall.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.body().status == "true") {
                    wisataModel = response.body().listDataWisata[0];

                    titleDetail.setText(wisataModel.getTitle());
                    collapsingToolbarLayout.setTitle(wisataModel.getTitle());
                    locationDetail.setText("Location : "+wisataModel.getLocation());
                    categoryDetail.setText("Category : "+wisataModel.getCategory());
                    descriptionDetail.setText(wisataModel.getDescription());

                    Picasso.with(getApplicationContext())
                            .load(wisataModel.getImageUrl())
                            .placeholder(R.color.cardview_shadow_start_color)
                            .into(imageCollapsing);
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
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
}
