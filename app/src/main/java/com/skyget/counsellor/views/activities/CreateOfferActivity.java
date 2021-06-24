package com.skyget.counsellor.views.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.skyget.counsellor.R;
import com.skyget.counsellor.databinding.DoubtsHomeDialogBinding;
import com.skyget.counsellor.databinding.FragmentCreateOfferBinding;
import com.skyget.counsellor.model.request.CreateSalesOfferRequest;
import com.skyget.counsellor.model.request.GetWalletAmountRequest;
import com.skyget.counsellor.model.response.AllSubscriptionsResponse;
import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.GetWalletAmountResponse;
import com.skyget.counsellor.other.Constants;
import com.skyget.counsellor.presenters.implementations.CreateSalesOfferDataPresenterImpl;
import com.skyget.counsellor.presenters.implementations.GetAllSubscriptionsDataPresenterImpl;
import com.skyget.counsellor.presenters.implementations.GetWalletAmountDataPresenterImpl;
import com.skyget.counsellor.presenters.interfaces.ICreateSalesOfferDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ICreateSalesOfferMainView;
import com.skyget.counsellor.presenters.interfaces.IGetAllSubscriptionsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetAllSubscriptionsMainView;
import com.skyget.counsellor.presenters.interfaces.IGetWalletAmountDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetWalletAmountMainView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CreateOfferActivity extends AppCompatActivity implements /*IGetSubscriptionsMainView*/IGetAllSubscriptionsMainView, View.OnClickListener, ICreateSalesOfferMainView, IGetWalletAmountMainView {

    int salesman_id;
    FragmentCreateOfferBinding fragmentCreateOfferBinding;
    String salesman_name, selected_offer_type;

    int width, heigth, std_id, subscription_id, value_amount, percentage_amount;
    double subscription_amount, value_y;

    AlertDialog dialog;

    IGetAllSubscriptionsDataPresenter iGetSubscriptionsDataPresenter;
    ICreateSalesOfferDataPresenter iCreateSalesOfferDataPresenter;

    Map<String, Integer> plan_map = new HashMap<>();
    List<String> plans_list = new ArrayList<>();
    List<Double> plans_price_value = new ArrayList<>();
    List<String> offers_list = new ArrayList<>();

    IGetWalletAmountDataPresenter iGetWalletAmountDataPresenter;
    double total_wallet_value;
    int gst_value_percent;
    boolean emi_status;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    List<String> stringList;
    double you_pay_value_total;
    String sel_value_month;


    public CreateOfferActivity() {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fragmentCreateOfferBinding = DataBindingUtil.setContentView(this, R.layout.fragment_create_offer);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.new_color));
        }

        width = getWindowManager().getDefaultDisplay().getWidth();
        heigth = getWindowManager().getDefaultDisplay().getHeight();
        SharedPreferences myPrefs = getSharedPreferences(Constants.LOGIN_SHARED, MODE_PRIVATE);
        salesman_id = myPrefs.getInt(Constants.SALESMAN_ID, 0);
        emi_status = myPrefs.getBoolean(Constants.EMI_STATUS, false);

        salesman_name = myPrefs.getString(Constants.SALESMAN_NAME, "Salesman");

        value_amount = myPrefs.getInt(Constants.SALESMAN_VALUE, 0);
        percentage_amount = myPrefs.getInt(Constants.SALESMAN_PERCENTAGE, 0);

        std_id = getIntent().getIntExtra("std_id", 3365);


        /*std_mobile = myPrefs.getString(Constants.STUDENT_MOB_NO, "");
        std_image = myPrefs.getString(Constants.STUDENT_IMAGE, "");*/

        fragmentCreateOfferBinding.toolbarProfile.setTitle("Create Offer");
        setSupportActionBar(fragmentCreateOfferBinding.toolbarProfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GetWalletAmountRequest getWalletAmountRequest = new GetWalletAmountRequest();
        getWalletAmountRequest.studentId = std_id;

        if (emi_status) {
            fragmentCreateOfferBinding.addEmiOption.setVisibility(View.VISIBLE);
        } else {
            fragmentCreateOfferBinding.addEmiOption.setVisibility(View.GONE);
        }

        iGetSubscriptionsDataPresenter = new GetAllSubscriptionsDataPresenterImpl(this, this);
        iCreateSalesOfferDataPresenter = new CreateSalesOfferDataPresenterImpl(this, this);
        iGetWalletAmountDataPresenter = new GetWalletAmountDataPresenterImpl(this, this);
        iGetWalletAmountDataPresenter.getWalletAmount(getWalletAmountRequest);


        iGetSubscriptionsDataPresenter.getAllSubscriptionsDetails();

        fragmentCreateOfferBinding.btnSubmit.setOnClickListener(this);

        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                fragmentCreateOfferBinding.addEmiLayoutA.datepicker.setText(sdf.format(myCalendar.getTime()));
            }

        };


        fragmentCreateOfferBinding.addEmiLayoutA.datepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateOfferActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        String[] stdsArray = getResources().getStringArray(R.array.months_emi_list);

        List<String> stringList = new ArrayList<String>(Arrays.asList(stdsArray)); //new ArrayList is only needed if you absolutely need an ArrayList

        ArrayAdapter months_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringList);
        months_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragmentCreateOfferBinding.addEmiLayoutA.emiMonthsSpinner.setAdapter(months_adapter);

        fragmentCreateOfferBinding.addEmiOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragmentCreateOfferBinding.addEmiOption.isChecked()) {
                    fragmentCreateOfferBinding.addEmiLayoutA.addEmiLayout.setVisibility(View.VISIBLE);
                } else {
                    fragmentCreateOfferBinding.addEmiLayoutA.addEmiLayout.setVisibility(View.GONE);
                }
            }
        });


        fragmentCreateOfferBinding.planidSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String selected_plan = plans_list.get(position);

                //int pos_selected=plans_price_value.indexOf(selected_plan);

                if (position != 0) {
                    subscription_amount = plans_price_value.get(position);
                    subscription_id = plan_map.get(plans_list.get(position));
                    fragmentCreateOfferBinding.editSubscription.setText(subscription_amount + " ");
                    double you_pay_value1 = subscription_amount - total_wallet_value;
                    double you_pay_value2 = you_pay_value1 * gst_value_percent / 100;
                    you_pay_value_total = you_pay_value1 + you_pay_value2;
                    fragmentCreateOfferBinding.editgstamount.setText(df2.format(you_pay_value2) + "");
                    fragmentCreateOfferBinding.edittotalPay.setText(df2.format(you_pay_value_total) + "");

                } else {
                    subscription_amount = 0;
                    subscription_id = 0;
                    fragmentCreateOfferBinding.editSubscription.setText(" ");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fragmentCreateOfferBinding.offerTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_offer_type = offers_list.get(position);

                if (selected_offer_type != null) {
                   /* String value = fragmentCreateOfferBinding.editvaluePercValue.getText().toString();
                    if (value != null && !value.isEmpty()) {
                        if (selected_offer_type.equalsIgnoreCase("value")) {
                            int value_x = Integer.parseInt(value);
                            //value_y = subscription_amount - value_x;
                            value_y = value_x;
                            fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");
                        } else if (selected_offer_type.equalsIgnoreCase("percentage")) {
                            int value_x = Integer.parseInt(value);
                            value_y = subscription_amount * value_x / 100;
                            fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");
                        } else {*/
                    fragmentCreateOfferBinding.editvaluePercValue.setText("");
                    fragmentCreateOfferBinding.editOfferAmount.setText("");


                        /*}

                    } else {

                    }*/
                } else {
                    fragmentCreateOfferBinding.editOfferAmount.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fragmentCreateOfferBinding.editvaluePercValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (selected_offer_type != null) {
                    String value = fragmentCreateOfferBinding.editvaluePercValue.getText().toString();
                    if (value != null && !value.isEmpty()) {
                        if (selected_offer_type.equalsIgnoreCase("value")) {
                            int value_x = Integer.parseInt(value);
                            if (value_x <= value_amount) {
                                //value_y = subscription_amount - value_x;
                                value_y = value_x;
                                fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");
                                double you_pay_value1 = subscription_amount - value_y - total_wallet_value;
                                double you_pay_value2 = you_pay_value1 * gst_value_percent / 100;
                                you_pay_value_total = you_pay_value1 + you_pay_value2;
                                fragmentCreateOfferBinding.editgstamount.setText(df2.format(you_pay_value2) + "");
                                fragmentCreateOfferBinding.edittotalPay.setText(df2.format(you_pay_value_total) + "");
                                //fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");


                            } else {
                                DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(CreateOfferActivity.this), R.layout.doubts_home_dialog, null, false);
                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(CreateOfferActivity.this).create();
                                alertDialog.setView(doubtsHomeDialogBinding.getRoot());
                                alertDialog.setCancelable(false);
                                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                doubtsHomeDialogBinding.doubtsTxt.setVisibility(View.GONE);
                                doubtsHomeDialogBinding.doubtsNotifyTxt.setText("You can give offer upto " + value_amount + " value");
                                doubtsHomeDialogBinding.okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        fragmentCreateOfferBinding.editvaluePercValue.setText("");
                                        fragmentCreateOfferBinding.editOfferAmount.setText("");
                                        alertDialog.dismiss();
                                    }
                                });

                                doubtsHomeDialogBinding.tbnCancelWithdraw.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        fragmentCreateOfferBinding.editvaluePercValue.setText("");
                                        fragmentCreateOfferBinding.editOfferAmount.setText("");
                                        alertDialog.dismiss();
                                    }
                                });


                                alertDialog.show();
                            }

                        } else if (selected_offer_type.equalsIgnoreCase("percentage")) {
                            int value_x = Integer.parseInt(value);
                            if (value_x <= percentage_amount) {
                                value_y = subscription_amount * value_x / 100;
                                fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");
                                double you_pay_value1 = subscription_amount - value_y - total_wallet_value;
                                double you_pay_value2 = you_pay_value1 * gst_value_percent / 100;
                                you_pay_value_total = you_pay_value1 + you_pay_value2;
                                fragmentCreateOfferBinding.editgstamount.setText(df2.format(you_pay_value2) + "");
                                fragmentCreateOfferBinding.edittotalPay.setText(df2.format(you_pay_value_total) + "");


                            } else {
                                DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(CreateOfferActivity.this), R.layout.doubts_home_dialog, null, false);
                                final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(CreateOfferActivity.this).create();
                                alertDialog.setView(doubtsHomeDialogBinding.getRoot());
                                alertDialog.setCancelable(false);
                                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                                doubtsHomeDialogBinding.doubtsTxt.setVisibility(View.GONE);
                                doubtsHomeDialogBinding.doubtsNotifyTxt.setText("You can give offer upto " + percentage_amount + " percentage");
                                doubtsHomeDialogBinding.okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        fragmentCreateOfferBinding.editvaluePercValue.setText("");
                                        fragmentCreateOfferBinding.editOfferAmount.setText("");
                                        alertDialog.dismiss();
                                    }
                                });

                                doubtsHomeDialogBinding.tbnCancelWithdraw.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        fragmentCreateOfferBinding.editvaluePercValue.setText("");
                                        fragmentCreateOfferBinding.editOfferAmount.setText("");
                                        alertDialog.dismiss();
                                    }
                                });


                                alertDialog.show();
                            }


                        } else {

                        }

                    } else {
//                        fragmentCreateOfferBinding.editvaluePercValue.setText("");
//                        fragmentCreateOfferBinding.editOfferAmount.setText("");
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        fragmentCreateOfferBinding.addEmiLayoutA.emiMonthsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected_value_emi = stringList.get(position);

                if (position != 0) {
                    String see_value[] = selected_value_emi.split(" ");
                    sel_value_month = see_value[0];
                    int sel_value_month_1 = Integer.parseInt(sel_value_month);
                    String x_nn = fragmentCreateOfferBinding.addEmiLayoutA.editdownpayment.getText().toString();

                    if (x_nn != "" && x_nn != null) {

                        int value111 = Integer.parseInt(x_nn);
                        double value222 = (you_pay_value_total - value111) / sel_value_month_1;
                        fragmentCreateOfferBinding.addEmiLayoutA.editmonthlyPayment.setText(df2.format(value222) + "");
                    } else {
                        fragmentCreateOfferBinding.addEmiLayoutA.editmonthlyPayment.setText("");
                    }
                }

                if (selected_value_emi.equalsIgnoreCase("Select EMI Months")) {

                }

                // selected_offer_type = offers_list.get(position);

              /*  if (selected_offer_type != null) {
                    String value = fragmentCreateOfferBinding.editvaluePercValue.getText().toString();
                    if (value != null && !value.isEmpty()) {
                        if (selected_offer_type.equalsIgnoreCase("value")) {
                            int value_x = Integer.parseInt(value);
                            //value_y = subscription_amount - value_x;
                            value_y = value_x;
                            fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");
                        } else if (selected_offer_type.equalsIgnoreCase("percentage")) {
                            int value_x = Integer.parseInt(value);
                            value_y = subscription_amount * value_x / 100;
                            fragmentCreateOfferBinding.editOfferAmount.setText(value_y + "");
                        } else {
                            fragmentCreateOfferBinding.editvaluePercValue.setText("");
                            fragmentCreateOfferBinding.editOfferAmount.setText("");
                        }

                    } else {

                    }
                } else {
                    fragmentCreateOfferBinding.editOfferAmount.setText("");
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public void showProgress() {

        fragmentCreateOfferBinding.progressBarDoubts.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void hideProgress() {

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        fragmentCreateOfferBinding.progressBarDoubts.setVisibility(View.GONE);

    }

    @Override
    public void getWalletAmount(GetWalletAmountResponse resp) {

        if (resp != null) {
            gst_value_percent = resp.taxPercentage;
            total_wallet_value = resp.walletAmount + 2 * resp.refferalAmount;

            fragmentCreateOfferBinding.gstView.setText("GST(" + gst_value_percent + "%)");
            fragmentCreateOfferBinding.editwalletamount.setText(df2.format(total_wallet_value) + "");

        }

    }

    @Override
    public void getResult(DefaultSuccessResponse resp) {
        if (resp.status) {
            Intent intent = new Intent(CreateOfferActivity.this, NavigationActivity.class);
            startActivity(intent);
            finishAffinity();
        } else {
            Toast.makeText(CreateOfferActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void getResult(List<AllSubscriptionsResponse> resp) {

        fragmentCreateOfferBinding.salesPersonId.setText(salesman_id + "");

        if (plans_list.size() > 0) {
            plans_list.clear();
        }
        if (plan_map.size() > 0) {
            plan_map.clear();
        }

        if (offers_list.size() > 0) {
            offers_list.clear();
        }
        if (plans_price_value.size() > 0) {
            plans_price_value.clear();
        }
        plans_list.add("---Select Plan Id---");
        plans_price_value.add(0.00);
        offers_list.add("---Select Offer Type---");
        offers_list.add("Percentage");
        offers_list.add("Value");
        for (int i = 0; i < resp.size(); i++) {
            plan_map.put(resp.get(i).academicyear, resp.get(i).subscriptionId);
            plans_list.add(resp.get(i).academicyear);
            plans_price_value.add(resp.get(i).offerprice);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plans_list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        fragmentCreateOfferBinding.planidSpinner.setAdapter(dataAdapter);

        ArrayAdapter<String> offertype = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, offers_list);
        offertype.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        fragmentCreateOfferBinding.offerTypeSpinner.setAdapter(offertype);


    }


    @Override
    public void failure(String msg) {

        DoubtsHomeDialogBinding doubtsHomeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(CreateOfferActivity.this), R.layout.doubts_home_dialog, null, false);
        final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(CreateOfferActivity.this).create();
        alertDialog.setView(doubtsHomeDialogBinding.getRoot());
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        doubtsHomeDialogBinding.doubtsTxt.setVisibility(View.GONE);
        doubtsHomeDialogBinding.doubtsNotifyTxt.setText(msg);
        doubtsHomeDialogBinding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        doubtsHomeDialogBinding.tbnCancelWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        alertDialog.show();

 /*Log.e("Info", " Error" + msg);
        fragmentNewHomeBinding.noTextWatch.setVisibility(View.VISIBLE);
        fragmentNewHomeBinding.noTextWatch.setText(msg);*/


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:

                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

               /* String mobile_num=fragmentNewHomeBinding.editUsername.getText().toString();
                if(mobile_num!=null)
                {
                    mobile_num=mobile_num.trim();
                    iGetStudentDetailsDataPresenter.getStudentDetails(mobile_num);
                }*/

                if (subscription_id != 0) {
                    if (subscription_amount != 0.0 && subscription_amount > 0.0) {
                        if (value_y != 0.0 && value_y > 0.0) {
                            CreateSalesOfferRequest createSalesOfferRequest = new CreateSalesOfferRequest();
                            createSalesOfferRequest.studentId = std_id;
                            createSalesOfferRequest.planId = subscription_id;
                            createSalesOfferRequest.subscriptionAmount = subscription_amount;
                            createSalesOfferRequest.percentage = Integer.parseInt(fragmentCreateOfferBinding.editvaluePercValue.getText().toString());
                            createSalesOfferRequest.offerType = selected_offer_type.toLowerCase();
                            createSalesOfferRequest.offerAmount = value_y;
                            createSalesOfferRequest.salesPersonId = salesman_id;
                            createSalesOfferRequest.validityInhrs = Integer.parseInt(fragmentCreateOfferBinding.editUsernameHours.getText().toString());


                            createSalesOfferRequest.gstAmount = Double.parseDouble(fragmentCreateOfferBinding.editgstamount.getText().toString());
                            createSalesOfferRequest.walletAmount = Double.parseDouble(fragmentCreateOfferBinding.editwalletamount.getText().toString());
                            createSalesOfferRequest.totalAmountToBePaid = Double.parseDouble(fragmentCreateOfferBinding.edittotalPay.getText().toString());


                            if (fragmentCreateOfferBinding.addEmiOption.isChecked()) {
                                createSalesOfferRequest.emiAmount = Double.parseDouble(fragmentCreateOfferBinding.addEmiLayoutA.editmonthlyPayment.getText().toString());
                                createSalesOfferRequest.emiMonths = Integer.parseInt(sel_value_month);
                                createSalesOfferRequest.downPayment = Double.parseDouble(fragmentCreateOfferBinding.addEmiLayoutA.editdownpayment.getText().toString());
                                createSalesOfferRequest.everyMonthDueDate = fragmentCreateOfferBinding.addEmiLayoutA.datepicker.getText().toString();

                            } else {
                                createSalesOfferRequest.emiMonths = 0;
                                createSalesOfferRequest.downPayment = 0;
                                createSalesOfferRequest.everyMonthDueDate = "0";
                                createSalesOfferRequest.emiAmount = 0;

                            }


                            iCreateSalesOfferDataPresenter.createSalesOffer(createSalesOfferRequest);
                        } else {
                            Toast.makeText(this, "Please select value", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Please select the plan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please select the plan", Toast.LENGTH_SHORT).show();
                }


                break;
            default:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(CreateOfferActivity.this, NavigationActivity.class);
        startActivity(i);
        finishAffinity();
    }

   /* private void updateLabel() {

    }*/


}