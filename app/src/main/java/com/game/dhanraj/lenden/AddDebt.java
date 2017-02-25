package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class AddDebt extends AppCompatActivity {


    private EditText reasonofdebt;
    private int parent_Id;
    private EditText debtamount;
    private Button addkardeyr;
    private DatabaseDena dbDena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);

        reasonofdebt = (EditText)findViewById(R.id.editreasonofdebt);
        debtamount = (EditText)findViewById(R.id.editaddinfodebt);
        addkardeyr = (Button)findViewById(R.id.addkardeyr);
        dbDena = new DatabaseDena(AddDebt.this);


        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            parent_Id = extras.getInt("prent");
        }
        addkardeyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDataBase();
            }
        });

    }

    public void saveToDataBase(){




        DataOfDebt dena = new DataOfDebt();
        dena.setReasonofdebt(reasonofdebt.getText().toString());
        dena.setDebtamount(debtamount.getText().toString());
        dena.setParentid(parent_Id);
        dbDena.addDebt(dena);
        dbDena.close();

        debtamount.setText("");
        reasonofdebt.setText("");

        finish();
    }
}
