package com.example.alphazontask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    Activity activity;
    int currentItemCount =1000;
    public ImageAdapter(MainActivity mainActivity, int currentItemCount) {
        activity =mainActivity;
        this.currentItemCount = currentItemCount;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.image_single_view, null);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        Picasso.get()
                .load("https://www.pixelstalk.net/wp-content/uploads/2016/09/Very-Cool-Wallpapers-HD.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .resize(500,500)
                .into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(activity,DetailScreen.class);
                intent.putExtra("URL", "https://www.pixelstalk.net/wp-content/uploads/2016/09/Very-Cool-Wallpapers-HD.jpg");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentItemCount;
    }

    static class ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);




        }
    }
}
