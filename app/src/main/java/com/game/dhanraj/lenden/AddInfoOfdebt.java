package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;
import Model.DataOfDebt;

/**
 * Created by Dhanraj on 04-01-2017.
 */
public class AddInfoOfdebt extends AppCompatActivity {


    private EditText name;
    private EditText number;
    private EditText emailid;
    private CuboidButton save;
    private DatabaseDena dbDena;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_of_debt);


        name = (EditText)findViewById(R.id.edittextnamedena);
        number=(EditText)findViewById(R.id.edittextnumberdena);
        emailid = (EditText)findViewById(R.id.edittextemailiddena);
        save = (CuboidButton) findViewById(R.id.savebuttonaddinfowala);
        dbDena = new DatabaseDena(AddInfoOfdebt.this);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveToDB();
            }
        });

    }




    public void saveToDB()
    {
        DataOfDebt dena = new DataOfDebt();
        dena.setName(name.getText().toString());
        dena.setNumber(number.getText().toString());
        dena.setEmailid(emailid.getText().toString());

        dbDena.addData(dena);
        dbDena.close();

        name.setText("");
        number.setText("");
        emailid.setText("");

        finish();

    }

}

