package com.annewangari18.sqlitelab;

/**
 * Created by Anne Wangari18 on 10/24/2017.
 */

public class Jobs {

    int job_id;
    String job_title;
    Double Salary;

    //Empty constructor
    public Jobs(){

    }
    //Constructor 1
    public Jobs(int job_id, String job_title, Double Salary){
        this.job_id = job_id;
        this.job_title = job_title;
        this.Salary = Salary;
    }
    //Constructor 2
    public Jobs(String job_title, Double Salary){
        this.job_title = job_title;
        this.Salary = Salary;
    }
    //Getters and Setters
    public int getJob_id() {
        return this.job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return this.job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Double getSalary() {
        return this.Salary;
    }

    public void setSalary(Double salary) {
        this.Salary = salary;
    }
}
