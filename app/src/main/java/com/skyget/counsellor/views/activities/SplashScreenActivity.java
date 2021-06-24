package com.skyget.counsellor.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.skyget.counsellor.R;
import com.skyget.counsellor.other.ConnectivityDetector;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2200;
    SharedPreferences userPreferences;
    //TextView version;
    ActivitySplashScreenBinding binding;

    public static int defaultScrenWidth = 800, defaultScrenHeit = 1280;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        statuBarColor(getResources().getColor(R.color.new_splash_status_color));

        // confiOneSingle();

        if (ConnectivityDetector.isConnectingToInternet(SplashScreenActivity.this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    userPreferences = getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
                    if (userPreferences.getBoolean(Constants.LOGIN_STATUS, false)) {
                        Intent homeIntent = new Intent(SplashScreenActivity.this, NavigationActivity.class);
                        startActivity(homeIntent);
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        Intent mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(mainIntent);
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }

                }

            }, SPLASH_DISPLAY_LENGTH);


        } else {
            String message;
            int color = Color.WHITE;

            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.constraint_lay), "Please check your Internet connection.", Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();

        }


    }


    public void statuBarColor(int color) {

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(color);
        }
    }
}