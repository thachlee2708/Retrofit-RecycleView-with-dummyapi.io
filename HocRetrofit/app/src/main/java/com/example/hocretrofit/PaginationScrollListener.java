package com.example.hocretrofit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    LinearLayoutManager linearLayoutManager;

    public PaginationScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        int visibleItemsCount=linearLayoutManager.getChildCount();
        int totalItemCount=linearLayoutManager.getItemCount();
        int firstVisibleItemPosition=linearLayoutManager.findFirstVisibleItemPosition();

        if (isLoading()||isLastPage()){

        }
        else if (firstVisibleItemPosition>=0&&(visibleItemsCount+firstVisibleItemPosition)>= totalItemCount)
        {
            loadMoreItems();
        }
    }
    public abstract void loadMoreItems();
    public abstract boolean isLoading();
    public abstract boolean isLastPage();
}
