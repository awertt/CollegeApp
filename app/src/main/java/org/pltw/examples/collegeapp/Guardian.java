package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wdumas on 2/11/2015.
 */
public class Guardian extends FamilyMember implements ApplicantData,Comparable<FamilyMember> {

    protected String mOccupation;
    protected static final String JSON_OCCUPATION = "occupation";

    public Guardian(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);
        mOccupation = json.getString(JSON_OCCUPATION);
    }

    public Guardian(){
        super();
        setFirstName("Roger");
        setLastName("Dumas");
        setOccupation("Unknown");
        setmRelation(GUARDIAN);
    }

    public Guardian(String firstName, String lastName) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setOccupation("Unknown");
        setmRelation(GUARDIAN);

    }

    public Guardian(String firstName, String lastName, String occupation){
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setOccupation(occupation);
        setmRelation(GUARDIAN);
    }

    public String toString(){
        return "Guardian: " + mFirstName + " " + mLastName +
                "\nOccupation: " + mOccupation;
    }



    public String getOccupation() {
        return mOccupation;
    }

    public void setOccupation(String occupation) {
        mOccupation = occupation;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        json.put(JSON_OCCUPATION, mOccupation);
        return json;
    }


}









