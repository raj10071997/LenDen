package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import Database.DatabaseDena;

public class updateRadioButton1 extends AppCompatActivity {

    private EditText reasonedittext;
    private EditText debtedittext;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private Button savebtn;
    DatabaseDena db;
    private Integer debtkeyID;
    private String reason,debtamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_radio_button1);


        reasonedittext = (EditText)findViewById(R.id.reasonedittext1);
        debtedittext = (EditText)findViewById(R.id.debtamountedittext1);
        radioGroup = (RadioGroup)findViewById(R.id.RadioGrp1);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            debtkeyID = extras.getInt("Debtkeyid");
            reason = extras.getString("reason");
            debtamount = extras.getString("debt");
        }
        reasonedittext.setText(reason);
        debtedittext.setText(debtamount);


    }

    public void savethevalueoftheradiobutton1(View view) {

        db = new DatabaseDena(getApplicationContext());

        int selectedoptn = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedoptn);
        String saveradiostring = radioButton.getText().toString();
        String updatedReason = reasonedittext.getText().toString();
        String updateddebt = debtedittext.getText().toString();
        db.addradio1(saveradiostring,debtkeyID,updatedReason,updateddebt);
        finish();

    }
}
