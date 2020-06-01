package com.softxpert.softxperttask.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by µðšţãƒâ ™ on 01/06/2020.
 * ->
 */
public abstract class PaginationListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLayoutManager;

    public PaginationListener(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = mLayoutManager.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= 10
            ) {
                loadMoreItems();
            }
        }
    }


    public abstract void loadMoreItems();

    public abstract Boolean isLastPage();

    public abstract Boolean isLoading();
}
