package com.game.dhanraj.lenden;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

import Database.DatabaseDena;
import Model.DataOfDebt;

public class ListOfExpend extends AppCompatActivity {

    private int eventid;
    private Integer IDuwanttodelete;
    private FloatingActionButton Addmembers;
    private ListView listViewofexpend;
    private DatabaseDena db;
    private Context context;
    private ListOfExpendAdapter listOfExpendAdapter;
    private ArrayList<DataOfDebt> Listofexpendkitnehai = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_expend);

        context = this;
        listViewofexpend = (ListView) findViewById(R.id.infoofexpendofanevent);
        Addmembers = (FloatingActionButton) findViewById(R.id.addexpendevent);
        Addmembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddExpenditure.class);
                i.putExtra("addexpenditure",eventid);
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
            eventid = extras.getInt("IdofEvent1");

        }
        refreshData();
    }


    private void refreshData()
    {
        Listofexpendkitnehai.clear();
        db = new DatabaseDena(ListOfExpend.this);
        ArrayList<DataOfDebt> dena = db.getlistofexpend(eventid);

        for(int i =0;i<dena.size();i++)
        {
            DataOfDebt debt = new DataOfDebt();
            debt.setName(dena.get(i).getName());
            debt.setDebtamount(dena.get(i).getDebtamount());
            debt.setDebt_keyid(dena.get(i).getDebt_keyid());
            debt.setReasonofdebt(dena.get(i).getReasonofdebt());
            debt.setTime(dena.get(i).getTime());
            debt.setParentid(dena.get(i).getParentid());

            Listofexpendkitnehai.add(debt);

        }

        db.close();


        listOfExpendAdapter = new ListOfExpendAdapter(ListOfExpend.this,R.layout.custom_list_of_expenditure,Listofexpendkitnehai);
        listViewofexpend.setAdapter(listOfExpendAdapter);
        listOfExpendAdapter.notifyDataSetChanged();
    }

    public class ListOfExpendAdapter extends ArrayAdapter<DataOfDebt> {
        Activity activity;
        int layoutResource;
        DataOfDebt debt;
        ArrayList<DataOfDebt> mdata = new ArrayList<>();


        public ListOfExpendAdapter(Activity act, int resource, ArrayList<DataOfDebt> objects) {
            super(act, resource, objects);

            layoutResource = resource;
            activity = act;
            mdata = objects;
            notifyDataSetChanged();

        }

        @Override
        public int getCount() {
            return mdata.size();
        }

        @Override
        public DataOfDebt getItem(int position) {
            return mdata.get(position);
        }

        @Override
        public int getPosition(DataOfDebt item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            if (row == null || row.getTag() == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResource, null);

                holder = new ViewHolder();
                holder.NAME = (TextView) row.findViewById(R.id.Nameofexpend);
                holder.reason = (TextView) row.findViewById(R.id.Reasonforexpend);
                holder.Time = (TextView) row.findViewById(R.id.timeofexpend);
                holder.amount = (TextView) row.findViewById(R.id.amountinevent) ;
                holder.Updatelistofexpendbtn = (CuboidButton) row.findViewById(R.id.updatelistofexpend);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }



            holder.owed = getItem(position);
            holder.NAME.setText("Name : " +holder.owed.getName());
            holder.reason.setText("Reason : " +holder.owed.getReasonofdebt());
            holder.Time.setText("Time : "+holder.owed.getTime());
            holder.amount.setText("Amount : "+holder.owed.getDebtamount());
            // holder.reason.setText("Reason : "+holder.owed.getReasonofdebt());
            IDuwanttodelete = holder.owed.getDebt_keyid();
            final ViewHolder finalHolder1 = holder;
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    IDuwanttodelete = finalHolder1.owed.getDebt_keyid();
                    prompts();
                    return true;

                }
            });


            // holder.display.setText(holder.owed.getRadiostringvalue());


            final ViewHolder finalHolder = holder;
            holder.Updatelistofexpendbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),UpdateInfoOfListExpend.class);
                    i.putExtra("idofexpend", finalHolder.owed.getDebt_keyid());
                    i.putExtra("getnameforexpend",finalHolder.owed.getName());
                    i.putExtra("getreasonforexpned",finalHolder.owed.getReasonofdebt());
                    i.putExtra("getamountofexpend",finalHolder.owed.getDebtamount());
                    startActivity(i);
                    // finish();
                }
            });


            return row;
        }

        public class ViewHolder {
            DataOfDebt owed;
            TextView NAME;
            TextView reason;
            TextView amount;
            TextView Time;
            CuboidButton Updatelistofexpendbtn;
            //TextView reason;
        }

    }

    public void prompts()
    {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView2 = li.inflate(R.layout.prompts,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView2);



        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                delete();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void delete() {
        db.deleteRowofinnerlisteventexpend(IDuwanttodelete);
        refreshData();
    }
}
