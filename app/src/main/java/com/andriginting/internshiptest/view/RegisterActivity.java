package com.andriginting.internshiptest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.model.ApiResponse;
import com.andriginting.internshiptest.model.RegisterModel;
import com.andriginting.internshiptest.network.ApiClient;
import com.andriginting.internshiptest.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity
implements View.OnClickListener{

    EditText edtFirstName, edtLastName,edtUsername,edtPassword,edtBdate,edtPhone;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnRegister;

    String firstname,lastname,username,password,bdate,phone,gender;
    int idGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        edtFirstName = findViewById(R.id.edtFirst_name_user);
        edtLastName = findViewById(R.id.edtLast_name_user);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtBdate = findViewById(R.id.edtBirth_date);
        edtPhone = findViewById(R.id.edtPhone_number);

        radioGroup = findViewById(R.id.gender);

        btnRegister = findViewById(R.id.button_register);
        btnRegister.setOnClickListener(this);

    }


    private void userSignUp(){
        firstname = edtFirstName.getText().toString();
        lastname = edtLastName.getText().toString();
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();
        bdate = edtBdate.getText().toString();
        phone = edtPhone.getText().toString();
        gender = "";
        idGender = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(idGender);

        if (radioButton != null){
            gender = radioButton.getText().toString();
        }

        if (TextUtils.isEmpty(firstname)
                || TextUtils.isEmpty(lastname)
                || TextUtils.isEmpty(username)
                || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(bdate)
                || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Field Empty ! ", Toast.LENGTH_SHORT).show();
        } else {
            ApiInterface registerService = ApiClient.getRetrofitClient().create(ApiInterface.class);
            RegisterModel registerModel = new RegisterModel(firstname,lastname,username,password,bdate,gender,phone);

            Call<ApiResponse> registerCall = registerService.getRegister(registerModel);
            registerCall.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()){
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }else {
                        Toast.makeText(getBaseContext(), response.body().message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("error",t.getMessage());
                }
            });
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_register : {
                userSignUp();
                break;
            }
        }
    }
}
