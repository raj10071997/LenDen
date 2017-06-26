package com.game.dhanraj.lenden;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class UpdateInfoOfListMembers extends AppCompatActivity {

    private EditText updatename,updateemail;
    private CuboidButton updateinfo;
    private DatabaseDena db;
    private int debtkeyID;
    private String name,emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_of_list_members);

        updateemail = (EditText)findViewById(R.id.edittextupdateemailidofmember);
        updatename=(EditText) findViewById(R.id.edittextupdatename);
        updateinfo = (CuboidButton) findViewById(R.id.saveupdatedinfoofmember);
        db = new DatabaseDena(this);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            debtkeyID = extras.getInt("idoflistmember");
            name = extras.getString("getname");
            emailid = extras.getString("getemailid");
        }

        updatename.setText(name);
        updateemail.setText(emailid);
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetheupdatedinfo();
            }
        });

    }

    private void savetheupdatedinfo() {
        DataOfDebt dena = new DataOfDebt();
        dena.setName(updatename.getText().toString());
        dena.setEmailid(updateemail.getText().toString());
        dena.setDebt_keyid(debtkeyID);
        db.updatetheinfoofmember(dena);
        db.close();
        finish();
    }
}
