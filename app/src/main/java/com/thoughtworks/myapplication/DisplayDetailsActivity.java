package com.thoughtworks.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtworks.myapplication.domain.PM25;

import java.io.File;

import retrofit.http.Url;

public class DisplayDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        PM25 pm25 =(PM25)intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        showTheDetails(pm25);
    }

    private void showTheDetails(PM25 pm25){
        TextView cityText = (TextView)findViewById(R.id.text_city_name);
        TextView positionText = (TextView)findViewById(R.id.text_position_name);
        TextView qualityText = (TextView)findViewById(R.id.text_quality);
        TextView timeText = (TextView)findViewById(R.id.text_time);
        ImageView motionImg = (ImageView)findViewById(R.id.motion_picture);

        cityText.setText(pm25.getArea());
        positionText.setText(pm25.getPositionName());
        qualityText.setText(pm25.getQuality());
        timeText.setText(pm25.getTime());

        switch (pm25.getQuality()){
            case "优":
                motionImg.setImageResource(R.mipmap.face_one);
                break;
            case "良":
                motionImg.setImageResource(R.mipmap.face_two);
                break;
            case "轻度污染":
                motionImg.setImageResource(R.mipmap.face_three);
                break;
            case "中度污染":
                motionImg.setImageResource(R.mipmap.face_four);
                break;
            case "重度污染":
                motionImg.setImageResource(R.mipmap.face_five);
                break;
            case "严重污染":
                motionImg.setImageResource(R.mipmap.face_six);
                break;
        }

    }

}
