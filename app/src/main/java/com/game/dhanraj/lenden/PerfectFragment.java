package com.game.dhanraj.lenden;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cuboid.cuboidcirclebutton.CuboidButton;

import java.util.ArrayList;

import Database.DatabaseDena;
import Model.DataOfDebt;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfectFragment extends Fragment {

    private Integer IDuwanttodelete;
    private DatabaseDena db;
    private ArrayList<DataOfDebt> datalena = new ArrayList<>();
    private OwedAdapter owedAdapter;
    private ListView listView1;
    private Context context1;
    private View promptsView1;


    private com.github.clans.fab.FloatingActionButton fabb1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfect,container,false);
        listView1 = (ListView)layout.findViewById(R.id.lenewalilist);

        context1 = getActivity();



        fabb1 = (com.github.clans.fab.FloatingActionButton) layout.findViewById(R.id.fabulous2);
        fabb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddInfoOfdebt1.class);
                startActivity(i);
               // getActivity().finish();
            }
        });



        return layout;
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshData();

    }

    private void refreshData() {
        datalena.clear();
        db = new DatabaseDena(getActivity());
        ArrayList<DataOfDebt> dataofowedmoney = db.getOwedmoney();

        for(int i =0;i<dataofowedmoney.size();i++)
        {
            DataOfDebt owed = new DataOfDebt();
            owed.setUdhariTableId(dataofowedmoney.get(i).getUdhariTableId());
            owed.setName(dataofowedmoney.get(i).getName());
            owed.setEmailid(dataofowedmoney.get(i).getEmailid());
            owed.setNumber(dataofowedmoney.get(i).getNumber());

            datalena.add(owed);

        }
        db.close();


        owedAdapter = new OwedAdapter(getActivity(),R.layout.custom_row_dena,datalena);
        listView1.setAdapter(owedAdapter);
        owedAdapter.notifyDataSetChanged();
    }


    public class OwedAdapter extends ArrayAdapter<DataOfDebt> {
        Activity activity;
        int layoutResource;
        DataOfDebt debt;
        ArrayList<DataOfDebt> mdata = new ArrayList<>();


        public OwedAdapter(Activity act, int resource, ArrayList<DataOfDebt> objects) {
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
                holder.name = (TextView) row.findViewById(R.id.customrownamedena);
                holder.emailid = (TextView) row.findViewById(R.id.emailidcustomrowdena);
                holder.number = (TextView) row.findViewById(R.id.numbercustomrowdena);
                holder.update = (CuboidButton) row.findViewById(R.id.upgradebutton);
                holder.IIT = (TextView) row.findViewById(R.id.iit);

                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.owe = getItem(position);
            holder.name.setText("Name : " + holder.owe.getName());
            holder.emailid.setText("Email ID : " + holder.owe.getEmailid());
            holder.number.setText("Mobile Number : " + holder.owe.getNumber());
            holder.IIT.setText("id : " + holder.owe.getUdhariTableId());

            final ViewHolder finalHolder1 = holder;
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    IDuwanttodelete = finalHolder1.owe.getUdhariTableId();
                    prompt1();
                    return true;
                }
            });
            final ViewHolder finalHolder = holder;
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Name = finalHolder.owe.getName().toString();
                    String Emailid = finalHolder.owe.getEmailid().toString();
                    String Number = finalHolder.owe.getNumber().toString();
                    Integer iit = finalHolder.owe.getUdhariTableId();
                    Intent i = new Intent(getActivity(), InfoOfOwedMoney.class);
                    i.putExtra("owedtableid1", iit);
                    i.putExtra("name1", Name);
                    i.putExtra("emailid1", Emailid);
                    i.putExtra("number1", Number);

                    startActivity(i);
                }
            });

            holder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Updating1.class);

                    String Name = finalHolder.owe.getName().toString();
                    String Emailid = finalHolder.owe.getEmailid().toString();
                    String Number = finalHolder.owe.getNumber().toString();
                    Integer iit = finalHolder.owe.getUdhariTableId();


                    intent.putExtra("owedMONEYTableID", iit);
                    intent.putExtra("NAME1", Name);
                    intent.putExtra("EMAILID1", Emailid);
                    intent.putExtra("NUMBER1", Number);
                    startActivity(intent);

                    // refreshData();

                }
            });


            return row;
        }


        public class ViewHolder
        {
            DataOfDebt owe;
            TextView name;
            TextView IIT;
            TextView emailid;
            TextView number;
            CuboidButton update;
        }


    }


        public void prompt1()
    {
        LayoutInflater li = LayoutInflater.from(context1);
         View promptsView = li.inflate(R.layout.prompts,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context1);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);



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
        db.deleteRowofinnerlist1(IDuwanttodelete);
        db.deleteRowofouterlist1(IDuwanttodelete);
        refreshData();



    }

}
