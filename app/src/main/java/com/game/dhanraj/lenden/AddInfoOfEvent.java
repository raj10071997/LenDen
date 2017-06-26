package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;

/**
 * Created by Dhanraj on 14-03-2017.
 */

public class AddInfoOfEvent extends AppCompatActivity {


    private EditText nameofevent;
    private CuboidButton saveevent;
    private DatabaseDena db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);

        nameofevent = (EditText) findViewById(R.id.edittexteventname);
        saveevent = (CuboidButton)findViewById(R.id.savethenameofevent);
        db = new DatabaseDena(AddInfoOfEvent.this);

        saveevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savethename();
            }
        });


    }

    private void savethename() {
       // DataOfDebt dena = new DataOfDebt();
        db.addEvent(nameofevent.getText().toString());
        nameofevent.setText("");
        finish();
    }

}
