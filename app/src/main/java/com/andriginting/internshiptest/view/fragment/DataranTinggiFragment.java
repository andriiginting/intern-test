package com.andriginting.internshiptest.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.adapter.WisataAdapter;
import com.andriginting.internshiptest.model.WisataModel;
import com.andriginting.internshiptest.model.WisataResponse;
import com.andriginting.internshiptest.network.ApiClient;
import com.andriginting.internshiptest.network.ApiInterface;
import com.andriginting.internshiptest.view.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataranTinggiFragment extends Fragment {

    ArrayList<WisataModel> wisataModelArrayList;
    WisataAdapter adapter;
    RecyclerView recyclerView;


    public DataranTinggiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dataran_tinggi, container, false);
        recyclerView = v.findViewById(R.id.recyclerView_wisata_tinggi);
        getDataFromApi();

        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Dataran Tinggi");
        return v;
    }

    private void getDataFromApi() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        Call<WisataResponse> apiResponseCall = apiInterface.getKategoriWisata(MainActivity.TAG_KATEGORI_TINGGI);

        apiResponseCall.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.body().status == "true"){
                    WisataResponse apiResponse = response.body();
                    wisataModelArrayList = new ArrayList<>(Arrays.asList(apiResponse.getListData()));
                    adapter = new WisataAdapter(wisataModelArrayList,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {

            }
        });
    }
}
