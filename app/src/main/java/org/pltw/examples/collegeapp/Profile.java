package org.pltw.examples.collegeapp;



import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wdumas on 12/22/2014.
 */
public class Profile implements ApplicantData {

    private String mFirstName;
    private String mLastName;
    private String mGPA;
    private String mSchoolGPA;
    private String mACT;
    private String mSchoolACT;
    private Date mDateOfBirth;
    private static final String JSON_FIRST_NAME = "firstName";
    private static final String JSON_LAST_NAME = "lastName";
    private static final String JSON_DOB = "dob";
    private static final String JSON_GPA = "gpa";

    public Profile(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);
        mGPA = json.getString(JSON_GPA);
        mDateOfBirth = new Date(json.getLong(JSON_DOB));
    }

    public String getSchoolACT() {
        return mSchoolACT;
    }

    public void setSchoolACT(String schoolACT) {
        mSchoolACT = schoolACT;
    }

    public String getACT() {
        return mACT;
    }

    public void setACT(String ACT) {
        mACT = ACT;
    }
    public String getSchoolGPA() {
        return mSchoolGPA;
    }

    public void setSchoolGPA(String schoolGPA) {
        this.mSchoolGPA = schoolGPA;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getGPA() {
        return mGPA;
    }

    public void setGPA(String mGPA) {
        this.mGPA = mGPA;
    }

    public Date getDateOfBirth() {
        return mDateOfBirth;
    }

    public String dobToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(mDateOfBirth.getTime());
    }

    public void setmDateOfBirth(Date mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public Profile() {
        mFirstName = new String("Wyatt");
        mLastName = new String("Dumas");
        mGPA = new String("3.5");
        mSchoolGPA = new String("3.0");
        mACT = new String("24");
        mSchoolACT = new String("24");
        mDateOfBirth = new Date(83, 0, 24);
    }

    public String toString() {
        return mFirstName + " " + mLastName + " " + mGPA + " " + mDateOfBirth.getTime();
    }

    public static boolean compareFloat(String input0, String input1) {
        float input0Float = Float.valueOf(input0);
        float input1Float = Float.valueOf(input1);

        if (input0Float >= input1Float) {
            return true;
        } else {
            return false;
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        json.put(JSON_GPA, mGPA);
        json.put(JSON_DOB, mDateOfBirth.getTime());
        System.out.println("Date of Birth Saved: " + mDateOfBirth);
        return json;
    }

}
