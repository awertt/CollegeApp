package org.pltw.examples.collegeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by wdumas on 12/23/2014.
 */
public class ProfileFragment extends Fragment{

    private static final String TAG = "ProfileFragment";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DOB = 0;
    private static final String KEY_FIRST_NAME = "firstname";
    private static final String FILENAME = "profile.json";

    private boolean accepted1 = false;
    private boolean accepted = false;
    private Profile mProfile;
    private TextView mFirstName;
    private EditText mEnterFirstName;
    private TextView mLastName;
    private EditText mEnterLastName;
    private TextView mGPA;
    private EditText mEnterGPA;
    private TextView mSchoolGPA;
    private EditText mEnterSchoolGPA;
    private TextView mACT;
    private EditText mEnterACT;
    private TextView mSchoolACT;
    private EditText mEnterSchoolACT;
    private TextView mAccepted;
    private Button mDoBButton;
    private Context mAppContext;
    ProfileJSONStorer mStorer;

    public ProfileFragment(){
        try {
            mProfile = mStorer.load();//loads the data
        } catch (Exception e) {//
            mProfile = new Profile();//creates a new profile with the original names
            Log.e(TAG, "Error loading profile: " + FILENAME, e);
        }
    }
    

    private void updateDoB() {
        mDoBButton.setText(mProfile.dobToString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DOB) {
            Date dob = (Date)data
                    .getSerializableExtra(DoBPickerFragment.EXTRA_DOB);
            mProfile.setmDateOfBirth(dob);
            updateDoB();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProfile = new Profile();

        if (savedInstanceState != null) {
            mProfile.setFirstName(savedInstanceState.getString(KEY_FIRST_NAME));
            Log.i(TAG, "The name is " + mProfile.getFirstName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        getActivity().setTitle(R.string.profile_title);



        mFirstName = (TextView)rootView.findViewById(R.id.first_name);
        mEnterFirstName = (EditText)rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView)rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText)rootView.findViewById(R.id.enter_last_name);
        mDoBButton = (Button)rootView.findViewById(R.id.dob_button);
        mGPA = (TextView)rootView.findViewById(R.id.gpa);
        mEnterGPA = (EditText)rootView.findViewById(R.id.enter_gpa);
        mSchoolGPA = (TextView)rootView.findViewById(R.id.school_gpa);
        mEnterSchoolGPA = (EditText)rootView.findViewById(R.id.enter_school_gpa);
        mACT = (TextView)rootView.findViewById(R.id.act);
        mEnterACT = (EditText)rootView.findViewById(R.id.enter_act);
        mSchoolACT = (TextView)rootView.findViewById(R.id.school_act);
        mEnterSchoolACT = (EditText)rootView.findViewById(R.id.enter_school_act);

        mAccepted = (TextView)rootView.findViewById(R.id.accepted);

        mFirstName.setText(mProfile.getFirstName());
        mLastName.setText(mProfile.getLastName());
        mGPA.setText(mProfile.getGPA());
        mSchoolGPA.setText("School's required GPA:");
        mACT.setText(mProfile.getACT());
        mSchoolACT.setText("School's required ACT score:");

        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();

        GPATextChanger gpaTextChanger = new GPATextChanger();
        SchoolGPATextChanger schoolGPATextChanger = new SchoolGPATextChanger();

        ACTTextChanger actTextChanger = new ACTTextChanger();
        SchoolACTTextChanger schoolACTTextChanger = new SchoolACTTextChanger();

        DoBButtonOnClickListener doBButtonOnClickListener = new DoBButtonOnClickListener();

        updateDoB();
        mDoBButton.setOnClickListener(doBButtonOnClickListener);

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);

        mEnterLastName.addTextChangedListener(lastNameTextChanger);

        mEnterGPA.addTextChangedListener(gpaTextChanger);

        mEnterSchoolGPA.addTextChangedListener(schoolGPATextChanger);

        mEnterACT.addTextChangedListener(actTextChanger);

        mEnterSchoolACT.addTextChangedListener(schoolACTTextChanger);

        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);

        mStorer = new ProfileJSONStorer(mAppContext, FILENAME);
        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState got called and it was a BLAST!!!1: " + mProfile.getFirstName());
        savedInstanceState.putString(KEY_FIRST_NAME, mProfile.getFirstName());
    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setFirstName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mProfile.getFirstName());
        }
    }

    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setLastName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mProfile.getLastName());
        }
    }

    private class GPATextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setGPA(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mGPA.setText(mProfile.getGPA());
        }
    }

    private class SchoolGPATextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setSchoolGPA(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            //mSchoolGPA.setText(mProfile.getSchoolGPA());
            try {
                String gpa = mProfile.getGPA();
                String schoolGPA = mProfile.getSchoolGPA();
                if (Profile.compareFloat(gpa,schoolGPA)) {
                    mSchoolGPA.setText("School's required GPA:");
                    //mAccepted.setText("Student meets requirements");
                    //mAccepted.setTextColor(Color.GREEN);
                    accepted = true;
                } else {
                    //mAccepted.setText("Student does not meet requirements");
                    //mAccepted.setTextColor(Color.RED);
                    accepted = false;
                }
            } catch (IllegalArgumentException i) {
                Log.e(TAG, i.getMessage());
            }
            if (accepted && accepted1) {
                mAccepted.setText("Meets school requirements");
                mAccepted.setTextColor(Color.GREEN);
            } else {
                mAccepted.setText("Does not meet school requirements");
                mAccepted.setTextColor(Color.RED);
            }
        }
    }

    private class ACTTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setACT(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mACT.setText(mProfile.getACT());
        }
    }

    private class SchoolACTTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mProfile.setSchoolACT(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            //mSchoolGPA.setText(mProfile.getSchoolGPA());
            try {
                String act = mProfile.getACT();
                String schoolACT = mProfile.getSchoolACT();

                if (Profile.compareFloat(act,schoolACT)) {
                    mSchoolACT.setText("School's required ACT:");
                    //mAccepted.setText("Student meets requirements");
                    //mAccepted.setTextColor(Color.GREEN);
                    accepted1 = true;
                } else {
                    //mAccepted.setText("Student does not meet requirements");
                    //mAccepted.setTextColor(Color.RED);
                    accepted1 = false;
                }
            } catch (IllegalArgumentException i) {
                Log.e(TAG, i.getMessage());
            }
            if (accepted && accepted1) {
                mAccepted.setText("Meets school requirements");
                mAccepted.setTextColor(Color.GREEN);
            } else {
                mAccepted.setText("Does not meet school requirements");
                mAccepted.setTextColor(Color.RED);
            }
        }
    }

    private class DoBButtonOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            FragmentManager fm = getActivity()
                    .getSupportFragmentManager();
            DoBPickerFragment dialog = DoBPickerFragment
                    .newInstance(mProfile.getDateOfBirth());
            dialog.setTargetFragment(ProfileFragment.this, REQUEST_DOB);
            dialog.show(fm, DIALOG_DATE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Fragment started.");
    }

    public boolean saveProfile() {
        try {
            mStorer.save(mProfile);
            Log.d(TAG, "profile saved to file.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving profile: ", e);
            return false;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        saveProfile();
        Log.d(TAG, "Fragment paused.");
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mProfile = mStorer.load();
            Log.d(TAG, "Loaded " + mProfile.getFirstName());
            mFirstName.setText(mProfile.getFirstName());
            mLastName.setText(mProfile.getLastName());
            mGPA.setText(mProfile.getGPA());
            mACT.setText(mProfile.getACT());

            updateDoB();
        } catch (Exception e) {
            mProfile = new Profile();
            Log.e(TAG, "Error loading profile from: " + FILENAME, e);
        }
        Log.d(TAG, "Fragment resumed.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment stopped.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment destroyed.");
    }


}

