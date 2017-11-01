package com.annewangari18.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         */

        //Inserting Contacts
        Log.d("Insert Contacts: ", "Inserting Contacts...");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        //Reading all contacts
        Log.d("Reading Contacts: ", "Reading all contacts...");
        List<Contact> contacts = db.getAllContacts();

        for(Contact cn: contacts){
            String log = "Id: " + cn.get_id() + ", Name: " + cn.get_name() + " , Phone Number: " + cn.get_phone_number();

            //Writing Contacts to log
            Log.d("Name: ", log);
        }

        Log.d("\nInsert Jobs: ", "Inserting Jobs...");
        db.addJobs(new Jobs("Senior Manager", 150000.00));
        db.addJobs(new Jobs("General Manager", 120000.00));
        db.addJobs(new Jobs("CEO", 100000.00));
        db.addJobs(new Jobs("Secretary", 91000.00));

        //Reading all jobs
        Log.d("Reading: ", "Reading all jobs...");
        List<Jobs> jobs = db.getAllJobs();

        for(Jobs jb: jobs){
            String log = "Id: " + jb.getJob_id() + ", Title: " + jb.getJob_title() + " , Salary: " + jb.getSalary();

            //Writing jobs to log
            Log.d("Title: ", log);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
