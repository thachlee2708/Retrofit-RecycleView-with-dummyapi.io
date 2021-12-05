package com.example.hocretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hocretrofit.API.ListUserApiService;
import com.example.hocretrofit.API.UserApiService;
import com.example.hocretrofit.model.ListUsers;
import com.example.hocretrofit.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnUser,btnListUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickApiUser();
            }
        });
        btnListUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListUser.class);
                startActivity(intent);

            }
        });
    }

    private void clickApiUser() {
        UserApiService.USER_API_SERVICE.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                Toast.makeText(MainActivity.this, ""+user, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "LOI",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        btnUser=findViewById(R.id.btn_user);
        btnListUsers=findViewById(R.id.btn_list_users);
    }
}