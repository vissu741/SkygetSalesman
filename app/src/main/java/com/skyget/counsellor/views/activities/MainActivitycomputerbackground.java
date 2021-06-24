package com.skyget.counsellor.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyget.counsellor.R;

public class MainActivitycomputerbackground extends AppCompatActivity {

    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    ImageView Iv1,Iv2,Iv3,Iv4,Iv5,Iv6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitycomputerbackground);
        textView1=findViewById(R.id. Tvaskdoubt);
        textView2=findViewById(R.id.Tv_viewposteddoubts);
        textView3=findViewById(R.id.Tv_subject);
        textView4=findViewById(R.id.TvUploadimage);
        textView5=findViewById(R.id. Tv_typeyourdoubt);
        textView6=findViewById(R.id.Tv_submit);
        textView7=findViewById(R.id.TvActive);
        textView8=findViewById(R.id.TvResults);
        textView9=findViewById(R.id.TvTakeTest);

        Iv1=findViewById(R.id.imageview1);
        Iv2=findViewById(R.id.imageview2);
        Iv3=findViewById(R.id.imageview3);
        Iv4=findViewById(R.id.imageviewbutton1);
        Iv5=findViewById(R.id.imageviewbutton2);
       // textView3.setPaintFlags(textView3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

    }
}