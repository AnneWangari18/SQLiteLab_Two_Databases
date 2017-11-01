package com.annewangari18.sqlitelab;

/**
 * Created by Anne Wangari18 on 10/17/2017.
 */

public class Contact {

    //private variables
    int _id;
    String _name;
    String _phone_number;

    //Empty constructor
    public Contact(){

    }

    //constructor
    public Contact(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    //constructor
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }
    //getting and setting values

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return this._phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

}
