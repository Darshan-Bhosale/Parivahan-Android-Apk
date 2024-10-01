package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;


public class UserEdit extends AppCompatActivity {
    EditText nameField, emailField, phoneField, passField;
    TextView typeField;
    RadioGroup genderField;
    RadioButton radiobtn;
    Button editButton, backButton;
    String id = "",userid="", name = "", email = "", phone = "", password = "", gender = "", type = "User", action = "";

    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameField = (EditText) findViewById(R.id.regName);
        emailField = (EditText) findViewById(R.id.regEmail);
        phoneField = (EditText) findViewById(R.id.regPhone);
        passField = findViewById(R.id.regPassword);
        typeField = findViewById(R.id.type);
        genderField = findViewById(R.id.regGenderRadio);

        editButton = (Button) findViewById(R.id.updateButton);
        backButton = (Button) findViewById(R.id.backButton);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        id = getIntent().getStringExtra("ID");
        userid = getIntent().getStringExtra("USERID");
        action = getIntent().getStringExtra("ACTION");
        if (action == null) {
            action = "NULL";
        }
        Cursor res = dataBaseHelper.displayByIdFromT1(id);

        if(!res.moveToNext())
        {
            FancyToast.makeText(getApplicationContext(), "Invalid User Id!!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }else
        {
            name = res.getString(1);
            email = res.getString(2);
            phone = res.getString(3);
            gender= res.getString(4);
            password = res.getString(5);
            type = res.getString(6);

            nameField.setText(String.valueOf(name));
            emailField.setText(String.valueOf(email));
            phoneField.setText(String.valueOf(phone));
            passField.setText(String.valueOf(password));
            genderField.setSelected(Boolean.parseBoolean(String.valueOf(gender)));
            typeField.setText(String.valueOf(type));

            genderField.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radiobtn = findViewById(radioId);
                    FancyToast.makeText(getApplicationContext(),"Gender Selected: " +radiobtn.getText(),FancyToast.LENGTH_SHORT).show();
                }
            });
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
                        Intent i = new Intent(getApplicationContext(), ViewUser.class);
                        i.putExtra("ID", id);
                        //i.putExtra("ACTION", "NULL");
                        startActivity(i);
                        finish();
                    }
                }
            });

            editButton.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    boolean flag = true;
                    name = nameField.getText().toString();
                    email = emailField.getText().toString();
                    phone = phoneField.getText().toString();
                    password = passField.getText().toString();
                    gender = genderField.toString();
                    type = typeField.getText().toString();

                    if(name.isEmpty()){
                        FancyToast.makeText(getApplicationContext(), "Please enter name!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                        flag = false;
                    }
                    if(email.isEmpty()){
                        FancyToast.makeText(getApplicationContext(), "Please enter email!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                        flag = false;
                    }
                    if(phone.isEmpty()){
                        FancyToast.makeText(getApplicationContext(), "Please enter phone no!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                        flag = false;
                    }
                    if(password.isEmpty()){
                        FancyToast.makeText(getApplicationContext(), "Please enter the password!", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                        flag = false;
                    }
                    if(type.isEmpty()){
                        FancyToast.makeText(getApplicationContext(), "Please enter the type", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                        flag = false;
                    }
                    if(flag)
                    {
                        boolean stat = dataBaseHelper.updateInT1(id,name, email, phone, gender, password, type);
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
                            FancyToast.makeText(getApplicationContext(), "Update failed!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                }
            });
        }
    }
}
