package com.skyget.counsellor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDailyRecallDesgine extends AppCompatActivity {
    TextView textview1,textview2,textview3,textview4,textview5,textview6,textview7,textview8,textview9,textView10,textView11,textView12,textView13,textView14;
    ImageView imageview1,imageview2,imageview3,imageview4,imageView5,imageView6,imageView7,imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_recall_desgine);
        textview1 = findViewById(R.id.TvDailyRecall);
        textview2 = findViewById(R.id.TvImportent);
        textview3 = findViewById(R.id.Tvdate);
        textview4 = findViewById(R.id.TvTrigonomentry);
        textview5 = findViewById(R.id.TvTrigonomentryEquatuions);
        textview6 = findViewById(R.id.TvQuestions);
        textview7 = findViewById(R.id.Tvactive);
        textview8 = findViewById(R.id.TvRank);
        textview9 = findViewById(R.id.Tvone);
        textView10 = findViewById(R.id.Tvline);
        textView11 = findViewById(R.id.TvTake);
        imageview1 = findViewById(R.id.bulbimage);
        imageview2 = findViewById(R.id.classImage);
        imageview3 = findViewById(R.id.imagestrip);
        imageview4 = findViewById(R.id.Yellowstrip);

    }
}