package com.example.hocretrofit.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hocretrofit.MainActivity;
import com.example.hocretrofit.R;
import com.example.hocretrofit.item_on_click.ItemOnClick;
import com.example.hocretrofit.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    Context context;
    ArrayList<User> list;
    ItemOnClick itemOnClick;


    private static final int TYPE_ITEM=1;
    private static final int TYPE_LOADING=2;
    private boolean isLoadingAdd;

    @Override
    public int getItemViewType(int position) {
        if (list!=null&&position==list.size()-1&&isLoadingAdd){
            return TYPE_LOADING;
        }
        return TYPE_ITEM;
    }
    public void getData(ArrayList<User> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public UserAdapter(Context context) {
        this.context = context;
    }
    public UserAdapter(Context context, ItemOnClick itemOnClick) {
        this.context = context;
        this.itemOnClick=itemOnClick;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<User> list)
    {
        this.list=list;
        notifyDataSetChanged();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void addData(ArrayList<User> list)
    {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_ITEM){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_list_user,parent ,false);
            return new UserViewHolder(view);
        }
        else {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_loading,parent,false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user=list.get(position);
        if (holder.getItemViewType()==TYPE_ITEM)
        {
            Glide.with(context).load(user.getPicture()).into(holder.imgAva);
            holder.txtUserName.setText(user.getFirstName());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClick.onClickItemListener(user);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imgAva;
        TextView txtUserName;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAva=itemView.findViewById(R.id.img_ava);
            txtUserName=itemView.findViewById(R.id.txt_user_name);
            linearLayout=itemView.findViewById(R.id.layout_item);
        }
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.progress_circular);
        }
    }
    public void addFooterLoading(){
        isLoadingAdd=true;
        list.add(new User(""));
    }
    public void removeFooterLoding(){
        isLoadingAdd=false;
        int position=list.size()-1;
        User user=list.get(position);
        if (user!=null){
            list.remove(position);
            notifyItemRemoved(position);
        }
    }
}
