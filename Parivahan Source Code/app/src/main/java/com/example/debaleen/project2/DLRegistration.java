package com.example.debaleen.project2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Locale;

public class DLRegistration extends AppCompatActivity {
    EditText DrivingLicense,HolderName,DLRelation,RegistrationDate,Validity,DOB,BloodGrp,VehicleClass,AuthorityC,Authority;
    Button backbtn, Submitbtn;
    String id,DLID;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_registration);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        DrivingLicense= findViewById(R.id.dlno);
        HolderName= findViewById(R.id.dlname);
        DLRelation= findViewById(R.id.relative);
        RegistrationDate= findViewById(R.id.regd);
        Validity= findViewById(R.id.dlval);
        DOB= findViewById(R.id.dldob);
        BloodGrp= findViewById(R.id.bgrp);
        VehicleClass= findViewById(R.id.dlvtype);
        AuthorityC= findViewById(R.id.dlautc);
        Authority= findViewById(R.id.dlaut);

        backbtn= findViewById(R.id.btnback);
        Submitbtn= findViewById(R.id.btnSubmit);

        id = getIntent().getExtras().getString("ID");

        Submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean flag = true;
                String dlno =DrivingLicense.getText().toString();
                String dlName =HolderName.getText().toString();
                String dlRelative =DLRelation.getText().toString();
                String dlregd =RegistrationDate.getText().toString();
                String dlVal =Validity.getText().toString();
                String dlDOB =DOB.getText().toString();
                String dlbgrp =BloodGrp.getText().toString();
                String dlvclass =VehicleClass.getText().toString();
                String dlAutC =AuthorityC.getText().toString();
                String dlAut =Authority.getText().toString();

                if(dlno.isEmpty() && dlName.isEmpty() && dlRelative.isEmpty() && dlregd.isEmpty() && dlVal.isEmpty() && dlDOB.isEmpty() && dlbgrp.isEmpty() && dlvclass.isEmpty() && dlAutC.isEmpty() && dlAut.isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please Enter the all data...", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    return;
                }

                if(dlno.isEmpty())
                {
                    FancyToast.makeText(getApplicationContext(), "Please enter Driving License No.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if(dlName.isEmpty() )
                {
                    FancyToast.makeText(getApplicationContext(), "Please enter Driving License Holder name", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (dlRelative.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the Relative Name.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if(dlregd.isEmpty())
                {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Registration Date.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;

                }
                if (dlVal.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Validity Date.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (dlDOB.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the Date Of Birth Date.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (dlbgrp.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the Blood Group.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (dlvclass.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Vehicle Class ", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if(dlAutC.isEmpty())
                {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Authority Code ", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if(dlAut.isEmpty())
                {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Authority RTO ", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (flag){
                    boolean regStatus =dataBaseHelper.insertIntoT3(dlno,dlName,dlRelative,dlregd,dlVal,dlDOB,dlbgrp,dlvclass,dlAutC,dlAut);
                    if (regStatus){
                        FancyToast.makeText(getApplicationContext(),"Registration Successfully!!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                                startActivity(i);
                                finish();
                            }
                        },1400);
                    }else{
                        FancyToast.makeText(getApplicationContext(), "Registration Failed!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                    }
                }
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });
    }
}
