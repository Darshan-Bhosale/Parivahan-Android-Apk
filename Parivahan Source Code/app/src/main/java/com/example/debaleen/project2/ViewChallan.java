package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ViewChallan extends AppCompatActivity {
    TextView pname,ecno, vno, oname, lno, date, selrule, amount;
    Button backButton;

    DataBaseHelper dataBaseHelper;

    String id, vehId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_challan);
        pname = findViewById(R.id.pname);
        ecno = findViewById(R.id.challan_no);
        vno = findViewById(R.id.vno);
        oname = findViewById(R.id.ownername);
        lno = findViewById(R.id.lno);
        date = findViewById(R.id.date);
        selrule = findViewById(R.id.violate_rule);
        amount = findViewById(R.id.paid_pending);

        backButton = findViewById(R.id.btnback);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        id = getIntent().getStringExtra("ID");
        vehId = getIntent().getStringExtra("VEHID");

        Cursor res = dataBaseHelper.displayByIdFormT4(vehId);
        if(!res.moveToNext())
        {
            FancyToast.makeText(getApplicationContext(), "Invalid Vehicle ID!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
        else
        {
            String policeName = res.getString(1);
            String challanNo = res.getString(2);
            String vehicleNo = res.getString(3);
            String ownerName = res.getString(4);
            String licenseNo = res.getString(5);
            String Date = res.getString(6);
            String ruleSt = res.getString(7);
            String amountSt = res.getString(8);

            if (amountSt.compareTo("Paid")==0)
            {
                amount.setTextColor(getResources().getColor(R.color.okstat));
            }
            else
            {
                amount.setTextColor(getResources().getColor(R.color.expstat));
            }

            pname.setText(String.valueOf(policeName));
            ecno.setText(String.valueOf(challanNo));
            vno.setText(String.valueOf(vehicleNo));
            oname.setText(String.valueOf(ownerName));
            lno.setText(String.valueOf(licenseNo));
            date.setText(String.valueOf(Date));
            selrule.setText(String.valueOf(ruleSt));
            amount.setText(String.valueOf(amountSt));
        }

        backButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(getApplicationContext(), ChallanViewList.class);
                i.putExtra("ID", id);
                //i.putExtra("ACTION", "NULL");
                startActivity(i);
                finish();
            }
        });
    }
}
