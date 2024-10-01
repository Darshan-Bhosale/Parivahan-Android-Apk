package com.example.debaleen.project2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

public class DlAdd extends AppCompatActivity {
    EditText Dlno,Dlname,Dlrelative,Dlregdate,Dlval,Dldob,Dlbgrp,Dlvehicletype,DlauthorityC,Dlauthority;
    Button Backbtn,Submitbtn;
    String id;
    String dlno,dlName,dlRelative,dlregd,dlVal,dlDOB,dlbgrp,dlvclass,dlAuthC,dlAut;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_registration);

        dataBaseHelper = new DataBaseHelper(this);
        id = getIntent().getExtras().getString("ID");

        Dlno = findViewById(R.id.dlno);
        Dlname = findViewById(R.id.dlname);
        Dlrelative = findViewById(R.id.relative);
        Dlregdate = findViewById(R.id.regd);
        Dlval = findViewById(R.id.dlval);
        Dldob = findViewById(R.id.dldob);
        Dlbgrp = findViewById(R.id.bgrp);
        Dlvehicletype = findViewById(R.id.dlvtype);
        DlauthorityC = findViewById(R.id.dlautc);
        Dlauthority = findViewById(R.id.dlaut);

        Backbtn = findViewById(R.id.btnback);
        Submitbtn = findViewById(R.id.btnSubmit);

        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                dlno = Dlno.getText().toString();
                dlName = Dlname.getText().toString();
                dlRelative = Dlrelative.getText().toString();
                dlregd = Dlregdate.getText().toString();
                dlVal = Dlval.getText().toString();
                dlDOB = Dldob.getText().toString();
                dlbgrp = Dlbgrp.getText().toString();
                dlvclass = Dlvehicletype.getText().toString();
                dlAuthC = DlauthorityC.getText().toString();
                dlAut = Dlauthority.getText().toString();

                if (dlno.trim().isEmpty() || dlName.trim().isEmpty() || dlRelative.trim().isEmpty() ||
                dlregd.trim().isEmpty() || dlVal.trim().isEmpty() || dlDOB.trim().isEmpty() ||
                dlbgrp.trim().isEmpty() || dlvclass.trim().isEmpty() || dlAuthC.trim().isEmpty() ||
                dlAut.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(), "Please fill all fields.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlno.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a valid no", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlName.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a valid name", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlRelative.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a valid DL Relation Name", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlregd.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a valid Vehicle Registration date", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlVal.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a DL Validity Date", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag =false;
                }
                if (dlDOB.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a Dl Date Of Birth Datw", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlbgrp.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a Blood Group", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlvclass.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a Vehicle class", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlAuthC.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a valid Registration RTO Authority Code", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }
                if (dlAut.trim().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),"Please enter a valid Registration RTO Authority", FancyToast.LENGTH_SHORT,FancyToast.WARNING, false).show();
                    flag = false;
                }

                if(flag) {
                    dataBaseHelper = new DataBaseHelper(getApplicationContext());
                    boolean regStatus = dataBaseHelper.insertIntoT3(dlno,dlName,dlRelative,dlregd,dlVal,dlDOB,dlbgrp,dlvclass,dlAuthC,dlAut);

                    if (regStatus) {
                        FancyToast.makeText(getApplicationContext(), "Inserted successful!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                                i.putExtra("ID", id);
                                startActivity(i);
                                finish();
                            }
                        }, 1400);
                    } else {
                        FancyToast.makeText(getApplicationContext(), "Insertion Failed!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                    }
                }
            }
        });

        Backbtn.setOnClickListener(new View.OnClickListener() {
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
