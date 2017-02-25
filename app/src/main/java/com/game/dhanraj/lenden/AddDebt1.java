package com.game.dhanraj.lenden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class AddDebt1 extends AppCompatActivity {

    private EditText reasonofdebt;
    private int parent_Id;
    private EditText debtamount;
    private Button addkardeyr;
    private DatabaseDena dbDena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt1);


        reasonofdebt = (EditText)findViewById(R.id.editreasonofdebt1);
        debtamount = (EditText)findViewById(R.id.editaddinfodebt1);
        addkardeyr = (Button)findViewById(R.id.addkardeyr1);
        dbDena = new DatabaseDena(AddDebt1.this);


        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            parent_Id = extras.getInt("prefe");
        }
        addkardeyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDataBase1();
            }
        });

    }

    private void saveToDataBase1() {

        DataOfDebt dena = new DataOfDebt();
        dena.setReasonofdebt(reasonofdebt.getText().toString());
        dena.setDebtamount(debtamount.getText().toString());
        dena.setParentid(parent_Id);
        dbDena.addDebt1(dena);
        dbDena.close();

        debtamount.setText("");
        reasonofdebt.setText("");

        finish();
    }
}
