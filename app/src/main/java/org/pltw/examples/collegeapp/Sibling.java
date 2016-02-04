package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 1/7/2016.
 */
public class Sibling extends FamilyMember implements ApplicantData,Comparable<FamilyMember>{

    public Sibling(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);
    }
    public Sibling(){
        setFirstName("Jimmy");
        setLastName("Mackelmore");
        setmRelation(SIBLING);
    }
    public Sibling(String firstName, String lastName){
        setFirstName(firstName);
        setLastName(lastName);
        setmRelation(SIBLING);
    }
    public String toString(){
        return "Sibling: " + mFirstName + " " + mLastName;
    }



}