package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 12/10/2015.
 */
public abstract class FamilyMember implements ApplicantData,Comparable<FamilyMember> {


    protected String mFirstName;
    protected String mLastName;
    protected static final String JSON_FIRST_NAME = "firstName";
    protected static final String JSON_LAST_NAME = "lastName";

    public final static int GUARDIAN = 0;
    public final static int SIBLING = 1;
    public final static String EXTRA_RELATIONS = "org.pltw.examples.collegeapp.relation";
    public final static String EXTRA_INDEX = "org.pltw.examples.collegeapp.index";
    private int mRelation;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        return json;
    }
    public int compareTo( FamilyMember familyMember) {
        if (this.mFirstName.equals(familyMember.mFirstName)
                &&this.mLastName.equals(familyMember.mLastName)){
            return 0;
        }
        else {
            return 1;
        }
    }

    public int getmRelation() {
        return mRelation;
    }

    public void setmRelation(int relation) {
        mRelation = relation;
    }
}
