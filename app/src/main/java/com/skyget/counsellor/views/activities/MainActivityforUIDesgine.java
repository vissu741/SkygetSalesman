 package com.skyget.counsellor.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyget.counsellor.R;

public class MainActivityforUIDesgine extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13,textView14,textView15;
    ImageView Iv1,Iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityfor_u_i_desgine);
        textView1=findViewById(R.id.textview_Jee);
        textView2=findViewById(R.id.TvsubjectWiseTest);
        textView3=findViewById(R.id.TvSequenceandSeries);
        textView4=findViewById(R.id.TvMarks);
        textView5=findViewById(R.id. TvMathematics);
        textView6=findViewById(R.id.Tv_AllindialiveTest);
        textView7=findViewById(R.id.TvActive);
        textView8=findViewById(R.id.TvResults);
        textView9=findViewById(R.id.TvTakeTest);
        textView10=findViewById(R.id.TvTopper);
        textView11=findViewById(R.id.Tvno_ofAspirents);
        textView12=findViewById(R.id.TvTopscore);
        textView13=findViewById(R.id.Tvmonth);
        textView14=findViewById(R.id.date);
        textView15=findViewById(R.id.Tvtime);
        Iv1=findViewById(R.id.image1);
        Iv2=findViewById(R.id.image2);


    }
}