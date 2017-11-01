package com.annewangari18.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anne Wangari18 on 10/17/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //All static variables
    //Database version

    public static final int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = "contactsManager";

    //Contacts Table Name
    public static final String TABLE_CONTACTS = "contacts";

    //Jobs Table Name
    public static final String TABLE_JOBS = "jobs";

    //Contacts Table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    //Jobs Table Columns
    private static final String KEY_JOB_ID = "id";
    private static final String KEY_JOB_TITLE = "job_title";
    private static final String KEY_SALARY = "salary";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating Table Contacts
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        //Creating Table Jobs
        String CREATE_JOBS_TABLE = "CREATE TABLE " + TABLE_JOBS + "(" + KEY_JOB_ID + " INTEGER PRIMARY KEY," + KEY_JOB_TITLE + " TEXT," + KEY_SALARY + " DOUBLE" + ")";
        db.execSQL(CREATE_JOBS_TABLE);
    }

    //Upgrading Tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_JOBS);
        //Create tables again
        onCreate(db);
    }
    //Adding new contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //Contact Name
        values.put(KEY_NAME, contact.get_name());
        //Contact Phone Number
        values.put(KEY_PH_NO, contact.get_phone_number());

        //Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    //Adding new job title
    public void addJobs(Jobs jobs){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues jobs_values = new ContentValues();
        //Job Title
        jobs_values.put(KEY_JOB_TITLE, jobs.getJob_title());
        //Salary for job
        jobs_values.put(KEY_SALARY, jobs.getSalary());

        //Inserting Row
        db.insert(TABLE_JOBS, null, jobs_values);
        db.close();
    }
    //Getting single contact
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[]{
                String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        //return contact
        return contact;
    }
    //Getting single contact
    public Jobs getJobs(int jobs_id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor jobs_cursor = db.query(TABLE_JOBS, new String[]{
                KEY_JOB_ID, KEY_JOB_TITLE, KEY_SALARY}, KEY_JOB_ID + "=?", new String[]{
                String.valueOf(jobs_id)}, null, null, null, null);
        if(jobs_cursor != null){
            jobs_cursor.moveToFirst();
        }

        Jobs jobs = new Jobs(Integer.parseInt(jobs_cursor.getString(0)), jobs_cursor.getString(1), jobs_cursor.getDouble(2));
        //return contact
        return jobs;
    }
    //Getting all contacts
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to the list
        if(cursor.moveToFirst()){
            do{
               Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));
                //Adding contact to list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }
    //Getting all jobs
    public List<Jobs> getAllJobs(){
        List<Jobs> jobsList = new ArrayList<Jobs>();
        //Select All Query
        String selectJobQuery = "SELECT * FROM " + TABLE_JOBS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor jobs_cursor = db.rawQuery(selectJobQuery, null);

        //looping through all rows and adding to the list
        if(jobs_cursor.moveToFirst()){
            do{
                Jobs jobs = new Jobs();
                jobs.setJob_id(Integer.parseInt(jobs_cursor.getString(0)));
                jobs.setJob_title(jobs_cursor.getString(1));
                jobs.setSalary(jobs_cursor.getDouble(2));
                //Adding contact to list
                jobsList.add(jobs);
            }while (jobs_cursor.moveToNext());
        }
        return jobsList;
    }

    //Getting contacts count
    public int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    //Getting jobs count
    public int getJobsCount(){
        String countQuery = "SELECT * FROM " + TABLE_JOBS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor jobs_cursor = db.rawQuery(countQuery, null);
        jobs_cursor.close();
        return jobs_cursor.getCount();
    }
    //Updating single contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?", new String[]{
                String.valueOf(contact.get_id())
        });
    }
    //Updating single job
    public int updateJobs(Jobs jobs){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues job_values = new ContentValues();
        job_values.put(KEY_JOB_TITLE, jobs.getJob_title());
        job_values.put(KEY_SALARY, jobs.getSalary());

        //updating row
        return db.update(TABLE_JOBS, job_values, KEY_JOB_ID + "=?", new String[]{
                String.valueOf(jobs.getJob_id())
        });
    }

    //Deleting single contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?", new String[]{
           String.valueOf(contact.get_id())
        });
        db.close();
    }
    //Deleting single job
    public void deleteJobs(Jobs jobs){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JOBS, KEY_JOB_ID + "=?", new String[]{
                String.valueOf(jobs.getJob_id())
        });
        db.close();
    }
}
