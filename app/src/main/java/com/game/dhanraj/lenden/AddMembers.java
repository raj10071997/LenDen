package com.game.dhanraj.lenden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class AddMembers extends AppCompatActivity {


    private EditText nameofthemeber;
    private EditText Emailidofthemember;
    private int parent_Id;
    private DatabaseDena dbDena;
    private Button savetheinfoofthemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        nameofthemeber = (EditText) findViewById(R.id.editaddinfoname1) ;
        Emailidofthemember = (EditText) findViewById(R.id.editemail1);
        savetheinfoofthemember = (Button) findViewById(R.id.addkardebe1);

        dbDena = new DatabaseDena(AddMembers.this);

        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            parent_Id = extras.getInt("addmembers");
        }

        savetheinfoofthemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveinfoofmember();
            }
        });
    }

    private void saveinfoofmember() {

        DataOfDebt dena = new DataOfDebt();
        dena.setName(nameofthemeber.getText().toString());
        dena.setEmailid(Emailidofthemember.getText().toString());
        dena.setParentid(parent_Id);
        dbDena.addMember1(dena);
        dbDena.close();

        nameofthemeber.setText("");
        Emailidofthemember.setText("");

        finish();
    }


}
