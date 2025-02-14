package com.example.debaleen.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shashank.sony.fancytoastlib.FancyToast;

import id.pahlevikun.droidcrypt.DroidCrypt;

public class ProfileAdmin extends AppCompatActivity {


    Toolbar toolbar;
    DroidCrypt droidCrypt;
    Button newCarButton, editCarButton, deleteCarButton, carGroupButton;
    Button newUserButton, editUserButton, deleteUserButton, userGroupButton;
    Button newDlButton, editDlButton, dlGroupButton;
    String id;

    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);
        dataBaseHelper = new DataBaseHelper(this);

        newCarButton = (Button)findViewById(R.id.addNewCarButton);
        editCarButton = (Button)findViewById(R.id.editCarButton);
        deleteCarButton = (Button)findViewById(R.id.deleteCarButton);
        carGroupButton = (Button)findViewById(R.id.carGroupButton);

        newUserButton = (Button)findViewById(R.id.addNewUserButton);
        editUserButton = (Button)findViewById(R.id.editUserButton);
        deleteUserButton = (Button)findViewById(R.id.deleteUserButton);
        userGroupButton = (Button)findViewById(R.id.userGroupButton);

        newDlButton = findViewById(R.id.addDl);
        editDlButton =findViewById(R.id.editDl);
        dlGroupButton =findViewById(R.id.viewDl);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getExtras().getString("ID");

        Cursor res = dataBaseHelper.displayByIdFromT1(id);
        if(res.moveToNext())
        {
            getSupportActionBar().setSubtitle(res.getString(1));
        }

        newCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), InsertNewCar.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });

        editCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewVehicleslist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION", "EDIT");
                startActivity(i);
                finish();
            }
        });

        deleteCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewVehicleslist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION", "DELETE");
                startActivity(i);
                finish();
            }
        });

        carGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewVehicleslist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION","VIEW");
                startActivity(i);
                finish();
            }
        });


        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                droidCrypt = new DroidCrypt(getApplicationContext());
                droidCrypt.deleteEncryptedFromPreferences();
                Intent i = new Intent(getApplicationContext(), UserRegistration.class);
                startActivity(i);
                finish();
            }
        });

        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewUserslist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION", "EDIT");
                startActivity(i);
                finish();
            }
        });


        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewUserslist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION", "DELETE");
                startActivity(i);
                finish();
            }
        });

        userGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewUserslist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION","VIEW");
                startActivity(i);
                finish();
            }
        });

        newDlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DlAdd.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });

        editDlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewDllist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION", "EDIT");
                startActivity(i);
                finish();
            }
        });

        dlGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewDllist.class);
                i.putExtra("ID", id);
                i.putExtra("ACTION","VIEW");
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_toolbar, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
            {
                droidCrypt = new DroidCrypt(getApplicationContext());
                droidCrypt.deleteEncryptedFromPreferences();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }
}
