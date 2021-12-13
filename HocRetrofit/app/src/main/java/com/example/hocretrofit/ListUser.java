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
import com.example.hocretrofit.API.ListUserApiServicePage;
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

    private boolean isLoading;
    private boolean isLastPage;
    private int totalPage=5;
    private int currentPage=1;
    ArrayList<User> list = new ArrayList<>();
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


        rcv_user=findViewById(R.id.rcv_user);
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcv_user.addItemDecoration(itemDecoration);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcv_user.setLayoutManager(linearLayoutManager);
        rcv_user.setAdapter(adapter);

        setFirstData();
        rcv_user.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading=true;
                currentPage+=1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage=1;
        adapter.setData(null);
        users.clear();
        list.clear();
        swipeRefreshLayout.setRefreshing(false);
        setFirstData();
    }
    private void loadNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<User>list=getListUser();
                adapter.removeFooterLoding();
                users.addAll(list);
                adapter.notifyDataSetChanged();
                isLoading=false;
                if (currentPage<totalPage)
                {
                    adapter.addFooterLoading();
                }
                else {
                    isLastPage=true;
                }
            }
        },2000);

    }
    //Load data page 0
    private void setFirstData(){
        users=getListUser();
        adapter.getData(users);
        if (currentPage<totalPage)
        {
            adapter.addFooterLoading();
        }
        else {
            isLastPage=true;
        }
    }
    private ArrayList<User>getListUser(){

        ListUserApiServicePage.LIST_USER_API_SERVICE_PAGE.getListUser((currentPage-1)+"").enqueue(new Callback<ListUsers>() {
            @Override
            public void onResponse(Call<ListUsers> call, Response<ListUsers> response) {
                list =response.body().getData();

            }
            @Override
            public void onFailure(Call<ListUsers> call, Throwable t) {
                Toast.makeText( ListUser.this, "LOI",Toast.LENGTH_LONG).show();

            }
        });
        return list;
    }
}