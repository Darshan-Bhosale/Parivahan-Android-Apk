package com.example.debaleen.project2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewChallan extends AppCompatActivity {
    EditText pname, chanu, carno, ownname, lno, Date;
    Spinner ruleSpinner, amountSpinner;
    Button sumbitButton, backButton;

    int ruleposition, amountStatus;

    String id,CID;
    String policeName,challanNumber,vehicleNumber,ownerName,licenseNumber,date;
    DataBaseHelper dataBaseHelper;

    String[] violateRuleChooser ={"Select Rule Violated","Riding or driving Without License= ₹2000","Riding or driving an unregistered vehicle= ₹3000","Violating speed regulation= ₹2000","Driving an uninsured vehicle= ₹1000","Not wearing seatbelt while driving= ₹500","Blocking the way of emergency vehicles= ₹1000","Driving rashly or dangerously= ₹5000"};
    String[] amountChooser={"Amount Status","Paid","Pending"};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_challan);
        pname= findViewById(R.id.pname);
        chanu = findViewById(R.id.chaNo);
        carno= findViewById(R.id.vno);
        ownname= findViewById(R.id.ownername);
        lno= findViewById(R.id.lno);
        Date= findViewById(R.id.date);
        ruleSpinner= findViewById(R.id.violate_rule);
        amountSpinner= findViewById(R.id.paid_pending);
        sumbitButton= findViewById(R.id.btnSubmit);
        backButton= findViewById(R.id.btnback);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        CID = getIntent().getExtras().getString("CID");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, violateRuleChooser);
        ruleSpinner.setAdapter(typeAdapter);

        ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ruleposition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> amountAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, amountChooser);
        amountSpinner.setAdapter(amountAdapter);

        amountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                amountStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                policeName = pname.getText().toString();
                challanNumber = chanu.getText().toString();
                vehicleNumber = carno.getText().toString();
                ownerName = ownname.getText().toString();
                licenseNumber = lno.getText().toString();
                date = Date.getText().toString();

                String ownnameRegex = "^[\\p{L} .'-]+$";
                String carnoRegex = "^[A-Z]{2}[0-9]{1,2}(?:A-Z)?(?:[A-Z]*)?[0-9]{4}$";

                Pattern patternOwnerName = Pattern.compile(ownnameRegex);
                Pattern patternCarNumber = Pattern.compile(carnoRegex);

                Matcher ownnameMatcher = patternOwnerName.matcher(ownerName);
                Matcher carnoMatcher = patternCarNumber.matcher(vehicleNumber);

                if(!ownnameMatcher.matches())
                {
                    FancyToast.makeText(getApplicationContext(), "Please enter a valid name.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (!carnoMatcher.matches()) {
                    FancyToast.makeText(getApplicationContext(), "Please enter a valid vehicle number.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if(policeName.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please enter a police name.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (challanNumber.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please enter a Challan Number.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (vehicleNumber.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please enter a Vehicle Number.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (ownerName.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please enter a Owner Name.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (licenseNumber.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please enter a License Number.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (date.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please enter a valid Date.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (ruleposition == 0) {
                    FancyToast.makeText(getApplicationContext(), "Please select rule violence.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (amountStatus == 0) {
                    FancyToast.makeText(getApplicationContext(), "Please select Amount Status.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if(flag)
                {
                    boolean regStatus = dataBaseHelper.insertIntoT4(policeName,challanNumber,vehicleNumber,ownerName,licenseNumber,date,violateRuleChooser[ruleposition],amountChooser[amountStatus]);
                    if (regStatus){
                        FancyToast.makeText(getApplicationContext(), "Challan Form Successful!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(),EChallan.class);
                                startActivity(i);
                                finish();
                            }
                        }, 1400);
                    } else {
                        FancyToast.makeText(getApplicationContext(), "Challan Form Failed!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EChallan.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });
    }
}