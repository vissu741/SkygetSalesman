 package com.skyget.counsellor.views.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.ActivityMainBinding;
import com.skyget.counsellor.databinding.PopupShowAccountDetailsBinding;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.views.fragments.AssignedStudentsFragment;
import com.skyget.counsellor.views.fragments.LinkingAmountFragment;
import com.skyget.counsellor.views.fragments.MyAccountFragment;
import com.skyget.counsellor.views.fragments.MynewStudents;
import com.skyget.counsellor.views.fragments.NewHomeFragment;
import com.skyget.counsellor.views.fragments.NewstudentFragement;
import com.skyget.counsellor.views.fragments.SalesPaymentFragment;
import com.skyget.counsellor.views.fragments.SettlementFragment;

import java.io.File;


public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding activityMainBinding;
    private Animation rotate_forward, rotate_backward;
    int width, height, salesman_id;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView recyclerView;

    SharedPreferences myPrefs;
    SharedPreferences.Editor editor;
    String salesman_name;


    AlertDialog dialog;
    TextView textviewfornewstudents;
    TextView textview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        myPrefs = getSharedPreferences(Constants.LOGIN_SHARED, Context.MODE_PRIVATE);
        editor = myPrefs.edit();

        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);
        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "salesman");




            // statuBarColor(getResources().getColor(R.color.new_color));

        DisplayMetrics mertics = this.getResources().getDisplayMetrics();
        width = mertics.widthPixels;
        height = mertics.heightPixels;
        //intilise the session manager
        activityMainBinding.toolbar.getLayoutParams().width = width;
        setSupportActionBar(activityMainBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        textviewfornewstudents=findViewById(R.id.new_customers_Txt);
        textview =findViewById(R.id.my_new_list_Txt);


        setupDrawer();
        setUpDrawerItems();

        activityMainBinding.includeNav.profileName.setText(salesman_name);

        activityMainBinding.includeNav.profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);
                PopupShowAccountDetailsBinding popupGetSalesReportsBinding = DataBindingUtil.inflate(LayoutInflater.from(NavigationActivity.this), R.layout.popup_show_account_details, null, false);
                dialog = new AlertDialog.Builder(NavigationActivity.this).create();
                dialog.setView(popupGetSalesReportsBinding.getRoot());
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                popupGetSalesReportsBinding.tbnCancelMissed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
                popupGetSalesReportsBinding.testName.setText("Hi " + salesman_name);

                popupGetSalesReportsBinding.mobileView.setText(myPrefs.getString(Constants.SALESMAN_MOB_NO, ""));
                popupGetSalesReportsBinding.cityView.setText(myPrefs.getString(Constants.SALESMAN_CITY, ""));
                popupGetSalesReportsBinding.stateView.setText(myPrefs.getString(Constants.SALESMAN_STATE, ""));
                popupGetSalesReportsBinding.countryView.setText(myPrefs.getString(Constants.SALESMAN_COUNTRY, ""));
                popupGetSalesReportsBinding.addressView.setText(myPrefs.getString(Constants.SALESMAN_ADDRESS1, ""));
                popupGetSalesReportsBinding.bankView.setText(myPrefs.getString(Constants.SALESMAN_BANK_DETAILS, ""));

                dialog.show();

            }
        });

        txtGradient(activityMainBinding.includeNav.aplyStudentDiscTxt);
        txtGradient(activityMainBinding.includeNav.salesPaymentTxt);
        txtGradient(activityMainBinding.includeNav.assignedStdsTxt);
        txtGradient(activityMainBinding.includeNav.linkingTxt);
        txtGradient(activityMainBinding.includeNav.myAccountTxt);
        txtGradient(activityMainBinding.includeNav.settlementTxt);
        txtGradient(activityMainBinding.includeNav.myNewListTxt);
        txtGradient(activityMainBinding.includeNav.tvLogOutText);
        txtGradient(activityMainBinding.includeNav.newCustomersTxt);


        activityMainBinding.includeNav.profileName.setText(salesman_name);

        showSelectedIcon(1);
        activityMainBinding.includeNav.aplyStudentDiscLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));


        // int fragment_type = getIntent().getIntExtra(Constants.FRAGMENT_TYPE, 11);

        setNewHomeFragment();

    }

    public void statuBarColor(int color) {

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(color);
        }
    }

    void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, activityMainBinding.drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
    }

    private void setUpDrawerItems() {

        activityMainBinding.includeNav.aplyStudentDiscLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.salesPaymentLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.assignedStdsLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.linkingLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.myAccountLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.settlementLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.logoutLinLay.setOnClickListener(this);
        activityMainBinding.includeNav.newCustomersTxt.setOnClickListener(this);
        activityMainBinding.includeNav.myNewListTxt.setOnClickListener(this);


    }


    public void showSelectedIcon(int i) {
        setInvisible();

        switch (i) {
            case 1:
                activityMainBinding.includeNav.aplyStudentDiscView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.aplyStudentDiscArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.aplyStudentDiscLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;

            case 2:
                activityMainBinding.includeNav.salesPaymentView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.salesPaymentArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.salesPaymentLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;


            case 3:
                activityMainBinding.includeNav.assignedStdsView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.assignedStdsArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.assignedStdsLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;

            case 4:
                activityMainBinding.includeNav.myAccountView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.myAccountArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.myAccountLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;

            case 5:
                activityMainBinding.includeNav.logoutView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.logoutArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.logoutLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;

            case 6:
                activityMainBinding.includeNav.linkingView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.linkingArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.linkingLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;

            case 7:
                activityMainBinding.includeNav.settlementView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.settlementArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.settlementLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;
            case 8:
                activityMainBinding.includeNav.newCustomersView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.newCustomersArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.newCustomersLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;
            case 9:
                activityMainBinding.includeNav.myNewListView.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.myNewListArrow.setVisibility(View.VISIBLE);
                activityMainBinding.includeNav.myNewListLinLay.setBackgroundColor(Color.parseColor("#F0E5FD"));
                break;


            default:
                break;
        }
    }

    public void setInvisible() {

        activityMainBinding.includeNav.aplyStudentDiscView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.aplyStudentDiscArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.aplyStudentDiscLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.salesPaymentView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.salesPaymentArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.salesPaymentLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.assignedStdsView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.assignedStdsArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.assignedStdsLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.linkingView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.linkingArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.linkingLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.myAccountView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.myAccountArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.myAccountLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.logoutView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.logoutArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.logoutLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.settlementView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.settlementArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.settlementLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));


        activityMainBinding.includeNav.newCustomersView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.newCustomersArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.newCustomersLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

        activityMainBinding.includeNav.myNewListView.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.myNewListArrow.setVisibility(View.INVISIBLE);
        activityMainBinding.includeNav.myNewListLinLay.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }


    private void setNewHomeFragment() {
        showSelectedIcon(1);
        activityMainBinding.menuNeme.setText("Apply Student Discount");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.ic_student_discount);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new NewHomeFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }

    private void setTakenDouFragment() {
        showSelectedIcon(2);
        activityMainBinding.menuNeme.setText("Counsellor Reports");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.ic_sales_report);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new SalesPaymentFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }

    private void setAssignedStdsFragment() {
        showSelectedIcon(3);
        activityMainBinding.menuNeme.setText("Assigned Students");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.ic_assigned_students);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new AssignedStudentsFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }

    private void setMyAccountFragment() {
        showSelectedIcon(4);
        activityMainBinding.menuNeme.setText("My Account");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.ic_profile_icon_new);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccountFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }

    private void setLinkingFragment() {
        showSelectedIcon(6);
        activityMainBinding.menuNeme.setText("Linking Amount");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.affiliate);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new LinkingAmountFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }

    private void setSettleFragment() {
        showSelectedIcon(7);
        activityMainBinding.menuNeme.setText("Settlement");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.affiliate);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new SettlementFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }
    private void newstudents() {
        showSelectedIcon(8);
        activityMainBinding.menuNeme.setText("new students");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.affiliate);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay,new NewstudentFragement())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }
    private void  MynewStudents() {
        showSelectedIcon(9);
        activityMainBinding.menuNeme.setText("My New List");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.affiliate);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
//                .commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay,new MynewStudents())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }




    private void setLogout() {
        //showSelectedIcon(3);
        /*activityMainBinding.menuNeme.setText("My Account");
        activityMainBinding.imgToolbar.setImageResource(R.drawable.ic_profile_icon_new);
        statuBarColor(getResources().getColor(R.color.active_status_bg));
        activityMainBinding.toolbar.setBackgroundResource(R.drawable.custom_tool_bg);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_framelay, new MyAccontFragment())
                .commit();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);*/
        SharedPreferences preferences = getSharedPreferences(Constants.LOGIN_SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        deleteCache(this);
        Intent login_intent = new Intent(getApplicationContext(), LoginActivity.class);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login_intent);
        finishAffinity();
        activityMainBinding.drawerLayout.closeDrawer(activityMainBinding.sideview);

    }


    public void txtGradient(TextView textView) {
        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#B466C9"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#A5A8D8"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#63A8CC"),}, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }

    @Override
    public void onBackPressed() {
        backButton();
    }

    private void backButton() {

        /*String backMsg = "Hey " + studentName + ", Do you want to exit";

        final View dialogBack = LayoutInflater.from(this).inflate(R.layout.navigation_backpressed_dialog, null);
        TextView msg = dialogBack.findViewById(R.id.name);
        msg.setText(backMsg);

        backAlert = new android.app.AlertDialog.Builder(NavigationActivity.this).create();
        backAlert.setView(dialogBack);

        backAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView btnYes = dialogBack.findViewById(R.id.btnYes);
        TextView btnNo = dialogBack.findViewById(R.id.btnNo);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAlert.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAlert.dismiss();
                NavigationActivity.this.finish();
            }
        });
        backAlert.show();*/

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Drawable overflowIcon = activityMainBinding.toolbar.getOverflowIcon();

        if (overflowIcon != null) {
            Drawable newIcon = overflowIcon.mutate();
            newIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
            activityMainBinding.toolbar.setOverflowIcon(newIcon);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.profileimglayout:
                setMyAccountFragment();
                break;

            case R.id.aply_student_disc_lin_lay:

                setNewHomeFragment();
                break;

            case R.id.sales_payment_lin_lay:

                setTakenDouFragment();
                break;

            case R.id.assigned_stds_lin_lay:

                setAssignedStdsFragment();
                break;
            case R.id.linking_lin_lay:

                setLinkingFragment();
                break;
            case R.id.my_account_lin_lay:

                setMyAccountFragment();
                break;
            case R.id.settlement_lin_lay:
                setSettleFragment();
                break;

            case R.id.new_customers_Txt:
                newstudents();
                break;
            case R.id.my_new_list_Txt:
                MynewStudents();
                break;

            case R.id.logout_lin_lay:

                setLogout();
                break;
            default:
                break;
        }


    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        }
        return false;
    }

}