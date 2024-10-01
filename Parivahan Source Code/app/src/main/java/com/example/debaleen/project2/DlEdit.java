package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class DlEdit extends AppCompatActivity {
    TextView dlno,dlregd,dlAuthC,dlAut;
    EditText dlName,dlRelative,dlVal,dlDOB,dlbgrp,dlvclass;
    Button backButton,updateButton;
    String id="", dlID="", lno="",lName="",lRelative="",lregd="",lVal="",lDOB="",lbgrp="",lvclass="",lAuthC="",lAut="",action="";

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_edit);

        dlno = findViewById(R.id.dlno);
        dlAuthC = findViewById(R.id.dlautc);
        dlAut = findViewById(R.id.dlaut);
        dlregd = findViewById(R.id.regd);

        dlName = findViewById(R.id.dlname);
        dlRelative = findViewById(R.id.relative);
        dlVal = findViewById(R.id.dlval);
        dlDOB = findViewById(R.id.dldob);
        dlbgrp = findViewById(R.id.bgrp);
        dlvclass = findViewById(R.id.dlvtype);

        backButton = findViewById(R.id.btnback);
        updateButton = findViewById(R.id.btnSubmit);

        id = getIntent().getStringExtra("ID");
        dlID = getIntent().getStringExtra("DLID");
        action = getIntent().getStringExtra("ACTION");
        if(action == null)
        {
            action = "NULL";
        }

        Cursor res = dataBaseHelper.displayByIdFormT3(dlID);

        if(!res.moveToNext())
        {
            FancyToast.makeText(getApplicationContext(), "Invalid DL ID!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
        else
        {
            lno = res.getString(1);
            lName = res.getString(2);
            lRelative = res.getString(3);
            lregd = res.getString(4);
            lVal = res.getString(5);
            lDOB = res.getString(6);
            lbgrp = res.getString(7);
            lvclass = res.getString(8);
            lAuthC = res.getString(9);
            lAut = res.getString(10);

            dlno.setText(String.valueOf(lno));
            dlregd.setText(String.valueOf(lregd));
            dlAuthC.setText(String.valueOf(lAuthC));
            dlAut.setText(String.valueOf(lAut));

            dlName.setText(String.valueOf(lName));
            dlRelative.setText(String.valueOf(lRelative));
            dlVal.setText(String.valueOf(lVal));
            dlDOB.setText(String.valueOf(lDOB));
            dlbgrp.setText(String.valueOf(lbgrp));
            dlvclass.setText(String.valueOf(lvclass));

        }
        backButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if(action.compareTo("EDIT")==0)
                {
                    Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                    i.putExtra("ID", id);
                    startActivity(i);
                    finish();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), ViewDllist.class);
                    i.putExtra("ID", id);
                    //i.putExtra("ACTION", "NULL");
                    startActivity(i);
                    finish();
                }
            }
        });
        updateButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                lName = dlName.getText().toString();
                lRelative = dlRelative.getText().toString();
                lVal = dlVal.getText().toString();
                lDOB = dlDOB.getText().toString();
                lbgrp = dlbgrp.getText().toString();
                lvclass = dlvclass.getText().toString();

                if(lName.isEmpty() )
                {
                    FancyToast.makeText(getApplicationContext(), "Please enter Driving License Holder name!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (lRelative.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the Relative Name.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (lVal.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Validity Date.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (lDOB.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the Date Of Birth Date.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (lbgrp.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the Blood Group.", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if (lvclass.isEmpty()) {
                    FancyToast.makeText(getApplicationContext(), "Please Enter the DL Vehicle Class ", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                    flag = false;
                }

                if(flag)
                {
                    //String id, String carNumber, String owner, String type, String regdate, String insurance, String pollutionstatus
                    String dlNameUpper = lName.toUpperCase();
                    boolean stat = dataBaseHelper.UpdateInT3(dlID, lno, dlNameUpper, lRelative, lregd, lVal, lDOB, lbgrp, lvclass, lAuthC, lAut);
                    if(stat)
                    {
                        FancyToast.makeText(getApplicationContext(), "Updated successful!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(action.compareTo("EDIT")==0)
                                {
                                    Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                                    i.putExtra("ID", id);
                                    startActivity(i);
                                    finish();
                                }
                                else {
                                    Intent i = new Intent(getApplicationContext(), ViewDllist.class);
                                    i.putExtra("ID", id);
                                    //i.putExtra("ACTION", "NULL");
                                    startActivity(i);
                                    finish();
                                }
                            }
                        }, 1400);
                    }
                    else
                    {
                        FancyToast.makeText(getApplicationContext(), "Update failed!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                    }
                }
            }
        });

    }
}
