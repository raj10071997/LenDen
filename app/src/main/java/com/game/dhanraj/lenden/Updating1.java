package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;

public class Updating1 extends AppCompatActivity {

    private DatabaseDena db;
    private EditText number,emailid;
    private CuboidButton updatingData;
    private String Number,Emailid;
    private Integer ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating1);

        number = (EditText)findViewById(R.id.updatingumber1);
        emailid = (EditText)findViewById(R.id.updatingemailid1);
        updatingData = (CuboidButton)findViewById(R.id.updateDatabutton1);
        Bundle extras = getIntent().getExtras();
        ID = extras.getInt("owedMONEYTableID");
        Number = extras.getString("NUMBER1");
        Emailid = extras.getString("EMAILID1");

        number.setText(Number);
        emailid.setText(Emailid);

        updatingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData1();

            }
        });
    }



    private void UpdateData1() {
        db = new DatabaseDena(getApplicationContext());
        db.UpdateEmail1(ID,emailid.getText().toString());
        db.UpdateNumber1(ID,number.getText().toString());

        finish();

    }
}
