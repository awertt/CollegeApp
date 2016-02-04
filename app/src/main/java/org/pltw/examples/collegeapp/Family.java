package org.pltw.examples.collegeapp;

import java.util.ArrayList;

/**
 * Created by PLTW on 12/10/2015.
 */
public class Family {

    private final static String TAG = "Family";
    private static Family sFamily;

    ArrayList<FamilyMember> mFamily;

    private Family(){
        mFamily = new ArrayList<FamilyMember>();
        mFamily.add(new Guardian());
        mFamily.add(new Guardian("Jason", "Dumas"));
        mFamily.add(new Sibling("Jimmy", "Mackelmore"));
    }
    public static Family get() {
        if( sFamily == null ) {
            sFamily = new Family();
        }
        return sFamily;
    }

    public ArrayList<FamilyMember> getFamily() {
        return mFamily;
    }

    public void setmFamily(ArrayList<FamilyMember> mFamily) {
        this.mFamily = mFamily;
    }

    public void addFamilyMember(FamilyMember familyMember){
        if (familyMember instanceof Guardian){
            mFamily.add(new Guardian());
        }
        else if (familyMember instanceof Sibling){
            mFamily.add(new Sibling());
    }
    }

    public void deleteFamilyMember(FamilyMember familyMember){
        if (familyMember instanceof Guardian){
            mFamily.remove(new Guardian());
        }
        else if (familyMember instanceof Sibling){
            mFamily.remove(new Sibling());
        }
    }

}
