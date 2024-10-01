package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ChallanEdit extends AppCompatActivity {
    TextView policeName,carno,ownerName,lnumber,date;
    Spinner ruleSpinner, amountSpinner;
    Button updatebtn, backbtn;
    EditText chano;
    String id="", ecfno="",pname="", ecno="", carnumber="", ownname="", lno="", Date="", ruleS="", amountS="", action="";

    DataBaseHelper dataBaseHelper;

    int ruleposition, amountStatus;

    String[] ruleSpinnerChooser ={"Select Rule Violated","Riding or driving Without License= ₹2000","Riding or driving an unregistered vehicle= ₹3000","Violating speed regulation= ₹2000","Driving an uninsured vehicle= ₹1000","Not wearing seatbelt while driving= ₹500","Blocking the way of emergency vehicles= ₹1000","Driving rashly or dangerously= ₹5000"};
    String[] amountChooser={"Amount Status","Paid","Pending"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_challan);

        policeName = findViewById(R.id.pname);
        carno = findViewById(R.id.vno);
        ownerName = findViewById(R.id.ownername);
        lnumber = findViewById(R.id.lno);
        date = findViewById(R.id.date);
        chano = findViewById(R.id.challan_no);
        ruleSpinner = findViewById(R.id.violate_rule);
        amountSpinner = findViewById(R.id.paid_pending);
        backbtn = findViewById(R.id.btnback);
        updatebtn = findViewById(R.id.btnUpdate);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        id = getIntent().getStringExtra("ID");
        ecfno = getIntent().getStringExtra("CHALLAN NUMBER");
        action = getIntent().getStringExtra("ACTION");
        if(action == null)
        {
            action = "NULL";
        }

        Cursor res = dataBaseHelper.displayByIdFormT4(ecfno);
        if(!res.moveToNext())
        {
            FancyToast.makeText(getApplicationContext(), "Invalid Vehicle ID!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
        else
        {
            pname = res.getString(1);
            ecno = res.getString(2);
            carnumber = res.getString(3);
            ownname = res.getString(4);
            lno = res.getString(5);
            Date = res.getString(6);
            ruleS = res.getString(7);
            amountS = res.getString(8);

            if (ruleS.compareTo("Riding or driving Without License= ₹2000")==0)
            {
                ruleposition = 1;
            }
            else if (ruleS.compareTo("Riding or driving an unregistered vehicle= ₹3000")==0)
            {
                ruleposition = 2;
            }
            else if (ruleS.compareTo("Violating speed regulation= ₹2000")==0)
            {
                ruleposition = 3;
            }
            else if (ruleS.compareTo("Driving an uninsured vehicle= ₹1000")==0)
            {
                ruleposition = 4;
            }
            else if (ruleS.compareTo("Not wearing seatbelt while driving= ₹500")==0)
            {
                ruleposition = 5;
            }
            else if (ruleS.compareTo("Blocking the way of emergency vehicles= ₹1000")==0)
            {
                ruleposition = 6;
            }
            else if (ruleS.compareTo("Driving rashly or dangerously= ₹5000")==0)
            {
                ruleposition = 7;
            }
            else
            {
                ruleposition = 0;
            }

            if (amountS.compareTo("Paid")==0)
            {
                amountStatus = 1;
            }
            else if (amountS.compareTo("Pending")==0)
            {
                amountStatus = 2;
            }
            else
            {
                amountStatus = 0;
            }

            policeName.setText(String.valueOf(pname));
            chano.setText(String.valueOf(ecno));
            carno.setText(String.valueOf(carnumber));
            ownerName.setText(String.valueOf(ownname));
            lnumber.setText(String.valueOf(lno));
            date.setText(String.valueOf(Date));

            ArrayAdapter<String> ruleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ruleSpinnerChooser);
            ruleSpinner.setAdapter(ruleAdapter);

            ruleSpinner.setSelection(ruleposition);

            ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                    ruleposition = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<String> insuranceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, amountChooser);
            amountSpinner.setAdapter(insuranceAdapter);

            amountSpinner.setSelection(amountStatus);

            amountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                    amountStatus = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    backbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(action.compareTo("Edit")==0)
            {
                Intent i = new Intent(getApplicationContext(), EChallan.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
            else{
                Intent i = new Intent(getApplicationContext(), ChallanViewList.class);
                i.putExtra("ID", id);
                //i.putExtra("ACTION","NUll");
                startActivity(i);
                finish();
            }
        }
    });
    updatebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           boolean flag =true;
           ecno = chano.getText().toString();

           if (ecno.isEmpty())
           {
               FancyToast.makeText(getApplicationContext(), "Please enter Challan Number!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
               flag = false;
           }
           if (ruleposition == 0)
           {
               FancyToast.makeText(getApplicationContext(), "Please Select Rule Violence!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
               flag = false;
           }
           if (amountStatus == 0)
           {
               FancyToast.makeText(getApplicationContext(), "Please Select the Amount Status!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
               flag = false;
           }

           if (flag)
           {
               String ecnoUpper = ecno.toUpperCase();
               boolean stat = dataBaseHelper.UpdateInT4(ecfno, pname, ecnoUpper, carnumber, ownname, lno, Date, ruleSpinnerChooser[ruleposition], amountChooser[amountStatus]);
               if (stat)
               {
                   FancyToast.makeText(getApplicationContext(), "Updated successful!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                   Handler mHandler = new Handler();
                   mHandler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           if(action.compareTo("EDIT")==0)
                           {
                               Intent i = new Intent(getApplicationContext(), EChallan.class);
                               i.putExtra("ID", id);
                               startActivity(i);
                               finish();
                           }
                           else {
                               Intent i = new Intent(getApplicationContext(), ChallanViewList.class);
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