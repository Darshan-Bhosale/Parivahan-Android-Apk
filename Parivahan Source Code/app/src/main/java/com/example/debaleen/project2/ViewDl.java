package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ViewDl extends AppCompatActivity {
    TextView dlno,dlName,dlRelative,dlregd,dlVal,dlDOB,dlbgrp,dlvclass,dlAuthC,dlAut;
    Button backButton;
    DataBaseHelper dataBaseHelper;
    String id, dlID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_view);

        dlno = findViewById(R.id.dlno);
        dlName = findViewById(R.id.dlname);
        dlRelative = findViewById(R.id.relative);
        dlregd = findViewById(R.id.regd);
        dlVal = findViewById(R.id.dlval);
        dlDOB = findViewById(R.id.dldob);
        dlbgrp = findViewById(R.id.bgrp);
        dlvclass = findViewById(R.id.dlvtype);
        dlAuthC = findViewById(R.id.dlautc);
        dlAut = findViewById(R.id.dlaut);

        backButton = findViewById(R.id.btnback);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        id = getIntent().getStringExtra("ID");
        dlID = getIntent().getStringExtra("DLID");

        Cursor res = dataBaseHelper.displayByIdFormT3(dlID);

        if(!res.moveToNext())
        {
            FancyToast.makeText(getApplicationContext(), "Invalid DL ID!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
        else
        {
            String lno = res.getString(1);
            String lName = res.getString(2);
            String lRelative = res.getString(3);
            String lregd = res.getString(4);
            String lVal = res.getString(5);
            String lDOB = res.getString(6);
            String lbgrp = res.getString(7);
            String lvclass = res.getString(8);
            String lAuthC = res.getString(9);
            String lAut = res.getString(10);

            dlno.setText(String.valueOf(lno));
            dlName.setText(String.valueOf(lName));
            dlRelative.setText(String.valueOf(lRelative));
            dlregd.setText(String.valueOf(lregd));
            dlVal.setText(String.valueOf(lVal));
            dlDOB.setText(String.valueOf(lDOB));
            dlbgrp.setText(String.valueOf(lbgrp));
            dlvclass.setText(String.valueOf(lvclass));
            dlAuthC.setText(String.valueOf(lAuthC));
            dlAut.setText(String.valueOf(lAut));
        }
        backButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(getApplicationContext(), ViewDllist.class);
                i.putExtra("ID", id);
                //i.putExtra("ACTION", "NULL");
                startActivity(i);
                finish();
            }
        });
    }
}
