package com.skyget.counsellor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RoboticLearningDesgine extends AppCompatActivity {
    TextView textview1,textview2,textview3,textview4,textview5,textview6,textview7,textview8,textview9;
ImageView imageview1,imageview2,imageview3,imageview4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robotic_learning_desgine);

        textview1 = findViewById(R.id.TvRankGuarantee);
        textview2 = findViewById(R.id.TvRobotDriven);
        textview3 = findViewById(R.id.TvLesstime);
        textview4 = findViewById(R.id.TvPoweredby);
        textview5 = findViewById(R.id.Tvphysics);
        textview6 = findViewById(R.id.Tvchemistry);
        textview7 = findViewById(R.id.Tvbiolagy);
        textview8 = findViewById(R.id.TvRankGuarantee);
        textview9 = findViewById(R.id.TvRankGuarantee);
        imageview1 = findViewById(R.id.RoboImage);
        imageview2 = findViewById(R.id.physicsimage);
        imageview3 = findViewById(R.id.chemistryimage);
        imageview4 = findViewById(R.id.biolagyimage);
    }
}