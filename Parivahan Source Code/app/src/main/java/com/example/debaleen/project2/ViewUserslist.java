package com.example.debaleen.project2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

public class ViewUserslist extends AppCompatActivity {

    ListView listView;
    Button backButton;
    TextView textView14;
    DataBaseHelper dataBaseHelper;

    SearchView searchView;
    Toolbar toolbar;

    String id="",Name, action="";

    ArrayList<String> userDetails= new ArrayList<String>();;
    ArrayAdapter<String> adapter;

    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_users);

        listView=findViewById(R.id.ulistView);
        backButton=findViewById(R.id.backButton);
        textView14=findViewById(R.id.ulistheader);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setSubtitle("by user name");


        dataBaseHelper = new DataBaseHelper(this);

        id = getIntent().getExtras().getString("ID");
        if(id == null)
        {
            FancyToast.makeText(getApplicationContext(), "INVALID ID", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
            id = "NULL";
        }

        action = getIntent().getExtras().getString("ACTION");
        if(action == null)
        {
            action = "NULL";
        }

        Cursor res = dataBaseHelper.displayAllInT1();

        if(res!=null)
        {
            //userDetails.add("ID--User Name");
            while(res.moveToNext())
            {
                userDetails.add(res.getString(0)+"-- "+res.getString(1)+" , " +res.getString(3));
                //userName.add(res.getString(2));
            }
        }
        if(res == null)
        {
            userDetails.add("--NO RECORD FOUND!!--");
            flag = false;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userDetails);
        listView.setAdapter(adapter);

        if(listView.getCount()<1)
        {
            //FancyToast.makeText(getApplicationContext(), "No records found", FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show();
            textView14.setTextColor(getResources().getColor(R.color.expstat));
            textView14.setText("--No Records Found--");
            flag = false;
        }
        else
        {
            textView14.setTextColor(getResources().getColor(R.color.colorPrimary));
            textView14.setText(getText(R.string.ulistheader));
        }
        if(flag) {
            if(action.compareTo("DELETE")==0)
            {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                        String B = adapterView.getItemAtPosition(i).toString();
                        Name=String.valueOf(B.split("--")[0]);
                        Intent intent = new Intent(getApplicationContext(), UserDelete.class);
                        intent.putExtra("ID", id);
                        intent.putExtra("NAME", Name);
                        intent.putExtra("ACTION", action);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            else if(action.compareTo("EDIT")==0)
            {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                        String B = adapterView.getItemAtPosition(i).toString();
                        Name=String.valueOf(B.split("--")[0]);
                        Intent intent = new Intent(getApplicationContext(), UserEdit.class);
                        intent.putExtra("ID", id);
                        intent.putExtra("NAME", Name);
                        intent.putExtra("ACTION", action);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            else {
                registerForContextMenu(listView);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                        FancyToast.makeText(getApplicationContext(), "Long press to activate menu.", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                    }
                });
            }

        }


        backButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(getApplicationContext(), ProfileAdmin.class);
                i.putExtra("ID", id);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_toolbar, menu);

        final MenuItem searchItem = menu.findItem(R.id.searchBar);

        searchView = (SearchView)searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                if(!searchView.isIconified())
                {
                    searchView.setIconified(true);
                }
                searchItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        String B=userDetails.get(info.position);

        Name=String.valueOf(B.split("--")[0]);

        //Toast.makeText(this,"id="+vehicleId,Toast.LENGTH_SHORT).show();
        menu.setHeaderTitle("Make your choice");
        menu.add(0,v.getId(),0,"View");
        menu.add(0,v.getId(),0,"Delete");
        menu.add(0,v.getId(),0,"Edit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="View")
        {
            /*Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
            Intent i1=new Intent(getApplicationContext(),View1.class);
            //String v1=B.substring(0,2);
            Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
            i1.putExtra("var1",id);
            startActivity(i1);*/
            Intent i = new Intent(getApplicationContext(), ViewUser.class);
            i.putExtra("ID", id);
            i.putExtra("NAME", Name);
            //i.putExtra("ACTION", "NULL");
            startActivity(i);
            finish();
        }
        else if(item.getTitle()=="Delete")
        {
            /*Toast.makeText(this,item.getItemId(),Toast.LENGTH_SHORT).show();
            Intent i1=new Intent(getApplicationContext(),Delete1.class);
            //String v1=B.substring(0,2);
            i1.putExtra("var1",id);
            startActivity(i1);*/
            Intent i = new Intent(getApplicationContext(), UserDelete.class);
            i.putExtra("ID", id);
            i.putExtra("NAME", Name);
            //i.putExtra("ACTION", "NULL");
            startActivity(i);
            finish();
        }
        else if(item.getTitle()=="Edit")
        {
            /*Toast.makeText(this,item.getItemId(),Toast.LENGTH_SHORT).show();
            Intent i1=new Intent(getApplicationContext(),Edit1.class);
            i1.putExtra("var1",id);
            startActivity(i1);*/
            Intent i = new Intent(getApplicationContext(), UserEdit.class);
            i.putExtra("ID", id);
            i.putExtra("NAME", Name);
            //i.putExtra("ACTION", "NULL");
            startActivity(i);
            finish();
        }
        return super.onContextItemSelected(item);
    }
}
