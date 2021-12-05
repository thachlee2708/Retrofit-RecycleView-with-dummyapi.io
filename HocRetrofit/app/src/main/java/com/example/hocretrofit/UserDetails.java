package com.example.hocretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hocretrofit.API.UserApiLoadFromID;
import com.example.hocretrofit.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetails extends AppCompatActivity {
    ImageView imgAva;
    TextView txtName, txtEmail,txtDOB, txtPhone, txtLocation;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        addControls();
        clickApiUser();
    }

    private void addControls() {
        imgAva=findViewById(R.id.img_ava);
        txtName=findViewById(R.id.txt_name);
        txtEmail=findViewById(R.id.txt_email);
        txtDOB=findViewById(R.id.txt_DOB);
        txtPhone=findViewById(R.id.txt_phone);
        txtLocation=findViewById(R.id.txt_location);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
    }

    private void clickApiUser() {
        ProgressDialog dialog=new ProgressDialog(UserDetails.this);
        dialog.setMessage("Loading...");
        dialog.show();
        UserApiLoadFromID.USER_API_SERVICE.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                assert user != null;
                Glide.with(UserDetails.this).load(user.getPicture()).into(imgAva);
                txtName.append(user.getFirstName()+" "+user.getLastName());
                txtEmail.append(user.getEmail());
                String DOB= user.getDateOfBirth().substring(0,9);
                txtDOB.append(DOB);
                txtPhone.append(user.getPhone());
                txtLocation.append("\n"+user.getLocation().toString());
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserDetails.this,"LOI",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}