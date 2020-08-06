package com.example.alphazontask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailScreen extends AppCompatActivity {

    String imgUrl;

    ImageView fullImage;
    Button orginal,twentyFive,fifty,seventyFive;
    TextView txtqualityValue,txtWidthHeightvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        fullImage = findViewById(R.id.fullImage);

        orginal = findViewById(R.id.orginal);
        twentyFive = findViewById(R.id.twentyfivepercent);
        fifty = findViewById(R.id.fiftypercent);
        seventyFive = findViewById(R.id.seventypercent);
        txtqualityValue =findViewById(R.id.txtqualityValue);
        txtWidthHeightvalue=findViewById(R.id.txtWidthHeightvalue);
        imgUrl = getIntent().getStringExtra("URL");
        showImage(100);

        orginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(100);
            }
        });

        twentyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(25);
            }
        });

        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(50);
            }
        });

        seventyFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage(75);
            }
        });
    }




    private void showImage(final int percentage) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                URL url = null;
                Bitmap b=null;
                Bitmap bitmap=null;
                Bitmap decoded=null;
                try {
                    url = new URL(imgUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    b = BitmapFactory.decodeStream(url.openConnection()
                           .getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int origWidth = b.getWidth();
                int origHeight = b.getHeight();

                final int destWidth = 600;//or the width you need

                if (origWidth > destWidth) {
                    int destHeight = origHeight / (origWidth / destWidth);
                    bitmap = Bitmap.createScaledBitmap(b, destWidth, destHeight, false);
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, percentage, outStream);
                    decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(outStream.toByteArray()));



                }

                final Bitmap finalBitmap = decoded;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int width= finalBitmap.getHeight();
                        int height= finalBitmap.getWidth();
                        int byteCount=finalBitmap.getByteCount();

                        txtWidthHeightvalue.setText(width +"*"+height);
                        fullImage.setImageBitmap(finalBitmap);
                        txtqualityValue.setText(String.valueOf(byteCount));
                    }
                });
            }
        }).start();
    }
}