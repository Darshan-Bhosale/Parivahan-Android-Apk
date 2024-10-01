package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ViewUser extends AppCompatActivity {
    TextView uname, uemail, phone, gender,password,type;
    Button backButton;
    DataBaseHelper dataBaseHelper;

    String id, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        uname = findViewById(R.id.regName);
        uemail = findViewById(R.id.regEmail);
        phone = findViewById(R.id.regPhone);
        gender = findViewById(R.id.regGenderRadio);
        password = findViewById(R.id.password);
        type = findViewById(R.id.type);

        backButton = findViewById(R.id.backButton);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        id = getIntent().getStringExtra("ID");
        userid = getIntent().getStringExtra("USERID");

        Cursor res = dataBaseHelper.displayByIdFromT1(id);

        if (!res.moveToNext()) {
            FancyToast.makeText(getApplicationContext(), "Invalid User Name!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
        else {
            String name = res.getString(1);
            String useremail = res.getString(2);
            String userphone = res.getString(3);
            String usergender = res.getString(4);
            String userpass = res.getString(5);
            String autype = res.getString(6);
            if(autype.compareTo("Admin")==0){
                type.setTextColor(getResources().getColor(R.color.okstat));
            }else if(autype.compareTo("User")==0){
                type.setTextColor(getResources().getColor(R.color.checkstat));
            }
            uname.setText(String.valueOf(name));
            uemail.setText(String.valueOf(useremail));
            phone.setText(String.valueOf(userphone));
            gender.setText(String.valueOf(usergender));
            password.setText(String.valueOf(userpass));
            type.setText(String.valueOf(autype));
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
    }
}

