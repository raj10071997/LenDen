package com.game.dhanraj.lenden;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
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
 * Created by Dhanraj on 14-03-2017.
 */

public class Events extends Fragment {

    private Integer IDuwanttodelete;
    private Context context1;
    private DatabaseDena db;
    private ListView listView;
    private ArrayList<DataOfDebt> dataevents = new ArrayList<>();
    private EventAdapter eventAdapter;
    Button addkarnewala;
    private int IIDD;
    private View layout;
    private com.github.clans.fab.FloatingActionButton fabb;
    private View promptsView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View layout = inflater.inflate(R.layout.events,container,false);

        listView = (ListView)layout.findViewById(R.id.eventwalilist);

        context1 = getActivity();




        fabb = (com.github.clans.fab.FloatingActionButton) layout.findViewById(R.id.addeventfloat);
        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddInfoOfEvent.class);
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
        dataevents.clear();
        db = new DatabaseDena(getActivity());
        ArrayList<DataOfDebt> dataofowedmoney = db.getEvent();

        for(int i =0;i<dataofowedmoney.size();i++)
        {
            DataOfDebt owed = new DataOfDebt();
            owed.setUdhariTableId(dataofowedmoney.get(i).getUdhariTableId());
            owed.setName(dataofowedmoney.get(i).getName());
           // owed.setEmailid(dataofowedmoney.get(i).getEmailid());
           // owed.setNumber(dataofowedmoney.get(i).getNumber());
            owed.setTime(dataofowedmoney.get(i).getTime());
            dataevents.add(owed);

        }
        db.close();


        eventAdapter = new EventAdapter(getActivity(),R.layout.custom_row_event,dataevents);
        listView.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }


    public class EventAdapter extends ArrayAdapter<DataOfDebt> {
        Activity activity;
        int layoutResource;
        DataOfDebt debt;
        ArrayList<DataOfDebt> mdata = new ArrayList<>();


        public EventAdapter(Activity act, int resource, ArrayList<DataOfDebt> objects) {
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
                holder.name = (TextView) row.findViewById(R.id.customrownameevent);
                holder.id = (TextView) row.findViewById(R.id.idcustomrowevent);
                holder.time = (TextView) row.findViewById(R.id.timecustomrowevent);
               // holder.update = (CuboidButton) row.findViewById(R.id.upgradebuttonevent);

                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.owe = getItem(position);
            holder.name.setText("Name of the event : " + holder.owe.getName());
            holder.id.setText("ID : " + holder.owe.getUdhariTableId());
            holder.time.setText("Time : " +  holder.owe.getTime());
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
                    //String Emailid = finalHolder.owe.getEmailid().toString();
                    //String Number = finalHolder.owe.getNumber().toString();
                    Integer iit = finalHolder.owe.getUdhariTableId();
                    Intent i = new Intent(getActivity(), Event2Activity.class);
                    i.putExtra("eventid", iit);
                    i.putExtra("eventname", Name);
                   // i.putExtra("emailid1", Emailid);
                    //i.putExtra("number1", Number);

                    startActivity(i);
                }
            });

            return row;
        }

        public class ViewHolder
        {
            DataOfDebt owe;
            TextView name;
            TextView time;
            TextView id;
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
        db.deleteRowofinnerlistmember(IDuwanttodelete);
        db.deleteRowofinnerlistexpend(IDuwanttodelete);
        db.deleteRowofouterlistevent(IDuwanttodelete);
        refreshData();



    }



}
