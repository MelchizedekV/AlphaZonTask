package com.example.alphazontask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    int mTotalItemsCount =1000;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    ImageAdapter customAdapter;
    boolean isLoading=false;
    int mPageSize=10;
    int currentItemCount = 10;
    int visibleItemCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         recyclerView = (RecyclerView) findViewById(R.id.imageList);
         gridLayoutManager = new GridLayoutManager(this,2);
         recyclerView.setLayoutManager(gridLayoutManager);
         customAdapter = new ImageAdapter(MainActivity.this,currentItemCount);
         recyclerView.setAdapter(customAdapter);
         callPagination();

    }

    private void callPagination() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    if ((!isLoading && gridLayoutManager.findFirstCompletelyVisibleItemPosition()>= 1) && currentItemCount < mTotalItemsCount) {
                         isLoading = true;
//                         mPageIndex = returnPageIndex(itemsList.size());
                         loadMoreItems();
                    }
                }
            }
        });
    }

    private void loadMoreItems() {

//         mLoadMoreProgressbar.setVisibility(GONE);
        currentItemCount = currentItemCount+10;
        customAdapter.notifyDataSetChanged();
        isLoading = false;

    }




}