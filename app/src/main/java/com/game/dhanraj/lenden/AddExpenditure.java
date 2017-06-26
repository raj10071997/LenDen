package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class AddExpenditure extends AppCompatActivity {


    private EditText name,amount,reason;
    private Button btn;
    private int parent_Id;
    private DatabaseDena db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenditure);

        name=(EditText) findViewById(R.id.editaddinfolistofmember12);
        amount = (EditText) findViewById(R.id.edittextamountofexpend);
        reason  = (EditText) findViewById(R.id.editreasonexpend1);
        btn = (Button) findViewById(R.id.addkardebe11);

        Bundle extras = getIntent().getExtras();
        db = new DatabaseDena(this);

        if(extras!=null){
            parent_Id = extras.getInt("addexpenditure");
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpendofevent();
            }
        });
    }

    private void addExpendofevent() {
        DataOfDebt dena = new DataOfDebt();
        dena.setParentid(parent_Id);
        dena.setName(name.getText().toString());
        dena.setReasonofdebt(reason.getText().toString());
        dena.setDebtamount(amount.getText().toString());
        db.addexpend(dena);
        db.close();
        finish();


    }
}
