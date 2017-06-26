package com.game.dhanraj.lenden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cuboid.cuboidcirclebutton.CuboidButton;

public class Event2Activity extends AppCompatActivity {


    private Integer UdhariTableID;
    private CuboidButton addmembers;
    private CuboidButton addexpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);

        addexpend = (CuboidButton) findViewById(R.id.addexpenditure);
        addmembers = (CuboidButton) findViewById(R.id.add_members);


        addmembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListOfMembers.class);
                i.putExtra("IdofEvent",UdhariTableID);
                startActivity(i);
            }
        });

        addexpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListOfExpend.class);
                i.putExtra("IdofEvent1",UdhariTableID);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            UdhariTableID = extras.getInt("eventid");

        }
    }
}
