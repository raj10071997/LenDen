package com.game.dhanraj.lenden;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import java.util.ArrayList;

import Database.DatabaseDena;
import Model.DataOfDebt;

/**
 * Created by Dhanraj on 05-01-2017.
 */
public  class MainFragment extends Fragment
{
    private Integer IDuwanttodelete;
    private  Context context;
    private DatabaseDena db;
    private ListView listView;
    private ArrayList<DataOfDebt> datadena = new ArrayList<>();
    private DebtAdapter debtAdapter;
    Button addkarnewala;
    private int IIDD;
    private View layout;
    private com.github.clans.fab.FloatingActionButton fabb;
    private View promptsView;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View layout = inflater.inflate(R.layout.main_fragment,container,false);

        listView = (ListView)layout.findViewById(R.id.denewalilist);

        context = getActivity();




        fabb = (com.github.clans.fab.FloatingActionButton) layout.findViewById(R.id.fabulous);
      fabb.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getActivity(),AddInfoOfdebt.class);
              startActivity(i);
             // getActivity().finish();
          }
      });


        refreshData();

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();



    }

    private void refreshData() {
        datadena.clear();
        db = new DatabaseDena(getActivity());
        ArrayList<DataOfDebt> dataofdebt = db.getDebt();

        for(int i =0;i<dataofdebt.size();i++)
        {
            DataOfDebt debt = new DataOfDebt();
            debt.setUdhariTableId(dataofdebt.get(i).getUdhariTableId());
            debt.setName(dataofdebt.get(i).getName());
            debt.setEmailid(dataofdebt.get(i).getEmailid());
            debt.setNumber(dataofdebt.get(i).getNumber());

            datadena.add(debt);

        }
        db.close();


        debtAdapter = new DebtAdapter(getActivity(),R.layout.custom_row_dena,datadena);
        listView.setAdapter(debtAdapter);
        debtAdapter.notifyDataSetChanged();
    }


    public class DebtAdapter extends ArrayAdapter<DataOfDebt>
    {      Activity activity;
        int layoutResource;
        DataOfDebt debt;
        ArrayList<DataOfDebt> mdata = new ArrayList<>();


        public DebtAdapter(Activity act, int resource, ArrayList<DataOfDebt> objects) {
            super(act, resource, objects);

            layoutResource=resource;
            activity = act;
            mdata=objects;
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

            if(row ==null|| row.getTag()==null)
            {
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResource,null);

                holder = new ViewHolder();
                holder.name = (TextView)row.findViewById(R.id.customrownamedena);
                holder.emailid=(TextView)row.findViewById(R.id.emailidcustomrowdena);
                holder.number=(TextView)row.findViewById(R.id.numbercustomrowdena);
                holder.update = (CuboidButton)row.findViewById(R.id.upgradebutton);
                holder.IIT = (TextView) row.findViewById(R.id.iit);

                row.setTag(holder);
            }else{
                holder = (ViewHolder)row.getTag();
            }

            holder.debt = getItem(position);
            holder.name.setText("Name : " + holder.debt.getName());
            holder.emailid.setText("Email ID : " + holder.debt.getEmailid());
            holder.number.setText("Mobile Number : " +holder.debt.getNumber());
            holder.IIT.setText("id : " + holder.debt.getUdhariTableId());

            final ViewHolder finalHolder = holder;
            final ViewHolder finalHolder1 = holder;
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    IDuwanttodelete = finalHolder1.debt.getUdhariTableId();
                    prompt();
                    return true;
                }
            });
            holder.name.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    String Name = finalHolder.debt.getName().toString();
                    String Emailid = finalHolder.debt.getEmailid().toString();
                    String Number = finalHolder.debt.getNumber().toString();
                    Integer iit = finalHolder.debt.getUdhariTableId();
                    Intent i  = new Intent(getActivity(),InfoOfDebt.class);
                    i.putExtra("Udharitableid",iit);
                    i.putExtra("Name",Name);
                    i.putExtra("Emailid",Emailid);
                    i.putExtra("Number",Number);

                    startActivity(i);
                }
            });

            holder.update.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),updating.class);

                    String Name = finalHolder.debt.getName().toString();
                    String Emailid = finalHolder.debt.getEmailid().toString();
                    String Number = finalHolder.debt.getNumber().toString();
                    Integer iit = finalHolder.debt.getUdhariTableId();

                    intent.putExtra("UdhariTableID",iit);
                    intent.putExtra("NAME",Name);
                    intent.putExtra("EMAILID",Emailid);
                    intent.putExtra("NUMBER",Number);
                    startActivity(intent);

                   // refreshData();

                }
            });




            return row;
        }


        public class ViewHolder
        {
            DataOfDebt debt;
            TextView name;
            TextView IIT;
            TextView emailid;
            TextView number;
            CuboidButton update;
        }
    }

    public void prompt()
    {
        //LayoutInflater li = LayoutInflater.from(context);
       // View promptsView = li.inflate(R.layout.prompts,null);

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


    public void delete()
    {

        db.deleteRowofinnerlist(IDuwanttodelete);
        db.deleteRowofouterlist(IDuwanttodelete);
        onResume();
        //refreshData();
    }



}

