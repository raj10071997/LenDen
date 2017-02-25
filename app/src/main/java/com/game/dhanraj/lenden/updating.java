package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;

public class updating extends AppCompatActivity {

    private DatabaseDena db;
    private EditText number,emailid;
    private CuboidButton updatingData;
    private String Number,Emailid;
    private Integer ID;
    private int main=0,perfect=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updating);


        number = (EditText)findViewById(R.id.updatingumber);
        emailid = (EditText)findViewById(R.id.updatingemailid);
        updatingData = (CuboidButton)findViewById(R.id.updateDatabutton);
        Bundle extras = getIntent().getExtras();
        ID = extras.getInt("UdhariTableID");
        Number = extras.getString("NUMBER");
        Emailid = extras.getString("EMAILID");

        number.setText(Number);
        emailid.setText(Emailid);

        updatingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    UpdateData();

            }
        });


    }



    public void UpdateData()
    {
        db = new DatabaseDena(getApplicationContext());
        db.UpdateEmail(ID,emailid.getText().toString());
        db.UpdateNumber(ID,number.getText().toString());


        finish();

    }


}
