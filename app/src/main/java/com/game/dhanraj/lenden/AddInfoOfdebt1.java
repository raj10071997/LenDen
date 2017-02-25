package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class AddInfoOfdebt1 extends AppCompatActivity {



    private EditText name;
    private EditText number;
    private EditText emailid;
    // private EditText debtamount;
    private CuboidButton save;
    private DatabaseDena dbDena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_ofdebt1);

        name = (EditText)findViewById(R.id.edittextnamedena1);
        number=(EditText)findViewById(R.id.edittextnumberdena1);
        emailid = (EditText)findViewById(R.id.edittextemailiddena1);
        //debtamount= (EditText)findViewById(R.id.edittextdebtdena);
        save = (CuboidButton) findViewById(R.id.savebuttonaddinfowala1);
        dbDena = new DatabaseDena(AddInfoOfdebt1.this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB1();
            }
        });
    }


    private void saveToDB1() {
        DataOfDebt dena = new DataOfDebt();
        dena.setName(name.getText().toString());
        dena.setNumber(number.getText().toString());
        dena.setEmailid(emailid.getText().toString());
        // dena.setDebtamount(debtamount.getText().toString());

        dbDena.addData1(dena);
        dbDena.close();

        name.setText("");
        number.setText("");
        emailid.setText("");
        //  debtamount.setText("");

        //Intent i = new Intent(AddInfoOfdebt1.this,MainActivity.class);

       /* i.putExtra("name",dena.getName());
        i.putExtra("number",dena.getNumber());
        i.putExtra("emailid",dena.getEmailid());
        i.putExtra("debtamount",dena.getDebtamount());*/

        //startActivity(i);
        finish();

    }
}
