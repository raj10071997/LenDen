package com.game.dhanraj.lenden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class UpdateInfoOfListExpend extends AppCompatActivity {

    private EditText name,amount,reason;
    private CuboidButton btn;
    private DatabaseDena db;
    private int debtkeyID;
    private String name1,reasonofexpend,amountofexpend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_of_list_expend);


        name=(EditText) findViewById(R.id.edittextupdatenameexpend);
        amount = (EditText) findViewById(R.id.edittextupdateamountofexpend);
        reason  = (EditText) findViewById(R.id.edittextupdatereasonofexpend);
        btn = (CuboidButton) findViewById(R.id.saveupdatedinfoofexpend);

        db = new DatabaseDena(this);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            debtkeyID = extras.getInt("idofexpend");
            name1 = extras.getString("getnameforexpend");
            reasonofexpend = extras.getString("getreasonforexpned");
            amountofexpend = extras.getString("getamountofexpend");
        }

        name.setText(name1);
        amount.setText(amountofexpend);
        reason.setText(reasonofexpend);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetheupdatedexpend();
            }
        });



    }

    private void savetheupdatedexpend() {
        DataOfDebt dena = new DataOfDebt();
        dena.setDebtamount(amount.getText().toString());
        dena.setName(name.getText().toString());
        dena.setReasonofdebt(reason.getText().toString());
        dena.setDebt_keyid(debtkeyID);
        db.updatetheinfoofexpend(dena);
        db.close();
        finish();

    }
}
