package com.andriginting.internshiptest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andriginting.internshiptest.R;
import com.andriginting.internshiptest.model.ApiResponse;
import com.andriginting.internshiptest.model.LoginModel;
import com.andriginting.internshiptest.network.ApiClient;
import com.andriginting.internshiptest.network.ApiInterface;
import com.andriginting.internshiptest.util.UserPreference;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener {

    Button buttonLogin;
    TextView registerText;
    EditText edtUsernameLogin;
    EditText edtPasswordLogin;
    RelativeLayout relativeLayout;

    UserPreference userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);

        registerText = findViewById(R.id.text_register);
        registerText.setOnClickListener(this);

        edtUsernameLogin = findViewById(R.id.username_login);
        edtPasswordLogin = findViewById(R.id.password_login);

        //untuk nyimpen state bahwa user sudah login
        userPreference = new UserPreference(this);
        if (userPreference.alreadyLogin()) {
            startActivity(new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    //convert password menggunakan MD5
    private String convertToMD(String pass) {
        String password = null;
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, messageDigest.digest()).toString(16);

            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    private void checkUserLogin(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Username/Password Empty ! ", Toast.LENGTH_SHORT).show();
        } else {
            ApiInterface apiService = ApiClient.getRetrofitClient().create(ApiInterface.class);
            LoginModel loginModel = new LoginModel(username, password);
            Call<ApiResponse> responseCall = apiService.getLogin(loginModel);

            responseCall.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.body().status == "true") {
                        userPreference.setPrefUsername(UserPreference.PREF_NAME,edtUsernameLogin.getText().toString());
                        userPreference.isLogin(UserPreference.PREF_IS_LOGIN,true);
//                        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(loginIntent);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    } else if (response.body().status == "false") {
                        Toast.makeText(LoginActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login: {
                String username = edtUsernameLogin.getText().toString();
                String passwordLoginMDFive = convertToMD(edtPasswordLogin.getText().toString());

                Log.d("username",username);
                Log.d("password",passwordLoginMDFive);
                checkUserLogin(username, passwordLoginMDFive);
                break;
            }
            case R.id.text_register: {
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
            }
        }
    }
}
