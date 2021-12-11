package com.example.hocretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.hocretrofit.API.ListUserApiService;
import com.example.hocretrofit.adapter.UserAdapter;
import com.example.hocretrofit.item_on_click.ItemOnClick;
import com.example.hocretrofit.model.ListUsers;
import com.example.hocretrofit.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUser extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    RecyclerView rcv_user;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<User>users=new ArrayList<>();
    UserAdapter adapter=new UserAdapter(ListUser.this, new ItemOnClick() {
        @Override
        public void onClickItemListener(User user) {

            Intent intent=new Intent(ListUser.this,UserDetails.class);
            intent.putExtra("id",user.getId());
            startActivity(intent);
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        addControls();
        addEvents();

    }

    private void addEvents() {

    }

    private void addControls() {
        swipeRefreshLayout =findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        ProgressDialog dialog=new ProgressDialog(ListUser.this);
        dialog.setMessage("Loading...");
        dialog.show();
        rcv_user=findViewById(R.id.rcv_user);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcv_user.addItemDecoration(itemDecoration);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcv_user.setLayoutManager(linearLayoutManager);
        ListUserApiService.LIST_USER_API_SERVICE.getListUser().enqueue(new Callback<ListUsers>() {
            @Override
            public void onResponse(Call<ListUsers> call, Response<ListUsers> response) {
                users=response.body().getData();
                adapter.setData(users);
                rcv_user.setAdapter(adapter);
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<ListUsers> call, Throwable t) {
                Toast.makeText( ListUser.this, "LOI",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onRefresh() {
        ListUserApiService.LIST_USER_API_SERVICE.getListUser().enqueue(new Callback<ListUsers>() {
            @Override
            public void onResponse(Call<ListUsers> call, Response<ListUsers> response) {
                users=response.body().getData();
                adapter.setData(users);
                rcv_user.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText( ListUser.this, "Refresh successfully",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ListUsers> call, Throwable t) {
                Toast.makeText( ListUser.this, "LOI",Toast.LENGTH_LONG).show();
            }
        });
    }
}