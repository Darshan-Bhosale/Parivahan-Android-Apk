package com.example.debaleen.project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class EChallan extends Activity {

    Button newchallan, editchallan, allchallan;
    Button backbutton;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.echallan);

        newchallan = findViewById(R.id.add_challan);
        editchallan = findViewById(R.id.edit_challan);
        allchallan = findViewById(R.id.view_challan);
        backbutton = findViewById(R.id.backButton);

        newchallan.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewChallan.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });
        editchallan.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChallanEdit.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION", "EDIT");
                startActivity(i);
                finish();
            }
        });
        /*allchallan.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChallanViewList.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION","VIEW");
                startActivity(i);
                finish();
            }
        });*/
        backbutton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProfileUser.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });

    }
}