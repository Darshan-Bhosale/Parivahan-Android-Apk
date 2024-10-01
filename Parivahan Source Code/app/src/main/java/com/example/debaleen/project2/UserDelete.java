package com.example.debaleen.project2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class UserDelete extends Activity {

    TextView uname, uemail, phone, gender,password;
    Button backButton, yesButton;
    DataBaseHelper dataBaseHelper;

    String id, Name, action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete);

        uname = findViewById(R.id.regName);
        uemail = findViewById(R.id.regEmail);
        phone = findViewById(R.id.regPhone);
        gender = findViewById(R.id.regGenderRadio);


        backButton = findViewById(R.id.backButton);

        yesButton = findViewById(R.id.yesButton);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        id = getIntent().getStringExtra("ID");
        Name = getIntent().getStringExtra("NAME");
        action = getIntent().getStringExtra("ACTION");
        if (action == null) {
            action = "NULL";
        }
        Cursor res = dataBaseHelper.displayByIdFromT1(Name);
        if (!res.moveToNext()) {
            FancyToast.makeText(getApplicationContext(), "Invalid User Name!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        } else {
            String name = res.getString(1);
            String useremail = res.getString(2);
            String userphone = res.getString(3);
            String usergender = res.getString(4);
            String upassword = res.getString(5);

            uname.setText(String.valueOf(name));
            uemail.setText(String.valueOf(useremail));
            phone.setText(String.valueOf(userphone));
            gender.setText(String.valueOf(usergender));


        }
        backButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(getApplicationContext(), ViewUserslist.class);
                i.putExtra("ID", id);
                //i.putExtra("ACTION", "NULL");
                startActivity(i);
                finish();
            }
        });
        yesButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean stat = dataBaseHelper.deleteFromT1(Name);
                if(stat)
                {
                    FancyToast.makeText(getApplicationContext(), "Deleted successfully.", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(action.compareTo("DELETE")==0) {
                                Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                                i.putExtra("ID", id);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Intent i = new Intent(getApplicationContext(), ViewUserslist.class);
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
                    FancyToast.makeText(getApplicationContext(), "Delete failed.", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }
            }
        });
    }
}
